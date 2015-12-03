package com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.persistors;

import java.sql.SQLException;

/**
 * @author mrebollob
 */
public interface Persistor<T> {
    void create(T data) throws SQLException;

    void update(T data) throws SQLException;
}
