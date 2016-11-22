package de.steinberg.rpc.execution.trigger.core.protocol;

import lombok.Data;

/**
 * Created by lkleen on 22.11.2016.
 */
@Data
public class StringMessage implements Message {
    String value;
}
