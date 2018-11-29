package de.steinberg.engine.homp.histogram.engine;

import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.monitor.AbstractAsyncMonitor;
import de.steinberg.engine.core.engine.setting.SettingsKey;
import de.steinberg.engine.homp.histogram.exception.XMLParserException;
import de.steinberg.engine.homp.histogram.generator.Histogram;
import de.steinberg.engine.homp.histogram.generator.HistogramGenerator;
import de.steinberg.engine.homp.histogram.parser.XMLParser;
import de.steinberg.engine.homp.histogram.parser.xml.entities.Document;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class HompHistogramGeneratorMonitor extends AbstractAsyncMonitor {

    @TooltipText("path to the folder with histogram xml files to parse. the parser will try to parse all files with xml extension")
    private class PathSetting implements SettingsKey {
        @Override
        public String get() {
            return "path";
        }
    }

    @TooltipText("parses input file and generates a nice histogram from it")
    private class GenerateHistogram implements Control {
        @Override
        public void trigger() {HompHistogramGeneratorMonitor.this.generateHistogram();}
    }

    private final PathSetting PATH = new PathSetting();
    private final GenerateHistogram GENERATE_HISTOGRAM = new GenerateHistogram();

    @Inject
    private HistogramGenerator histogramGenerator;

    @Inject
    private XMLParser xmlParser;

    public HompHistogramGeneratorMonitor () {
        settings.put(PATH, null);
        controls.put("generate histogram", GENERATE_HISTOGRAM);
    }

    @Override
    public boolean conditionFulfilled() {
        return false;
    }

    private void generateHistogram () {
        String pathString = settings.get(PATH);
        log.info("reading from " + pathString);
        Path path = Paths.get(pathString);
        try {
            Document document = xmlParser.parse(path);
            Histogram histogram = histogramGenerator.generateHistogram(document);
            histogram.plot();
        } catch (XMLParserException e) {
            handleHistogramGenerationException (e, path);
        }
    }

    private void handleHistogramGenerationException(XMLParserException e, Path path) {
        if (e.getCause() instanceof NoSuchFileException) {
            log.error("does not exist: " + path.toString());
        } else {
            log.error(e.toString());
            throw e;
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
