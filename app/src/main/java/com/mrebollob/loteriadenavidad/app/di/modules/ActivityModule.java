package com.mrebollob.loteriadenavidad.app.di.modules;

import android.content.Context;

import com.mrebollob.loteriadenavidad.app.di.qualifiers.Activity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Context mContext;

    public ActivityModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Activity
    Context provideActivityContext() {
        return mContext;
    }
}
