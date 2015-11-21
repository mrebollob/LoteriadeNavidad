package com.mrebollob.loteriadenavidad.app.di;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */

@Module(addsTo = AppModule.class, library = true)
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    ActionBar provideActionBar() {
        return activity.getSupportActionBar();
    }

    @Provides
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    Window provideWindow() {
        return activity.getWindow();
    }
}