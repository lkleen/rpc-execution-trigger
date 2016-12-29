package de.steinberg.rpc.execution.trigger.core.protocol.message;

import de.steinberg.rpc.execution.trigger.core.protocol.message.Message;
import lombok.Data;

/**
 * Created by lkleen on 22.11.2016.
 */
@Data
public class StringMessage implements Message {
    String value;
}
