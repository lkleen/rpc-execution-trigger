package de.steinberg.rpc.execution.trigger.core.engine.monitor;

import de.steinberg.rpc.execution.trigger.core.annotations.DisplayNameResolver;
import de.steinberg.rpc.execution.trigger.core.engine.Controls;
import de.steinberg.rpc.execution.trigger.core.engine.Settings;
import de.steinberg.rpc.execution.trigger.core.engine.action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * implements asynchronous execution
 *
 * Created by lkleen on 11/28/2016.
 */
public abstract class AbstractAsyncMonitor implements Monitor {

    // scheduling defaults to once per second
    long period = 1;
    TimeUnit timeUnit = TimeUnit.SECONDS;

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    List<Action> actions = new ArrayList<>();
    Controls controls = new Controls();
    Settings settings = new Settings();
    DisplayNameResolver displayNameResolver = new DisplayNameResolver();

    @Override
    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public List<Action> getActions() {return actions;}

    @Override
    public Controls getControls() {return controls;}

    @Override
    public Settings getSettings() {return settings;}

    @Override
    public void setInterval(long period, TimeUnit timeUnit) {
        this.period = period;
        this.timeUnit = timeUnit;
    }

    @Override
    public void run() {
        try {
            if (conditionFulfilled()) {
                getActions().stream().forEach(action -> action.execute());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void runAsync() {
        executor.scheduleAtFixedRate(this, 0, period, timeUnit);
    }

    @Override
    public void shutdown() {
        executor.shutdown();
    }

    @Override
    public String toString() {
        return displayNameResolver.resolveFrom(this);
    }

}
