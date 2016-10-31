package com.mrebollob.loteriadenavidad.app.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mrebollob.loteriadenavidad.BuildConfig;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.SharedPreferencesName;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module()
public class SpModule {

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Application application,
                                                      @SharedPreferencesName String sharedPreferencesName) {
        return application.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @SharedPreferencesName
    public String provideSharedPreferencesName() {
        return "lotery" + (BuildConfig.DEBUG ? "-dev" : "");
    }
}
