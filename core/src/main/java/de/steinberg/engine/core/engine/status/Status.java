package de.steinberg.engine.core.engine.status;

import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lars on 26.01.2017.
 */
public class Status {

    public enum Color {
        RED,
        GREEN,
        YELLOW
    }

    @Getter
    private final Color color;

    @Getter
    private final String text;

    public Status(Color color, String text) {
        this.color = color;
        this.text = text;
    }

}
