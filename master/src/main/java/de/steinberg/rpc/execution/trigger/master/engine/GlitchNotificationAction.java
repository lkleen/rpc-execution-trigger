package de.steinberg.rpc.execution.trigger.master.engine;

import de.steinberg.rpc.execution.trigger.core.engine.AbstractAction;
import de.steinberg.rpc.execution.trigger.core.protocol.impl.SocketStringSender;

import javax.inject.Inject;

/**
 * Created by lkleen on 12/14/2016.
 */
public class GlitchNotificationAction extends AbstractAction {

    static final String HOST_SETTING = "Host:";
    static final String PORT_SETTING = "Port:";

    @Inject
    SocketStringSender sender;

    public GlitchNotificationAction() {
        getSettings().put(HOST_SETTING, "");
        getSettings().put(PORT_SETTING, "");
    }

    @Override
    public void execute() {

    }
}
