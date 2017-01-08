package de.steinberg.engine.core.protocol.message;

import lombok.Data;

/**
 * Created by lkleen on 22.11.2016.
 */
@Data
public class StringMessage implements Message {
    String value;
}
