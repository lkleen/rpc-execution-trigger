package de.steinberg.rpc.execution.trigger.core.engine;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * engine -> MONITORS -> listener -> actions
 *
 * a monitor monitors stuff and triggers a list of listeners.
 *
 * The implementation must support asynchronous execution
 *
 * Created by lkleen on 11/28/2016.
 */
public interface Monitor extends Runnable, SettingsAware {

    void addListener(Listener listener);
    List<Listener> getListeners ();

    void setInterval(long period, TimeUnit timeUnit);

    boolean conditionFulfilled();

    void runAsync();
    void shutdown();

}
