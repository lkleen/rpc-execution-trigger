package de.steinberg.engine.homp.histogram.configuration;

import de.steinberg.engine.core.engine.Engine;
import de.steinberg.engine.homp.histogram.engine.HompHistogramGeneratorEngine;
import de.steinberg.engine.homp.histogram.engine.HompHistogramGeneratorMonitor;
import de.steinberg.engine.homp.histogram.generator.HistogramGenerator;
import de.steinberg.engine.homp.histogram.parser.XMLParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HompHistogramGeneratorConfiguration {

    @Bean
    public Engine engine() {
        Engine engine = new HompHistogramGeneratorEngine();
        engine.addMonitor(hompHistogramGeneratorMonitor());
        return engine;
    }

    @Bean
    public HompHistogramGeneratorMonitor hompHistogramGeneratorMonitor() {
        return new HompHistogramGeneratorMonitor();
    }

    @Bean
    public XMLParser xmlParser() {
        return new XMLParser();
    }

    @Bean
    public HistogramGenerator histogramGenerator() {
        return new HistogramGenerator();
    }

}
