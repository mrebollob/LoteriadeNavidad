package com.mrebollob.loteriadenavidad.domain.interactors;

import com.mrebollob.loteriadenavidad.domain.executor.PostExecutionThread;
import com.mrebollob.loteriadenavidad.domain.executor.ThreadExecutor;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author mrebollob
 */
public class GetLotteryTickets extends UseCase {

    private final LotteryRepository mLotteryRepository;

    @Inject
    public GetLotteryTickets(LotteryRepository userRepository, ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mLotteryRepository = userRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.mLotteryRepository.lotteryTickets();
    }
}
