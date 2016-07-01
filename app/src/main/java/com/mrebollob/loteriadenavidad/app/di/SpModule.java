package com.mrebollob.loteriadenavidad.app.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mrebollob.loteriadenavidad.BuildConfig;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.data.SharedPreferencesName;
import com.mrebollob.loteriadenavidad.data.repository.datasources.sp.LotterySPDataSourceImp;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotterySPDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */
@Module(
        complete = false,
        library = true)
public class SpModule {

    @Provides
    @Singleton
    LotterySPDataSource provideLotterySPDataSource(SharedPreferences sharedPreferences) {
        return new LotterySPDataSourceImp(sharedPreferences);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application,
                                               @SharedPreferencesName String sharedPreferencesName) {
        return application.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @SharedPreferencesName
    String provideSharedPreferencesName() {
        return "loteria_navidad" + (BuildConfig.DEBUG ? "-dev" : "");
    }

}
