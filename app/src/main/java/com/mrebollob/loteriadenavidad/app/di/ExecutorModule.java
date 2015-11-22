package com.mrebollob.loteriadenavidad.app.di;

import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.app.executor.MainThreadImp;
import com.mrebollob.loteriadenavidad.app.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */
@Module(
        complete = false,
        library = true)
public final class ExecutorModule {

    @Provides
    @Singleton
    InteractorExecutor providesInteractroExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    @Singleton
    MainThread providesMainThreadExecutor() {
        return new MainThreadImp();
    }
}