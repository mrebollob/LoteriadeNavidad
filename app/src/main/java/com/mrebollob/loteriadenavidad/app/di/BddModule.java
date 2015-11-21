package com.mrebollob.loteriadenavidad.app.di;

import android.app.Application;

import com.mrebollob.loteriadenavidad.BuildConfig;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.data.DatabaseName;
import com.mrebollob.loteriadenavidad.data.DatabaseHelper;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.LotteryBddDataSourceImp;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryBddDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */

@Module(
        complete = false,
        library = true)
public class BddModule {

    @Provides @Singleton
    LotteryBddDataSource provideContactsBddDataSource() {
        return new LotteryBddDataSourceImp();
    }

    @Provides @Singleton public DatabaseHelper provideDatabaseHelper(
            @DatabaseName String databaseName, Application app) {
        return new DatabaseHelper(databaseName, app);
    }

    @Provides @Singleton @DatabaseName String provideDatabaseName() {
        return "loteria_navidad" + (BuildConfig.DEBUG ? "-dev" : "") + ".db";
    }

}