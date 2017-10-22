package org.larsworks.trading.data.persistence.json;

import de.steinberg.engine.core.parser.ObjectMapper;
import org.larsworks.trading.data.collector.exception.PersistenceException;
import org.larsworks.trading.data.persistence.RepositoryWriter;
import org.larsworks.trading.data.repository.Repository;

import javax.inject.Inject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * writes Repository to an OutputStream in json format
 */
public class JsonRepositoryWriter implements RepositoryWriter {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public void write(Repository repository, OutputStream outputStream) {
        String json = objectMapper.write(Repository.class, repository);
        OutputStreamWriter osw = new OutputStreamWriter(outputStream);
        try {
            osw.write(json);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }
}
