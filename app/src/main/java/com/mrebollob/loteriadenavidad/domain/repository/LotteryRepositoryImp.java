package com.mrebollob.loteriadenavidad.domain.repository;

import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryBddDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryNetworkDataSource;

/**
 * @author mrebollob
 */
public class LotteryRepositoryImp implements LotteryRepository {

    private final LotteryNetworkDataSource networkDataSource;
    private final LotteryBddDataSource bddDataSource;

    public LotteryRepositoryImp(LotteryNetworkDataSource networkDataSource,
                                LotteryBddDataSource bddDataSource) {
        this.networkDataSource = networkDataSource;
        this.bddDataSource = bddDataSource;
    }
}
