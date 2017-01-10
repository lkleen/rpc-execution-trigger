package de.steinberg.engine.core.engine.action;

import de.steinberg.engine.core.engine.Control;
import de.steinberg.engine.core.engine.Settings;
import de.steinberg.engine.core.protocol.message.GlitchNotificationMessage;
import de.steinberg.engine.core.protocol.sender.IntegerSocketSender;
import de.steinberg.engine.core.protocol.sender.SocketSender;

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
            public void trigger() {
                GlitchNotificationAction.this.execute();
            }
        });
    }

    @Override
    public void execute() {
        sender.setHost(settings.get(HOST_SETTING));
        sender.setPort(parseString(settings.get(PORT_SETTING)));
        sender.send(message);
    }

    private int parseString(String number) {
        try {
            return Integer.valueOf(number);
        } catch (NumberFormatException e) {
            return SocketSender.UNINITIALIZED;
        }
    }
}
