package com.mrebollob.loteriadenavidad.app;

import android.app.Application;

import com.mrebollob.loteriadenavidad.app.di.components.AppComponent;
import com.mrebollob.loteriadenavidad.app.di.components.DaggerAppComponent;
import com.mrebollob.loteriadenavidad.app.di.modules.AppModule;

/**
 * @author mrebollob
 */
public class LotteryApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
