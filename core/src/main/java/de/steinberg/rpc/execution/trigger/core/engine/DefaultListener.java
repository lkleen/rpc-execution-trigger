package de.steinberg.rpc.execution.trigger.core.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lkleen on 11/28/2016.
 */
public class DefaultListener implements Listener {

    List<Action> actions = new ArrayList<Action>();

    public void addAction(Action action) {
        actions.add(action);
    }

    public List<Action> getActions() {
        return actions;
    }

    public void trigger() {
        for (Action action : actions) {
            action.run();
        }
    }
}
