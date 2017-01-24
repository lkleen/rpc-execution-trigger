package de.steinberg.engine.core.engine.monitor;

import de.steinberg.engine.core.annotations.DisplayNameResolver;
import de.steinberg.engine.core.engine.DefaultParameterized;
import de.steinberg.engine.core.engine.control.Controls;
import de.steinberg.engine.core.engine.setting.Settings;
import de.steinberg.engine.core.engine.action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * implements asynchronous execution
 *
 * Created by lkleen on 11/28/2016.
 */
public abstract class AbstractAsyncMonitor extends DefaultParameterized implements Monitor {

    // scheduling defaults to once per second
    long period = 1;
    TimeUnit timeUnit = TimeUnit.SECONDS;
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    List<Action> actions = new ArrayList<>();
    DisplayNameResolver displayNameResolver = new DisplayNameResolver();

    @Override
    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public List<Action> getActions() {return actions;}

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
