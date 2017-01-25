package de.steinberg.engine.ui.test;

import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.selection.SelectionList;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by lkleen on 11/29/2016.
 */
@Slf4j
public class ActionA extends ActionMock {

    public ActionA() {
        settings.put( () -> "schni","this");
        settings.put( () -> "schna","is");
        settings.put( () -> "schnuck","text");

        controls.put("action control a", new Control() {
            @Override
            public void trigger() {
                log.info("action control a triggered");

                for (SelectionList sl: selections.values()) {
                    log.info ("selected: {}", sl.getSelected());
                }

            }
        });

        SelectionList sizes = new SelectionList();
        sizes.add("small");
        sizes.add("medium");
        sizes.add("extra large");

        SelectionList colors = new SelectionList();
        colors.add("green");
        colors.add("black");
        colors.add("blue");

        selections.put("size", sizes);
        selections.put("color", colors);
    }

}
