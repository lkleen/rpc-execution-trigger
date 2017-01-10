package de.steinberg.engine.glitchtest.detector.engine;

import de.steinberg.engine.core.engine.SettingsAwareControl;
import de.steinberg.engine.core.engine.monitor.AbstractAsyncMonitor;
import de.steinberg.engine.core.network.NetworkInterfacesInfo;
import de.steinberg.engine.core.protocol.message.GlitchNotificationMessage;
import de.steinberg.engine.core.protocol.message.Message;
import de.steinberg.engine.core.protocol.receiver.GlitchNotificationMessageReceiver;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.net.SocketException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by lkleen on 12/14/2016.
 */
@Slf4j
public class GlitchNotificationMonitor extends AbstractAsyncMonitor {

    boolean detectedGlitch = false;

    @Inject
    GlitchNotificationMessageReceiver receiver;

    @Inject
    ScheduledExecutorService executorService;

    Future<?> currentFuture;

    private static final String PORT = "port";

    public GlitchNotificationMonitor() {
        settings.put(PORT, null);
    }

    @Override
    protected void initializeControls() {
        controls.put("start", () -> {
            GlitchNotificationMonitor.this.startMonitoring();
        });
        controls.put("stop", () -> {
            GlitchNotificationMonitor.this.stopMonitoring();
        });
        controls.put("show interfaces", () -> {
            GlitchNotificationMonitor.this.showInterfaces();
        });
        controls.put("trigger", () -> {
            GlitchNotificationMonitor.this.detectedGlitch = true;
        });
    }

    void startMonitoring() {
        log.info("start listening to glitch notifications");
        currentFuture = executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        receiver.setPort(Integer.valueOf(settings.get(PORT)));
                        log.debug("glitch notification receiver waiting for messages");
                        Message<Integer> msg = receiver.receive();
                        GlitchNotificationMonitor.this.detectedGlitch = msg.getValue() == GlitchNotificationMessage.ID;
                        log.debug("glitch notification receiver received {}", msg.getValue());
                    }
                } catch (NumberFormatException e) {
                    log.error("invalid port: {}", settings.get(PORT));
                } catch (Exception e) {
                    if (e.getCause() instanceof SocketException) {
                        log.info(e.getCause().getMessage());
                        return;
                    }
                    log.error(e.toString());
                }
            }
        });
    }

    void stopMonitoring() {
        log.info("stop listening to glitch notifications");
        if (currentFuture != null) {
            receiver.close();
            currentFuture.cancel(true);
        }
    }

    void showInterfaces() {
        log.info("reading network interfaces list");
        log.info(NetworkInterfacesInfo.create());
    }

    @Override
    public boolean conditionFulfilled() {
        log.info("glitch detected");
        boolean glitchDetected = detectedGlitch;
        detectedGlitch = false;
        return glitchDetected;
    }
}
