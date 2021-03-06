package com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryType;
import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.Interactor;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.GetLotteryTicketsException;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

import java.util.List;

/**
 * @author mrebollob
 */
public class GetLotteryTickets implements Interactor {

    private final InteractorExecutor interactorExecutor;
    private final MainThread mainThread;
    private final LotteryRepository lotteryRepository;
    private Callback mCallback;
    private LotteryType mLotteryType;

    public GetLotteryTickets(InteractorExecutor interactorExecutor, MainThread mainThread,
                             LotteryRepository lotteryRepository) {
        this.interactorExecutor = interactorExecutor;
        this.mainThread = mainThread;
        this.lotteryRepository = lotteryRepository;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void setType(LotteryType lotteryType) {
        this.mLotteryType = lotteryType;
    }

    @Override
    public void run() {
        try {
            switch (mLotteryType) {
                case CHRISTMAS:
                    notifySuccess(lotteryRepository.getChristmasLotteryTickets());
                    break;
                case CHILD:
                    notifySuccess(lotteryRepository.getChildLotteryTickets());
                    break;
                default:
                    notifySuccess(lotteryRepository.getLotteryTickets());
                    break;
            }
        } catch (GetLotteryTicketsException e) {
            notifyError(e);
        }
    }

    @Override
    public void execute() {
        interactorExecutor.run(this);
    }

    private void notifySuccess(final List<LotteryTicket> lotteryTickets) {
        mainThread.execute(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(lotteryTickets);
            }
        });
    }

    private void notifyError(final Throwable error) {
        mainThread.execute(new Runnable() {
            @Override
            public void run() {
                mCallback.onError(error);
            }
        });
    }

    public interface Callback {

        void onSuccess(List<LotteryTicket> lotteryTickets);

        void onError(Throwable error);
    }
}
