package com.mrebollob.loteriadenavidad.app.di;

import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CheckLotteryStatus;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CheckLotteryTicketsPrize;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CreateLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLastUpdatedTime;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTickets;
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
    CheckLotteryTicketsPrize provideCheckLotteryTicketsPrize(InteractorExecutor interactorExecutor,
                                                             MainThread mainThread,
                                                             LotteryRepository lotteryRepository) {
        return new CheckLotteryTicketsPrize(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    GetLastUpdatedTime provideGetLastUpdatedTime(InteractorExecutor interactorExecutor,
                                                 MainThread mainThread,
                                                 LotteryRepository lotteryRepository) {
        return new GetLastUpdatedTime(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    CheckLotteryStatus provideCheckLotteryStatus(InteractorExecutor interactorExecutor,
                                                 MainThread mainThread,
                                                 LotteryRepository lotteryRepository) {
        return new CheckLotteryStatus(interactorExecutor, mainThread, lotteryRepository);
    }
}