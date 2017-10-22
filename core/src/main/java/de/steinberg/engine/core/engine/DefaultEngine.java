package de.steinberg.engine.core.engine;

import de.steinberg.engine.core.engine.monitor.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lkleen on 11/28/2016.
 */
public class DefaultEngine implements Engine {

    List<Monitor> monitors = new ArrayList<Monitor>();

    @Override
    public void addMonitor(Monitor monitor) {
        monitors.add(monitor);
    }

    @Override
    public List<Monitor> getMonitors() {
        return monitors;
    }

    @Override
    public void initialize() {
        monitors.forEach(monitor -> monitor.initialize());
    }

    @Override
    public void run() {
        for (Monitor monitor : monitors) {
            monitor.run();
        }
    }

    @Override
    public void runAsync() {
        for (Monitor monitor : monitors) {
            monitor.runAsync();
        }
    }

    @Override
    public void shutdown() {
        for (Monitor monitor : monitors) {
            monitor.shutdown();
        }
    }
}
