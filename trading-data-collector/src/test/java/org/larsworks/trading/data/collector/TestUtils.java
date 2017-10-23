package org.larsworks.trading.data.collector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.larsworks.trading.data.collector.provider.yahoo.yql.json.RepositoryAppender;
import org.larsworks.trading.data.collector.provider.yahoo.yql.json.Response;
import org.larsworks.trading.data.collector.repository.Repository;
import org.springframework.context.ApplicationContext;

import java.io.InputStream;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 22.10.17
 *        Time: 13:18
 */
public class TestUtils {

    public static Repository createRepositoryFromSingleResponse(ApplicationContext context, String filename) throws Exception {
        Repository repository = context.getBean(Repository.class);
        RepositoryAppender appender = context.getBean(RepositoryAppender.class);
        Response response = getResponse(context, filename);
        appender.append(repository, response);
        return repository;
    }

    private static Response getResponse(ApplicationContext context, String filename) throws Exception {
        ObjectMapper mapper = context.getBean(ObjectMapper.class);
        InputStream input = TestUtils.class.getClassLoader().getSystemResourceAsStream(filename);
        return mapper.readValue(input, Response.class);
    }

}
