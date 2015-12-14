package com.mrebollob.loteriadenavidad.app.di;

import android.app.Application;
import android.os.Build;

import com.mrebollob.loteriadenavidad.app.LoteriaDeNavidadApp;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.ApiLevel;
import com.mrebollob.loteriadenavidad.app.util.AnalyticsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */
@Module(
        includes = {
                DataModule.class, DomainModule.class, PresentationModule.class
        },
        injects = LoteriaDeNavidadApp.class,
        library = true
)
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    AnalyticsManager provideAnalyticsManager() {
        return new AnalyticsManager(app);
    }

    @Provides
    @Singleton
    @ApiLevel
    int provideApiLevel() {
        return Build.VERSION.SDK_INT;
    }
}