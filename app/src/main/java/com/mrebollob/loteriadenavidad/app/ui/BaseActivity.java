package com.mrebollob.loteriadenavidad.app.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mrebollob.loteriadenavidad.app.LoteriaDeNavidadApp;
import com.mrebollob.loteriadenavidad.app.di.ActivityModule;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

/**
 * @author mrebollob
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityGraph = new ActivityInjector(this).createGraph(getModules());
        super.onCreate(savedInstanceState);

        int layoutId = onCreateViewId();
        if (layoutId != 0) {
            setContentView(layoutId);
            ButterKnife.bind(this);
        }
    }

    public int onCreateViewId() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        activityGraph = null;
        super.onDestroy();
    }

    public ObjectGraph getActivityGraph() {
        return activityGraph;
    }

    public void inject(Object obj) {
        if (activityGraph != null) {
            activityGraph.inject(obj);
        }
    }

    protected List<Object> getModules() {
        return new ArrayList<>();
    }

    static class ActivityInjector {

        private AppCompatActivity activity;

        public ActivityInjector(AppCompatActivity activity) {
            this.activity = activity;
        }

        public ObjectGraph createGraph() {
            return createGraph(new ArrayList<>());
        }

        public ObjectGraph createGraph(List<Object> modules) {
            LoteriaDeNavidadApp app = LoteriaDeNavidadApp.get(activity);
            ObjectGraph graph = app.getObjectGraph().plus(getCombinedModules(modules).toArray());
            graph.inject(activity);
            return graph;
        }

        private List<Object> getCombinedModules(List<Object> modules) {
            List<Object> combined = new ArrayList<>(modules);
            combined.add(new ActivityModule(activity));
            return combined;
        }
    }
}