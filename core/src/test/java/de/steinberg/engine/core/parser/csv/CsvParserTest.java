package de.steinberg.engine.core.parser.csv;

import de.steinberg.engine.core.parser.DefaultStreamParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by lars on 29.01.2017.
 */
public class CsvParserTest {

    @Test
    public void testCsvParser() {
        DefaultStreamParser parser = new DefaultStreamParser();
        parser.setMapper(new CsvObjectMapper());
        InputStream input = getClass().getClassLoader().getSystemResourceAsStream("companies.csv");
        List<Company> companies = parser.read(Company.class, input);
        Assert.assertEquals(companies.size(), 3);
    }

}
