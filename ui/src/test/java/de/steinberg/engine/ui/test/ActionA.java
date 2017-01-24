package de.steinberg.engine.ui.test;

import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.control.Controls;
import lombok.extern.slf4j.Slf4j;

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
            }
        });
    }

}
