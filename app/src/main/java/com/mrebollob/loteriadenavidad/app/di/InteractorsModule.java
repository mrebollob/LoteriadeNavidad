package com.mrebollob.loteriadenavidad.app.di;

import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CreateLotteryTicketInteractor;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicketInteractor;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTicketsInteractor;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.UpdateLotteryTicketInteractor;
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
    CreateLotteryTicketInteractor provideCreateLotteryTicketInteractor(InteractorExecutor interactorExecutor,
                                                                       MainThread mainThread,
                                                                       LotteryRepository lotteryRepository) {
        return new CreateLotteryTicketInteractor(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    GetLotteryTicketsInteractor provideGetLotteryTicketsInteractor(InteractorExecutor interactorExecutor,
                                                                   MainThread mainThread,
                                                                   LotteryRepository lotteryRepository) {
        return new GetLotteryTicketsInteractor(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    UpdateLotteryTicketInteractor provideUpdateLotteryTicketInteractor(InteractorExecutor interactorExecutor,
                                                                       MainThread mainThread,
                                                                       LotteryRepository lotteryRepository) {
        return new UpdateLotteryTicketInteractor(interactorExecutor, mainThread, lotteryRepository);
    }

    @Provides
    @Singleton
    DeleteLotteryTicketInteractor provideDeleteLotteryTicketInteractor(InteractorExecutor interactorExecutor,
                                                                       MainThread mainThread,
                                                                       LotteryRepository lotteryRepository) {
        return new DeleteLotteryTicketInteractor(interactorExecutor, mainThread, lotteryRepository);
    }
}