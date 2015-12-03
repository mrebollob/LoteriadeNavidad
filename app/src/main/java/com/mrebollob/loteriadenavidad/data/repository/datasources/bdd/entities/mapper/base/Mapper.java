package com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.mapper.base;

/**
 * @author mrebollob
 */
public interface Mapper<M, P> {
    P modelToData(M model);

    M dataToModel(P data);
}