package de.steinberg.engine.homp.histogram.parser;

import de.steinberg.engine.homp.histogram.exception.XMLParserException;
import de.steinberg.engine.homp.histogram.parser.xml.entities.Document;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class XMLParser {

    public Document parse (Path path) throws XMLParserException
    {
        try {
            InputStream is = Files.newInputStream(path);
            return JAXB.unmarshal(is, Document.class);
        } catch (IOException e) {
            throw new XMLParserException(e);
        }
    }

}
