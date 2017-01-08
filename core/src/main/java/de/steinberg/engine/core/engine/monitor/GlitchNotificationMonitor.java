package de.steinberg.engine.core.engine.monitor;

import de.steinberg.engine.core.engine.Control;
import de.steinberg.engine.core.engine.Settings;
import de.steinberg.engine.core.network.NetworkInterfacesInfo;
import de.steinberg.engine.core.protocol.message.GlitchNotificationMessage;
import de.steinberg.engine.core.protocol.message.Message;
import de.steinberg.engine.core.protocol.receiver.GlitchNotificationMessageReceiver;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
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

    public GlitchNotificationMonitor() {
        controls.put("start", new Control() {
            @Override
            public void setSettings(Settings settings) {
            }

            @Override
            public void trigger() {
                GlitchNotificationMonitor.this.startMonitoring();
            }
        });
        controls.put("stop", new Control() {
            @Override
            public void setSettings(Settings settings) {
            }

            @Override
            public void trigger() {
                GlitchNotificationMonitor.this.stopMonitoring();
            }
        });
        controls.put("show interfaces", new Control() {
            @Override
            public void setSettings(Settings settings) {
            }

            @Override
            public void trigger() {
                GlitchNotificationMonitor.this.showInterfaces();
            }
        });
    }

    void startMonitoring() {
        log.info("start listening to glitch notifications");
        currentFuture = executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        log.debug("glitch notification receiver waiting for messages");
                        Message<Integer> msg = receiver.receive();
                        GlitchNotificationMonitor.this.detectedGlitch = msg.getValue() == GlitchNotificationMessage.ID;
                        log.debug("glitch notification receiver received {}", msg.getValue());
                    }
                } catch (Exception e) {
                    log.debug("glitch notification receiver interrupted");
                }
            }
        });
    }

    void stopMonitoring() {
        log.info("stop listening to glitch notifications");
        if (currentFuture != null) {
            currentFuture.cancel(true);
        }
    }

    void showInterfaces() {
        log.info("reading network interfaces list");
        log.info(NetworkInterfacesInfo.create());
    }

    @Override
    public boolean conditionFulfilled() {
        boolean glitchDetected = detectedGlitch;
        detectedGlitch = false;
        return glitchDetected;
    }
}
