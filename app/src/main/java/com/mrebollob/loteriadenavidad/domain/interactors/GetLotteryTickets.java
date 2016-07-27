package com.mrebollob.loteriadenavidad.domain.interactors;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * @author mrebollob
 */
public class GetLotteryTickets extends UseCase<List<LotteryTicket>> {

    private final LotteryRepository mRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;

    @Inject
    public GetLotteryTickets(LotteryRepository repository,
                             @Named("ui_thread") Scheduler uiThread,
                             @Named("executor_thread") Scheduler executorThread) {

        mRepository = repository;
        mUiThread = uiThread;
        mExecutorThread = executorThread;
    }

    @Override
    public Observable<List<LotteryTicket>> buildObservable() {
        return mRepository.getLotteryTickets()
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread);
    }

    @Override
    public Observable<List<LotteryTicket>> execute() {
        return super.execute();
    }
}
