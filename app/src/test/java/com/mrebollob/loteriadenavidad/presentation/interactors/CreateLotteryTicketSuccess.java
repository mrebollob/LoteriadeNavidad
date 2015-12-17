package com.mrebollob.loteriadenavidad.presentation.interactors;

import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CreateLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

/**
 * @author mrebollob
 */
public class CreateLotteryTicketSuccess extends CreateLotteryTicket {

    private Callback mCallback;

    public CreateLotteryTicketSuccess(InteractorExecutor interactorExecutor,
                                      MainThread mainThread,
                                      LotteryRepository lotteryRepository) {
        super(interactorExecutor, mainThread, lotteryRepository);
    }

    @Override
    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    public void execute() {
        if (mCallback != null)
            mCallback.onSuccess();
    }
}
