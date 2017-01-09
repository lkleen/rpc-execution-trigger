package de.steinberg.engine.glitchtest.detector.engine;

import de.steinberg.engine.core.engine.Control;
import de.steinberg.engine.core.engine.Controls;
import de.steinberg.engine.core.engine.Settings;

import javax.inject.Inject;

/**
 * Created by LKLeen on 09.01.2017.
 */
public class StartMonitoringControl implements Control {

    final GlitchNotificationMonitor monitor;

    public StartMonitoringControl(GlitchNotificationMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void setSettings(Settings settings) {
    }

    @Override
    public void trigger() {
        monitor.startMonitoring();
    }
}
