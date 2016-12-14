package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Action;
import de.steinberg.rpc.execution.trigger.core.engine.Controls;
import de.steinberg.rpc.execution.trigger.core.engine.Monitor;
import de.steinberg.rpc.execution.trigger.core.engine.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lkleen on 11/29/2016.
 */
public class MonitorMock implements Monitor {

    List<Action> actions = new ArrayList<>();

    @Override
    public Settings getSettings() {
        return null;
    }


    @Override
    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public List<Action> getActions() {
        return actions;
    }

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

    public Controls getControls() {
        return new Controls();
    }
}
