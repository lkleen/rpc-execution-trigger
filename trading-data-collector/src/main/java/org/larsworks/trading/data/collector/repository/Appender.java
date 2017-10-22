package org.larsworks.trading.data.collector.repository;

/**
 * appends data to the repository
 */
public interface Appender<T> {
    void append(Repository repository, T data);
}
