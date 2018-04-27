package de.steinberg.engine.homp.histogram.parser.xml.entities;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class Document {
    public static class System {
        @XmlElement public int numberOfCpus;
        @XmlElement public float sampleRate;
        @XmlElement public float blocksize;
    }

    public static class Bin {
        @XmlAttribute public int index;
        @XmlAttribute public float value;
        @XmlValue public int elementValue;
    }

    public static class Histogram {
        @XmlElement public float minValue;
        @XmlElement public float avgValue;
        @XmlElement public float maxValue;
        @XmlElement public float medianValue;
        @XmlElement(name = "bin") public List<Bin> bins;
    }

    @XmlElement public Histogram histogram;

    @XmlElement public System system;

}
