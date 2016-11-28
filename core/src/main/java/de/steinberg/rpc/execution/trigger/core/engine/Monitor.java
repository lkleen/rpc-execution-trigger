package de.steinberg.rpc.execution.trigger.core.engine;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lkleen on 11/28/2016.
 */
public interface Monitor extends Runnable {

    void addListener(Listener listener);
    List<Listener> getListeners ();

    void setInterval(long period, TimeUnit timeUnit);

    boolean conditionFulfilled();

    void runAsync();
    void shutdown();

}
