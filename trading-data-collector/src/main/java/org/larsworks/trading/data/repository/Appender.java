package org.larsworks.trading.data.repository;

/**
 * appends data to the repository
 */
public interface Appender<T> {
    void store(Repository repository, T data);
}
