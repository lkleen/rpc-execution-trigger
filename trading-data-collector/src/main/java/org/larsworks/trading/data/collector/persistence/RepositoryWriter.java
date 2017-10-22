package org.larsworks.trading.data.collector.persistence;

import org.larsworks.trading.data.collector.repository.Repository;

import java.io.OutputStream;

/**
 * writes Repository to OutputStream
 */
public interface RepositoryWriter {
    void write(Repository repository, OutputStream outputStream);
}
