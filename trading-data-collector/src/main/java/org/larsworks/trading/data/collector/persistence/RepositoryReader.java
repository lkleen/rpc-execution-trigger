package org.larsworks.trading.data.collector.persistence;

import org.larsworks.trading.data.collector.repository.Repository;

import java.io.InputStream;

/**
 * reads Repository from InputStream
 */
public interface RepositoryReader {
    Repository read(InputStream input);
}
