package org.larsworks.trading.data.repository;

/**
 * Created by LKLeen on 29.03.2017.
 */
public interface Writer<T> {
    void store(Repository repository, T data);
}
