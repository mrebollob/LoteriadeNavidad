package com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets;

import com.mrebollob.loteriadenavidad.domain.comparator.LotteryTicketLabelComparator;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.Interactor;

import java.util.Collections;
import java.util.List;

/**
 * @author mrebollob
 */
public class SortLotteryTicketsListByLabel implements Interactor {

    private final InteractorExecutor interactorExecutor;
    private final MainThread mainThread;
    private final LotteryTicketLabelComparator lotteryTicketLabelComparator;
    private Callback mCallback;
    private List<LotteryTicket> mLotteryTickets;

    public SortLotteryTicketsListByLabel(InteractorExecutor interactorExecutor, MainThread mainThread,
                                         LotteryTicketLabelComparator lotteryTicketLabelComparator) {
        this.interactorExecutor = interactorExecutor;
        this.mainThread = mainThread;
        this.lotteryTicketLabelComparator = lotteryTicketLabelComparator;
    }

    public void setData(List<LotteryTicket> lotteryTickets) {
        this.mLotteryTickets = lotteryTickets;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    public void run() {
        try {
            Collections.sort(mLotteryTickets, lotteryTicketLabelComparator);
            notifySuccess(mLotteryTickets);
        } catch (Exception e) {
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
