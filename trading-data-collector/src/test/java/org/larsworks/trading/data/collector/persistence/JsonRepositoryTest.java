package org.larsworks.trading.data.collector.persistence;

import org.larsworks.trading.data.collector.TestUtils;
import org.larsworks.trading.data.collector.configuration.TradingDataCollectorConfiguration;
import org.larsworks.trading.data.collector.persistence.json.JsonRepositoryReader;
import org.larsworks.trading.data.collector.persistence.json.JsonRepositoryWriter;
import org.larsworks.trading.data.collector.repository.Repository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 22.10.17
 *        Time: 13:17
 */
public class JsonRepositoryTest {

    @Test
    public void testWriteRepository () throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        Repository repository = getTestRepository(context);
        JsonRepositoryWriter writer = context.getBean(JsonRepositoryWriter.class);
        Path path = createTestFile();
        writer.write(repository, Files.newOutputStream(path));
        Assert.assertEquals(repository.getCompanies().size(), 1);
        Files.delete(path);
    }

    private Path createTestFile() throws IOException {
        Path path = Paths.get("repo.test.json");
        if(!Files.exists(path)) {path = Files.createFile(path);}
        return path;
    }

    @Test
    public void testWriteReadRepository () throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                TradingDataCollectorConfiguration.class
        );
        Repository expectedRepository = getTestRepository(context);
        JsonRepositoryWriter writer = context.getBean(JsonRepositoryWriter.class);
        Path path = createTestFile();
        writer.write(expectedRepository, Files.newOutputStream(path));
        Assert.assertEquals(expectedRepository.getCompanies().size(), 1);

        InputStream input = Files.newInputStream(path);
        JsonRepositoryReader reader = context.getBean(JsonRepositoryReader.class);
        Repository actualRepository = reader.read(input);
        Files.delete(path);

        Assert.assertEquals(actualRepository.getCompanies().size(), expectedRepository.getCompanies().size());
    }

    private Repository getTestRepository(ApplicationContext context) throws Exception {
        return TestUtils.createRepositoryFromSingleResponse(context, "yql-query-response.json");
    }
}
