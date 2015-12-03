package com.mrebollob.loteriadenavidad.app.executor;

import android.os.Handler;
import android.os.Looper;

import com.mrebollob.loteriadenavidad.domain.executor.MainThread;

/**
 * @author mrebollob
 */
public class MainThreadImp implements MainThread {

    private Handler handler;

    public MainThreadImp() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}