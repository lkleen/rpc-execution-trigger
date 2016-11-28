package de.steinberg.rpc.execution.trigger.slave;

import de.steinberg.rpc.execution.trigger.slave.engine.FileAddedMonitor;
import de.steinberg.rpc.execution.trigger.slave.engine.SlaveEngine;

import javax.inject.Inject;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by lkleen on 11/28/2016.
 */
public class UI {

    @Inject
    FileAddedMonitor fileAddedMonitor;

    @Inject
    SlaveEngine slaveEngine;

    void run() {
        Path path = Paths.get("S:\\temp");
        fileAddedMonitor.setPath(path);
        fileAddedMonitor.setExtension(".test");
        slaveEngine.runAsync();
    }

}
