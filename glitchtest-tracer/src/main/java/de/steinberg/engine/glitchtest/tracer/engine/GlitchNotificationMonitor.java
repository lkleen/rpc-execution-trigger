package de.steinberg.engine.glitchtest.tracer.engine;

import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.setting.SettingsKey;
import de.steinberg.engine.core.engine.monitor.AbstractAsyncMonitor;
import de.steinberg.engine.core.exception.MonitorException;
import de.steinberg.engine.core.protocol.message.GlitchNotificationMessage;
import de.steinberg.engine.core.protocol.message.Message;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by lkleen on 12/14/2016.
 */
@Slf4j
public class GlitchNotificationMonitor extends AbstractAsyncMonitor {

    @TooltipText("network port to listen for glitch notifications from the glitchtest-detector tool")
    private class PortSetting implements SettingsKey {
        @Override
        public String get() {
            return "port";
        }
    }

    @TooltipText("starts listening to network port")
    private class Start implements Control {
        @Override
        public void trigger() {
            GlitchNotificationMonitor.this.startMonitoring();
        }
    }

    @TooltipText("stops listening to network port")
    private class Stop implements Control {
        @Override
        public void trigger() {
            GlitchNotificationMonitor.this.stopMonitoring();
        }
    }

    @TooltipText("shows the local host and ip address. please note that the " +
            "ip address might be wrong since there are a lot of interfaces present. " +
            "in this case use 'ipconfig' (win) or 'ifconfig' (mac) to retrive the correct address")
    private class ShowLocalhost implements Control {
        @Override
        public void trigger() {
            GlitchNotificationMonitor.this.showLocalhost();
        }
    }



    boolean detectedGlitch = false;

    @Inject
    GlitchNotificationMessageReceiver receiver;

    @Inject
    ScheduledExecutorService executorService;

    Future<?> currentFuture;

    private final PortSetting PORT = new PortSetting();

    private final Control start = new Start();
    private final Control stop = new Stop();
    private final Control showLocalHost = new ShowLocalhost();

    public GlitchNotificationMonitor() {
        settings.put(PORT, null);
        initializeControls();
    }

    protected void initializeControls() {
        controls.put("start", start);
        controls.put("stop", stop);
        controls.put("show localhost", showLocalHost);
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
                        Message<Character> msg = receiver.receive();
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

    void showLocalhost() {
        try {
            log.info(InetAddress.getLocalHost().toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MonitorException(e);
        }

    }

    @Override
    public void shutdown() {
        super.shutdown();
        stopMonitoring();
        executorService.shutdown();
    }

    @Override
    public boolean conditionFulfilled() {
        if (detectedGlitch) {log.info("glitch detected");}
        boolean glitchDetected = detectedGlitch;
        detectedGlitch = false;
        return glitchDetected;
    }
}
