package de.steinberg.rpc.execution.trigger.core.engine;

import java.util.List;

/**
 * ENGINE -> monitors -> listener -> actions
 *
 * the engine manages a list of monitors and provides global functionality for run and shutdown
 *
 * Created by lkleen on 11/28/2016.
 */
public interface Engine {

    void addMonitor(Monitor monitor);
    List<Monitor> getMonitors();

    void run();
    void runAsync();
    void shutdown();

}
