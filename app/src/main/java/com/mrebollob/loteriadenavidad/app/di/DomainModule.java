package com.mrebollob.loteriadenavidad.app.di;

import com.mrebollob.loteriadenavidad.domain.comparator.LotteryTicketLabelComparator;
import com.mrebollob.loteriadenavidad.domain.comparator.LotteryTicketNumberComparator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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

    @Provides
    @Singleton
    LotteryTicketLabelComparator provideLotteryTicketLabelComparator() {
        return new LotteryTicketLabelComparator();
    }

    @Provides
    @Singleton
    LotteryTicketNumberComparator provideLotteryTicketNumberComparator() {
        return new LotteryTicketNumberComparator();
    }
}