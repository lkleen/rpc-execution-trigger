package org.larsworks.trading.data.collector.persistence.json;

import de.steinberg.engine.core.parser.ObjectMapper;
import org.larsworks.trading.data.collector.persistence.RepositoryReader;
import org.larsworks.trading.data.collector.repository.Repository;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * reads a repository from an InputStream in json format
 */
public class JsonRepositoryReader implements RepositoryReader {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Repository read(InputStream input) {
        String str = fromInputStream (input);
        return objectMapper.read(Repository.class, str);
    }

    private String fromInputStream(InputStream input) {
        InputStreamReader isr = new InputStreamReader(input);
        Stream<String> lines = new BufferedReader(isr).lines();
        StringBuilder sb = new StringBuilder();
        lines.forEach(line -> sb.append(line));
        return sb.toString();
    }
}
