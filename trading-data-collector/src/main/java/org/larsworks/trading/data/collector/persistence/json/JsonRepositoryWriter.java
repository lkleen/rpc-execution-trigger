package org.larsworks.trading.data.collector.persistence.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.larsworks.trading.data.collector.exception.PersistenceException;
import org.larsworks.trading.data.collector.persistence.RepositoryWriter;
import org.larsworks.trading.data.collector.repository.Repository;

import javax.inject.Inject;
import java.io.IOException;
import java.io.OutputStream;

/**
 * writes Repository to an OutputStream in json format
 */
public class JsonRepositoryWriter implements RepositoryWriter {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public void write(Repository repository, OutputStream outputStream) {
        try {
            objectMapper.writeValue(outputStream, repository);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }
}
