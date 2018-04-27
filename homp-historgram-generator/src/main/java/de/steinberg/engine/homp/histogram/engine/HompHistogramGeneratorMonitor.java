package de.steinberg.engine.homp.histogram.engine;

import de.steinberg.engine.core.annotations.TooltipText;
import de.steinberg.engine.core.engine.control.Control;
import de.steinberg.engine.core.engine.monitor.AbstractAsyncMonitor;
import de.steinberg.engine.core.engine.setting.SettingsKey;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HompHistogramGeneratorMonitor extends AbstractAsyncMonitor {

    @TooltipText("path to the file to parse")
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

    public HompHistogramGeneratorMonitor () {
        settings.put(PATH, null);
        controls.put("generate histogram", GENERATE_HISTOGRAM);
    }

    @Override
    public boolean conditionFulfilled() {
        return false;
    }

    private void generateHistogram () {
        log.info("reading from " + settings.get(PATH));
    }
}
