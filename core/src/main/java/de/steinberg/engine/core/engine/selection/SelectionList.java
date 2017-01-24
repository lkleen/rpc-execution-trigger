package de.steinberg.engine.core.engine.selection;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * represents a sorted set of values to select from (eg. for a dropdown selection list)
 */
public class SelectionList extends ArrayList<String> {

    private String selected;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
