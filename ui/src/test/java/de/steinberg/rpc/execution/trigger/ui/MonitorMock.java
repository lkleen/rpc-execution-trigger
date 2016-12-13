package de.steinberg.rpc.execution.trigger.ui;

import de.steinberg.rpc.execution.trigger.core.engine.Controllers;
import de.steinberg.rpc.execution.trigger.core.engine.Listener;
import de.steinberg.rpc.execution.trigger.core.engine.Monitor;
import de.steinberg.rpc.execution.trigger.core.engine.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lkleen on 11/29/2016.
 */
public class MonitorMock implements Monitor {

    List<Listener> listeners = new ArrayList<>();

    @Override
    public Settings getSettings() {
        return null;
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public List<Listener> getListeners() {
        return listeners;
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

    }

    @Override
    public Controllers getControllers() {
        return new Controllers();
    }
}
