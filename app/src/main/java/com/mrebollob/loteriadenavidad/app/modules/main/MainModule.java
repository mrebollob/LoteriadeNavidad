package com.mrebollob.loteriadenavidad.app.modules.main;

import com.mrebollob.loteriadenavidad.app.di.ActivityModule;

import dagger.Module;

/**
 * @author mrebollob
 */
@Module(
        addsTo = ActivityModule.class,
        injects = MainActivity.class)
public class MainModule {

}