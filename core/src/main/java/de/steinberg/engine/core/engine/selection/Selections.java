package de.steinberg.engine.core.engine.selection;

import lombok.Getter;
import lombok.Setter;

import java.util.TreeSet;

/**
 * Created by lars on 24.01.2017.
 */
public class Selections extends TreeSet<Selection> {

    @Getter
    @Setter
    private Selection selection;

}
