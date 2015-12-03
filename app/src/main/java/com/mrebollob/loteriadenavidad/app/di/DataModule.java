package com.mrebollob.loteriadenavidad.app.di;

import android.os.Build;

import com.mrebollob.loteriadenavidad.BuildConfig;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.data.RetrofitLog;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.data.UserAgent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;


/**
 * @author mrebollob
 */
@Module(
        includes = {
                InteractorsModule.class, RepositoryModule.class,
        },
        complete = false,
        library = true)
public class DataModule {

    @Provides
    @Singleton Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(BuildConfig.API_URL);
    }

    @Provides @Singleton
    @RetrofitLog
    boolean provideRetrofitLog() {
        return BuildConfig.RETROFIT_LOG;
    }

    @Provides @Singleton @UserAgent
    String provideUserAgent() {
        return String.format("Sample-Android;%s;%s;%s;%d;", Build.MANUFACTURER, Build.MODEL,
                Build.VERSION.RELEASE, BuildConfig.VERSION_CODE);
    }
}