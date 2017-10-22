package org.larsworks.trading.data.persistence;

import org.larsworks.trading.data.repository.Repository;

import java.io.InputStream;

/**
 * reads Repository from InputStream
 */
public interface RepositoryReader {
    Repository read(InputStream input);
}
