package com.mrebollob.loteriadenavidad.app.di;

import dagger.Module;

/**
 * @author mrebollob
 */

@Module(
        includes = {
                InteractorsModule.class, ExecutorModule.class, RepositoryModule.class
        },
        complete = false,
        library = true)
public class DomainModule {

}