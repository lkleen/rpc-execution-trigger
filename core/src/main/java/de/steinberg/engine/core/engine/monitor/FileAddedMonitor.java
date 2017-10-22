package de.steinberg.engine.core.engine.monitor;

import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.setting.SettingsKey;
import de.steinberg.engine.core.engine.status.Status;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by lkleen on 11/28/2016.
 */
@Slf4j
public class FileAddedMonitor extends AbstractAsyncMonitor {

    @TooltipText("the folder which should be monitored (once every second)")
    private class PathSetting implements SettingsKey {
        @Override
        public String get() {
            return "path";
        }
    }

    @TooltipText("only files with the given file extension are monitored eg .log")
    private class ExtensionSetting implements SettingsKey {
        @Override
        public String get() {
            return "file extension";
        }
    }

    private final SettingsKey PATH = new PathSetting();
    private final SettingsKey EXTENSION = new ExtensionSetting();

    long previousNumMatches = -1;
    long numMatches = -1;

    Path path;

    String extension;

    public FileAddedMonitor() {
        settings.put(PATH, "");
        settings.put(EXTENSION, "");
        statusProperty = new SimpleObjectProperty<>(new Status(Status.Color.RED, "disabled"));
    }

    @Override
    public boolean conditionFulfilled() {
        try {
            path = Paths.get(settings.get(PATH));
            extension = settings.get(EXTENSION);
            statusProperty.set(new Status(Status.Color.GREEN, "monitoring\n" + path.toAbsolutePath().toString()));
            Stream<Path> files = Files.walk(path, 1, FileVisitOption.FOLLOW_LINKS);
            numMatches = files.filter((p) -> {
                File file = p.toFile();
                return file.isFile() && file.getAbsolutePath().endsWith(extension);
            }).count();
        } catch (Exception e) {
            statusProperty.set(new Status(Status.Color.RED, "invalid path: " + path.toString()));
            log.error(e.toString());
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
