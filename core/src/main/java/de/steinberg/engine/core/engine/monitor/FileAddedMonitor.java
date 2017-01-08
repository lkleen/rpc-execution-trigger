package de.steinberg.engine.core.engine.monitor;

import de.steinberg.engine.core.exception.MonitorException;

import java.io.File;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by lkleen on 11/28/2016.
 */
public class FileAddedMonitor extends AbstractAsyncMonitor {

    private static String PATH = "path";
    private static String EXTENSION = "file extension";

    long previousNumMatches = -1;
    long numMatches = -1;

    Path path;

    String extension;

    public FileAddedMonitor() {
        settings.put(PATH, "");
        settings.put(EXTENSION, "");
    }

    public boolean conditionFulfilled() {
        try {
            path = Paths.get(settings.get(PATH));
            extension = settings.get(EXTENSION);
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
