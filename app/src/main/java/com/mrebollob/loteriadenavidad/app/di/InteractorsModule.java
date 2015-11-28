package com.mrebollob.loteriadenavidad.app.di;

import com.mrebollob.loteriadenavidad.domain.comparator.LotteryTicketLabelComparator;
import com.mrebollob.loteriadenavidad.domain.comparator.LotteryTicketNumberComparator;
import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CreateLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTickets;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTicketsByType;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.SortLotteryTicketsListByLabel;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.SortLotteryTicketsListByNumber;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.UpdateLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */
@Module(
        complete = false,
        library = true
)
public class InteractorsModule {

    @Provides
    @Singleton
    CreateLotteryTicket provideCreateLotteryTicketInteractor(InteractorExecutor interactorExecutor,
                                                             MainThread mainThread,
                                                             LotteryRepository lotteryRepository) {
        return new CreateLotteryTicket(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    GetLotteryTickets provideGetLotteryTicketsInteractor(InteractorExecutor interactorExecutor,
                                                         MainThread mainThread,
                                                         LotteryRepository lotteryRepository) {
        return new GetLotteryTickets(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    UpdateLotteryTicket provideUpdateLotteryTicketInteractor(InteractorExecutor interactorExecutor,
                                                             MainThread mainThread,
                                                             LotteryRepository lotteryRepository) {
        return new UpdateLotteryTicket(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    DeleteLotteryTicket provideDeleteLotteryTicketInteractor(InteractorExecutor interactorExecutor,
                                                             MainThread mainThread,
                                                             LotteryRepository lotteryRepository) {
        return new DeleteLotteryTicket(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    GetLotteryTicketsByType provideGetLotteryTicketsByTypeInteractor(InteractorExecutor interactorExecutor,
                                                                     MainThread mainThread,
                                                                     LotteryRepository lotteryRepository) {
        return new GetLotteryTicketsByType(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    SortLotteryTicketsListByLabel provideSortLotterryTicketsListByLabelInteractor(
            InteractorExecutor interactorExecutor,
            MainThread mainThread,
            LotteryTicketLabelComparator lotteryTicketLabelComparator) {
        return new SortLotteryTicketsListByLabel(interactorExecutor, mainThread, lotteryTicketLabelComparator);
    }

    @Provides
    @Singleton
    SortLotteryTicketsListByNumber provideSortLotteryTicketsListByNumberInteractor(
            InteractorExecutor interactorExecutor,
            MainThread mainThread,
            LotteryTicketNumberComparator lotteryTicketNumberComparator) {
        return new SortLotteryTicketsListByNumber(interactorExecutor, mainThread, lotteryTicketNumberComparator);
    }
}