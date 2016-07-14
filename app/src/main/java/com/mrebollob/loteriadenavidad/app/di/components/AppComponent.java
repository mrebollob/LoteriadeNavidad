package com.mrebollob.loteriadenavidad.app.di.components;

import com.mrebollob.loteriadenavidad.app.LotteryApplication;
import com.mrebollob.loteriadenavidad.app.di.modules.AppModule;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    LotteryApplication app();

    LotteryRepository dataRepository();

    @Named("ui_thread")
    Scheduler uiThread();

    @Named("executor_thread")
    Scheduler executorThread();
}
