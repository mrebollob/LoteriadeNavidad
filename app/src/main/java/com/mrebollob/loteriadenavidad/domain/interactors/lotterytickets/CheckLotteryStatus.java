package com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets;

import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.Interactor;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.NetworkException;

/**
 * @author mrebollob
 */
public class CheckLotteryStatus implements Interactor {

    private final InteractorExecutor interactorExecutor;
    private final MainThread mainThread;
    private final LotteryRepository lotteryRepository;
    private Callback mCallback;

    public CheckLotteryStatus(InteractorExecutor interactorExecutor, MainThread mainThread,
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
            notifySuccess(lotteryRepository.checkLotteryStatus());
        } catch (NetworkException e) {
            notifyError(e);
        }
    }

    @Override
    public void execute() {
        interactorExecutor.run(this);
    }

    private void notifySuccess(final int lotteryStatus) {
        mainThread.execute(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(lotteryStatus);
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

        void onSuccess(int lotteryStatus);

        void onError(Throwable error);
    }
}
