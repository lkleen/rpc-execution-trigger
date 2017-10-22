package de.steinberg.engine.ui.test;

import de.steinberg.engine.core.engine.DefaultParametrized;
import de.steinberg.engine.core.engine.action.Action;
import de.steinberg.engine.core.engine.monitor.Monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lkleen on 11/29/2016.
 */
public class MonitorMock extends DefaultParametrized implements Monitor {

    List<Action> actions = new ArrayList<>();

    @Override
    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public List<Action> getActions() {
        return actions;
    }

    @Override
    public void initialize() {}

    @Override
    public void setInterval(long period, TimeUnit timeUnit) {

    }

    @Override
    public boolean conditionFulfilled() {
        return false;
    }

    @Override
    public void runAsync() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void run() {
        actions.stream().forEach(action -> action.execute());
    }

}
