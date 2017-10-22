package org.larsworks.trading.data.persistence;

import org.larsworks.trading.data.repository.Repository;

import java.io.OutputStream;

/**
 * writes Repository to OutputStream
 */
public interface RepositoryWriter {
    void write(Repository repository, OutputStream outputStream);
}
