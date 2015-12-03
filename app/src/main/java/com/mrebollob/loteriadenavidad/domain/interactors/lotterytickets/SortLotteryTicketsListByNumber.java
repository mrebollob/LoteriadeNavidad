package com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets;

import com.mrebollob.loteriadenavidad.domain.comparator.LotteryTicketNumberComparator;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.executor.InteractorExecutor;
import com.mrebollob.loteriadenavidad.domain.executor.MainThread;
import com.mrebollob.loteriadenavidad.domain.interactors.Interactor;

import java.util.Collections;
import java.util.List;

/**
 * @author mrebollob
 */
public class SortLotteryTicketsListByNumber implements Interactor {

    private final InteractorExecutor interactorExecutor;
    private final MainThread mainThread;
    private final LotteryTicketNumberComparator lotteryTicketNumberComparator;
    private Callback mCallback;
    private List<LotteryTicket> mLotteryTickets;

    public SortLotteryTicketsListByNumber(InteractorExecutor interactorExecutor, MainThread mainThread,
                                          LotteryTicketNumberComparator lotteryTicketNumberComparator) {
        this.interactorExecutor = interactorExecutor;
        this.mainThread = mainThread;
        this.lotteryTicketNumberComparator = lotteryTicketNumberComparator;
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
            Collections.sort(mLotteryTickets, lotteryTicketNumberComparator);
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
