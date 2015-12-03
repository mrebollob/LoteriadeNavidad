package com.mrebollob.loteriadenavidad.data.repository.datasources.api;

import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryNetworkDataSource;

/**
 * @author mrebollob
 */
public class LotteryNetworkDataSourceImp implements LotteryNetworkDataSource {

    private LotteryApiService apiService;

    public LotteryNetworkDataSourceImp(LotteryApiService apiService) {
        this.apiService = apiService;
    }
}
