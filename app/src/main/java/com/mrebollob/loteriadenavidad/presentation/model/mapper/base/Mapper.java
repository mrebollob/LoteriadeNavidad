package com.mrebollob.loteriadenavidad.presentation.model.mapper.base;

/**
 * @author mrebollob
 */
public interface Mapper<M, P> {
    P modelToData(M model);

    M dataToModel(P data);
}