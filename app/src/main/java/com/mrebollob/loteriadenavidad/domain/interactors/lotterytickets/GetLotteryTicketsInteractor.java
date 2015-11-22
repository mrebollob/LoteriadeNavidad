package com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets;

import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.Interactor;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.GetLotteryTicketsException;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

/**
 * @author mrebollob
 */
public class GetLotteryTicketsInteractor implements Interactor {

    private final InteractorExecutor interactorExecutor;
    private final MainThread mainThread;
    private final LotteryRepository lotteryRepository;
    private Callback mCallback;

    public GetLotteryTicketsInteractor(InteractorExecutor interactorExecutor, MainThread mainThread,
                                       LotteryRepository lotteryRepository) {
        this.interactorExecutor = interactorExecutor;
        this.mainThread = mainThread;
        this.lotteryRepository = lotteryRepository;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    public void run() {
        try {
            lotteryRepository.getLotteryTickets();
            notifySuccess();
        } catch (GetLotteryTicketsException e) {
            notifyError(e);
        }
    }

    @Override
    public void execute() {
        interactorExecutor.run(this);
    }

    private void notifySuccess() {
        mainThread.execute(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess();
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

        void onSuccess();

        void onError(Throwable error);
    }
}
