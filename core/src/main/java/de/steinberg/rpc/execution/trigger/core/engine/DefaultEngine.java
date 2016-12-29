package de.steinberg.rpc.execution.trigger.core.engine;

import de.steinberg.rpc.execution.trigger.core.engine.monitor.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lkleen on 11/28/2016.
 */
public class DefaultEngine implements Engine {

    List<Monitor> monitors = new ArrayList<Monitor>();

    public void addMonitor(Monitor monitor) {
        monitors.add(monitor);
    }

    public List<Monitor> getMonitors() {
        return monitors;
    }

    public void run() {
        for (Monitor monitor : monitors) {
            monitor.run();
        }
    }

    public void runAsync() {
        for (Monitor monitor : monitors) {
            monitor.runAsync();
        }
    }

    public void shutdown() {
        for (Monitor monitor : monitors) {
            monitor.shutdown();
        }
    }
}
