package de.steinberg.rpc.execution.trigger.slave.engine;

import de.steinberg.rpc.execution.trigger.core.engine.AbstractAsyncMonitor;
import de.steinberg.rpc.execution.trigger.core.exception.MonitorException;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by lkleen on 11/28/2016.
 */
public class FileAddedMonitor extends AbstractAsyncMonitor {

    long previousNumMatches = -1;
    long numMatches = -1;

    @Getter
    @Setter
    Path path;

    @Getter
    @Setter
    String extension;

    public boolean conditionFulfilled() {
        try {
            Stream<Path> files = Files.walk(path, 1, FileVisitOption.FOLLOW_LINKS);

            numMatches = files.filter((p) -> {
                File file = p.toFile();
                return file.isFile() && file.getAbsolutePath().endsWith(extension);
            }).count();
        } catch (Exception e) {
            throw new MonitorException(e);
        }

        if (previousNumMatches == -1) {
            previousNumMatches = numMatches;
            return false;
        }

        boolean changed = previousNumMatches != numMatches;
        previousNumMatches = numMatches;

        return changed;
    }


}
