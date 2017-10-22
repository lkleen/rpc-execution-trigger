package org.larsworks.trading.data.repository;

/**
 * appends data to the repository
 */
public interface Appender<T> {
    void append(Repository repository, T data);
}
