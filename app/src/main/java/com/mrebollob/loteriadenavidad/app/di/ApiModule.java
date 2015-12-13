package com.mrebollob.loteriadenavidad.app.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.data.RetrofitLog;
import com.mrebollob.loteriadenavidad.data.repository.datasources.api.LotteryApiService;
import com.mrebollob.loteriadenavidad.data.repository.datasources.api.LotteryNetworkDataSourceImp;
import com.mrebollob.loteriadenavidad.data.repository.datasources.api.converter.StringConverter;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryNetworkDataSource;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * @author mrebollob
 */

@Module(
        complete = false,
        library = true)
public class ApiModule {

    @Provides
    @Singleton
    LotteryNetworkDataSource provideLotteryNetworkDataSource(
            LotteryApiService apiService, Gson gson) {
        return new LotteryNetworkDataSourceImp(apiService, gson);
    }

    @Provides
    @Singleton
    LotteryApiService provideApiService(StringConverter stringConverter, Endpoint endPoint,
                                        RestAdapter.LogLevel logLevel, OkClient okClient) {
        return new RestAdapter.Builder().setEndpoint(endPoint)
                .setLogLevel(logLevel)
                .setConverter(stringConverter)
                .setClient(okClient)
                .build()
                .create(LotteryApiService.class);
    }

    @Provides
    @Singleton
    RestAdapter.LogLevel provideLogLevel(@RetrofitLog boolean logTraces) {
        return logTraces ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE;
    }

    @Provides
    @Singleton
    OkClient provideOkClient() {
        return new OkClient(new OkHttpClient());
    }

    @Provides
    @Singleton
    GsonConverter provideGsonConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides
    @Singleton
    StringConverter provideStringConverter() {
        return new StringConverter();
    }

    @Provides
    @Singleton
    Gson provideGsonParser() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
}