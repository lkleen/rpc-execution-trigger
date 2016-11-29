package de.steinberg.rpc.execution.trigger.core.engine;

import java.util.List;

/**
 * engine -> monitors -> LISTENER -> actions
 *
 * a listener triggers a list of actions
 *
 * Created by lkleen on 11/28/2016.
 */
public interface Listener {

    void addAction(Action action);
    List<Action> getActions();

    void trigger();

}
