package com.mrebollob.loteriadenavidad.presentation.interactors;

import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.UpdateLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

/**
 * @author mrebollob
 */
public class UpdateLotteryTicketSuccess extends UpdateLotteryTicket {

    private Callback mCallback;

    public UpdateLotteryTicketSuccess(InteractorExecutor interactorExecutor,
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
        if (mCallback != null) {
            mCallback.onSuccess();
        }
    }
}
