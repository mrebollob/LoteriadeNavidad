package com.mrebollob.loteriadenavidad.app.di.components;

import android.content.Context;

import com.mrebollob.loteriadenavidad.app.di.modules.ActivityModule;
import com.mrebollob.loteriadenavidad.app.di.qualifiers.Activity;
import com.mrebollob.loteriadenavidad.app.modules.main.MainActivity;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface LotteryComponent extends ActivityComponent {
    void inject(MainActivity activity);

    Context activityContext();
}
