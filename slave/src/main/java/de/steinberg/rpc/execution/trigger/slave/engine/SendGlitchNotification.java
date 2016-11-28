package de.steinberg.rpc.execution.trigger.slave.engine;

import de.steinberg.rpc.execution.trigger.core.engine.Action;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 11/28/2016.
 */
@Slf4j
public class SendGlitchNotification implements Action {
    @Override
    public void run() {
        log.info("FAKE NOTIFICATION");
    }
}
