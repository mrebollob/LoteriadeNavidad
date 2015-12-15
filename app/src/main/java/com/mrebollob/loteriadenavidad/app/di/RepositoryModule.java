package com.mrebollob.loteriadenavidad.app.di;

import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepositoryImp;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryBddDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryNetworkDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotterySPDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */
@Module(
        includes = {
                ApiModule.class, BddModule.class, SpModule.class
        },
        complete = false,
        library = true)
public class RepositoryModule {

    @Provides
    @Singleton
    LotteryRepository provideLotteryRepository(
            LotteryNetworkDataSource networkDataSource, LotteryBddDataSource bddDataSource,
            LotterySPDataSource spDataSource) {
        return new LotteryRepositoryImp(networkDataSource, bddDataSource, spDataSource);
    }
}