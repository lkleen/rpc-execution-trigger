package de.steinberg.engine.homp.histogram;

import de.steinberg.engine.homp.histogram.parser.xml.entities.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXB;
import java.io.InputStream;

public class XmlParserTest {

    @Test
    public void test() {
        InputStream is = ClassLoader.getSystemResourceAsStream("rt-load-histogram.xml");
        Document document = JAXB.unmarshal(is, Document.class);
        Assert.assertNotNull(document);
    }

}
