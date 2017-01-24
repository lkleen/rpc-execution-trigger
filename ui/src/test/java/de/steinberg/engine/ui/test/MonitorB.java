package de.steinberg.engine.ui.test;

import de.steinberg.engine.core.annotations.DisplayName;

/**
 * Created by lkleen on 11/29/2016.
 */
@DisplayName("awkward monitor")
public class MonitorB extends MonitorMock {

    public MonitorB() {
        settings.put( () -> {return "schnick";}, null);
        settings.put( () -> {return "schnuck";}, null);
    }

}
