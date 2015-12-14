package com.mrebollob.loteriadenavidad.app;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.mrebollob.loteriadenavidad.app.di.AppModule;

import dagger.ObjectGraph;
import io.fabric.sdk.android.Fabric;

/**
 * @author mrebollob
 */
public class LoteriaDeNavidadApp extends Application {

    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        initObjectGraph();
    }

    private void initObjectGraph() {
        objectGraph = ObjectGraph.create(new AppModule(this));
        inject(this);
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public static LoteriaDeNavidadApp get(Context context) {
        return (LoteriaDeNavidadApp) context.getApplicationContext();
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}