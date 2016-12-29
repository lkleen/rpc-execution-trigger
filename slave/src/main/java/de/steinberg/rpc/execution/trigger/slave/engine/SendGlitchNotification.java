package de.steinberg.rpc.execution.trigger.slave.engine;

import de.steinberg.rpc.execution.trigger.core.engine.action.AbstractAction;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 11/28/2016.
 */
@Slf4j
public class SendGlitchNotification extends AbstractAction {
    @Override
    public void execute() {
        log.info("FAKE NOTIFICATION");
    }
}
