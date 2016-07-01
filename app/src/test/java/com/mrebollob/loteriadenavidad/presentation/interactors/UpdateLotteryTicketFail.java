package com.mrebollob.loteriadenavidad.presentation.interactors;

import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.UpdateLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.CreateLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

/**
 * @author mrebollob
 */
public class UpdateLotteryTicketFail extends UpdateLotteryTicket {

    private Callback mCallback;

    public UpdateLotteryTicketFail(InteractorExecutor interactorExecutor,
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
            mCallback.onError(new CreateLotteryTicketException());
        }
    }
}
