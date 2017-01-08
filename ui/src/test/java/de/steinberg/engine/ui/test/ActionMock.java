package de.steinberg.engine.ui.test;

import de.steinberg.engine.core.engine.action.AbstractAction;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lkleen on 11/29/2016.
 */
@Slf4j
public class ActionMock extends AbstractAction {

    @Override
    public void execute() {
        log.debug("executing action {}",this);
    }
}
