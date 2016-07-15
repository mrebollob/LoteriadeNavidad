package com.mrebollob.loteriadenavidad.presentation;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.GetLotteryTickets;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author mrebollob
 */
public class LotteryTicketsPresenter extends Presenter<LotteryTicketsPresenter.View> {

    private final GetLotteryTickets mGetLotteryTickets;
    private Subscription mLotteryTicketsSubscription;

    @Inject
    public LotteryTicketsPresenter(GetLotteryTickets getLotteryTickets) {
        this.mGetLotteryTickets = getLotteryTickets;
    }

    @Override
    public void initialize() {
        super.initialize();
        getLotteryTickets();
    }

    @Override
    public void update() {
        super.update();
        getLotteryTickets();
    }

    @Override
    public void finalize() {
        super.finalize();
        mLotteryTicketsSubscription.unsubscribe();
    }

    private void getLotteryTickets() {
        mLotteryTicketsSubscription = mGetLotteryTickets.execute().subscribe(lotteryTickets -> {
            getView().hideLoading();
            if (lotteryTickets.isEmpty()) {
                getView().showEmptyCase();
            } else {
                getView().showLotteryTickets(lotteryTickets);
            }
        }, throwable -> {
            getView().hideLoading();
        });
    }

    public void onLotteryTicketClicked(LotteryTicket lotteryTicket) {
        getView().openLotteryTicketScreen(lotteryTicket);
    }

    public interface View extends Presenter.View {

        void showLotteryTickets(List<LotteryTicket> lotteryTickets);

        void showEmptyCase();

        void openLotteryTicketScreen(LotteryTicket lotteryTicket);
    }
}