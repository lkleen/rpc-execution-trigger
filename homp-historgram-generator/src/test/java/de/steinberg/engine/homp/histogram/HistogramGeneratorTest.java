package de.steinberg.engine.homp.histogram;

import de.steinberg.engine.homp.histogram.generator.Histogram;
import de.steinberg.engine.homp.histogram.generator.HistogramGenerator;
import de.steinberg.engine.homp.histogram.parser.xml.entities.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXB;
import java.io.InputStream;

public class HistogramGeneratorTest {

    // @Test disabled, depends on the availability of gnuplot
    public void test () {
        InputStream is = ClassLoader.getSystemResourceAsStream("rt-load-histogram.xml");
        Document document = JAXB.unmarshal(is, Document.class);
        HistogramGenerator histogramGenerator = new HistogramGenerator();
        Histogram histogram = histogramGenerator.generateHistogram(document);
        Assert.assertNotNull(histogram);
        //histogram.plot ();
    }

}
