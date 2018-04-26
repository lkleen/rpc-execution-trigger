package de.steinberg.engine.homp.histogram.parser.xml.entities;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class Document {
    public static class System {
        @XmlElement int numberOfCpus;
        @XmlElement float sampleRate;
        @XmlElement float blocksize;
    }

    public static class Bin {
        @XmlAttribute int index;
        @XmlAttribute float value;
        @XmlValue int elementValue;
    }

    public static class Histogram {
        @XmlElement float minValue;
        @XmlElement float avgValue;
        @XmlElement float maxValue;
        @XmlElement float medianValue;
        @XmlElement(name = "bin") List<Bin> bins;
    }

    @XmlElement Histogram histogram;

    @XmlElement System system;

}
