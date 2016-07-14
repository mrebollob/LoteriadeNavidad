package com.mrebollob.loteriadenavidad.app.di.modules;

import com.mrebollob.loteriadenavidad.app.LotteryApplication;
import com.mrebollob.loteriadenavidad.data.repository.LotteryRepositoryImp;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class AppModule {
    private final LotteryApplication mLotteryApplication;

    public AppModule(LotteryApplication lotteryApplication) {
        this.mLotteryApplication = lotteryApplication;
    }

    @Provides
    @Singleton
    LotteryApplication provideLotteryApplicationContext() {
        return mLotteryApplication;
    }


    @Provides
    @Singleton
    LotteryRepository provideLotteryRepositoryImp(LotteryRepositoryImp lotteryRepository) {
        return lotteryRepository;
    }

    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.newThread();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}
