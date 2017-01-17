package de.steinberg.engine.glitchtest.detector.engine;

import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.Control;
import de.steinberg.engine.core.engine.SettingsKey;
import de.steinberg.engine.core.engine.action.AbstractAction;
import de.steinberg.engine.core.protocol.message.GlitchNotificationMessage;
import de.steinberg.engine.core.protocol.sender.SingleCharSocketSender;
import de.steinberg.engine.core.protocol.sender.SocketSender;

import javax.inject.Inject;

/**
 * Created by lkleen on 12/14/2016.
 */
public class GlitchNotificationAction extends AbstractAction {

    @TooltipText("host name or ip address to send the glitch notification eg. localhost or 192.168.0.5")
    private class HostSetting implements SettingsKey {
        @Override
        public String get() {
            return "Host:";
        }
    }

    @TooltipText("network port to send the glitch notification. a glitchtest-tracer must be listening to that port.")
    private class PortSetting implements SettingsKey {
        @Override
        public String get() {
            return "Port:";
        }
    }

    final HostSetting HOST_SETTING = new HostSetting();
    final PortSetting PORT_SETTING = new PortSetting();

    static final GlitchNotificationMessage message = new GlitchNotificationMessage();

    @Inject
    SingleCharSocketSender sender;

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
