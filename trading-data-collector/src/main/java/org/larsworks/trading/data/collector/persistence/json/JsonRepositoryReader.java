package org.larsworks.trading.data.collector.persistence.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.larsworks.trading.data.collector.exception.PersistenceException;
import org.larsworks.trading.data.collector.persistence.RepositoryReader;
import org.larsworks.trading.data.collector.repository.Repository;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

/**
 * reads a repository from an InputStream in json format
 */
public class JsonRepositoryReader implements RepositoryReader {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Repository read(InputStream input) {
        try {
            return objectMapper.readValue(input, Repository.class);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

}
