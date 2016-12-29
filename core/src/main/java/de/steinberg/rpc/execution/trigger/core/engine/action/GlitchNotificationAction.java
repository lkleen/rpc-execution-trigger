package de.steinberg.rpc.execution.trigger.core.engine.action;

import de.steinberg.rpc.execution.trigger.core.engine.Control;
import de.steinberg.rpc.execution.trigger.core.engine.Settings;
import de.steinberg.rpc.execution.trigger.core.protocol.message.GlitchNotificationMessage;
import de.steinberg.rpc.execution.trigger.core.protocol.sender.IntegerSocketSender;

import javax.inject.Inject;

/**
 * Created by lkleen on 12/14/2016.
 */
public class GlitchNotificationAction extends AbstractAction {

    static final String HOST_SETTING = "Host:";
    static final String PORT_SETTING = "Port:";

    static final GlitchNotificationMessage message = new GlitchNotificationMessage();

    @Inject
    IntegerSocketSender sender;

    public GlitchNotificationAction() {
        settings.put(HOST_SETTING, "");
        settings.put(PORT_SETTING, "");
        controls.put("send glitch notification", new Control() {
            @Override
            public void setSettings(Settings settings) {
            }
            @Override
            public void trigger() {
                GlitchNotificationAction.this.execute();
            }
        });
    }

    @Override
    public void execute() {
        sender.setHost(settings.get(HOST_SETTING));
        sender.setPort(Integer.valueOf(settings.get(PORT_SETTING)));
        sender.send(message);
    }
}
