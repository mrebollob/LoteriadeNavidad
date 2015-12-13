package com.mrebollob.loteriadenavidad.app.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.data.RetrofitLog;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.data.UserAgent;
import com.mrebollob.loteriadenavidad.data.repository.datasources.api.LotteryApiService;
import com.mrebollob.loteriadenavidad.data.repository.datasources.api.LotteryNetworkDataSourceImp;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryNetworkDataSource;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
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
            LotteryApiService apiService) {
        return new LotteryNetworkDataSourceImp(apiService);
    }

    @Provides
    @Singleton
    LotteryApiService provideApiService(GsonConverter gsonConverter,
                                        ErrorHandler errorHandler,
                                        RequestInterceptor requestInterceptor, Endpoint endPoint,
                                        RestAdapter.LogLevel logLevel, OkClient okClient) {
        return new RestAdapter.Builder().setEndpoint(endPoint)
                .setLogLevel(logLevel)
                .setConverter(gsonConverter)
                .setClient(okClient)
                .setRequestInterceptor(requestInterceptor)
                .setErrorHandler(errorHandler)
                .build()
                .create(LotteryApiService.class);
    }

    @Provides
    @Singleton
    RequestInterceptor provideRequestInterceptor(
            @UserAgent final String userAgent) {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade requestFacade) {
                requestFacade.addQueryParam("seed", "panavtec");
                requestFacade.addHeader("User-Agent", userAgent);
            }
        };
    }

    @Provides
    @Singleton
    ErrorHandler provideApiErrorHandler() {
        return new ErrorHandler() {
            @Override
            public Throwable handleError(RetrofitError cause) {
                ApiNetworkError networkError = new ApiNetworkError();
                networkError.setStackTrace(cause.getStackTrace());
                return networkError;
            }
        };
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
    Gson provideGsonParser() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
}