package de.steinberg.rpc.execution.trigger.core.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by lkleen on 11/28/2016.
 */
public abstract class AbstractAsyncMonitor implements Monitor {

    // scheduling defaults to once per second
    long period = 1;
    TimeUnit timeUnit = TimeUnit.SECONDS;

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    List<Listener> listeners = new ArrayList<Listener>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public List<Listener> getListeners() {
        return listeners;
    }

    public void setInterval(long period, TimeUnit timeUnit) {
        this.period = period;
        this.timeUnit = timeUnit;
    }

    public void run() {
        try {
            if (conditionFulfilled()) {
                for (Listener listener : listeners) {
                    listener.trigger();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void runAsync() {
        executor.scheduleAtFixedRate(this, 0, period, timeUnit);
    }

    public void shutdown() {
        executor.shutdown();
    }

}
