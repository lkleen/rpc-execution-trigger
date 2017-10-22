package org.larsworks.trading.data.collector.engine.monitor;

import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.monitor.AbstractAsyncMonitor;
import de.steinberg.engine.core.engine.setting.SettingsKey;
import de.steinberg.engine.core.exception.ActionException;
import de.steinberg.engine.core.exception.MonitorException;
import lombok.extern.slf4j.Slf4j;
import org.larsworks.trading.data.collector.persistence.json.JsonRepositoryReader;
import org.larsworks.trading.data.collector.persistence.json.JsonRepositoryWriter;
import org.larsworks.trading.data.collector.repository.Repository;
import org.slf4j.spi.LocationAwareLogger;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:41
 */
@Slf4j
public class TriggerCollectionMonitor extends AbstractAsyncMonitor {

    @TooltipText("path to the json repository file (note: changing the path is not implemented and will be ignored)")
    private class JsonFilePathSetting implements SettingsKey {
        @Override
        public String get() {
            return "jsonFilePath";
        }
    }

    @TooltipText("starts trading data collection")
    private class Start implements Control {
        @Override
        public void trigger() {
            log.info("data collection started");
            started = true;
        }
    }

    @TooltipText("stops trading data collection")
    private class Stop implements Control {
        @Override
        public void trigger() {
            log.info("data collection stopped");
            started = false;
        }
    }

    boolean started = false;

    final JsonFilePathSetting jsonFilePathSetting = new JsonFilePathSetting();

    Path repositoryPath = null;

    @Inject
    Repository repository;

    @Inject
    JsonRepositoryReader repositoryReader;

    @Inject
    JsonRepositoryWriter repositoryWriter;

    public TriggerCollectionMonitor() {
        controls.put("start", new Start());
        controls.put("stop", new Stop());
        settings.put(jsonFilePathSetting,"repository.json");
    }

    @Override
    public void initialize () {
        initializeRepository();
    }

    @Override
    public boolean conditionFulfilled() {
        return started;
    }

    @Override
    public void shutdown() {
        super.shutdown();
        writeBackRepository();
    }

    private void writeBackRepository() {
        try {
            repositoryWriter.write(repository, Files.newOutputStream(repositoryPath));
        } catch (IOException e) {
            throw new MonitorException(e);
        }
    }

    private void initializeRepository() {
        try {
            repositoryPath = Paths.get(settings.get(jsonFilePathSetting));
            if (!Files.exists(repositoryPath)) {
                Files.createFile(repositoryPath);
                repository = new Repository();
            } else {
                InputStream input = Files.newInputStream(repositoryPath);
                repository = repositoryReader.read(input);
            }
        } catch (IOException e) {
            throw new ActionException(e);
        }
    }

}
