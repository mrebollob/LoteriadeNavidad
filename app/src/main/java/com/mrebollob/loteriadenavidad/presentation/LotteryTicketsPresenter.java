package com.mrebollob.loteriadenavidad.presentation;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mrebollob
 */
public class LotteryTicketsPresenter extends Presenter<LotteryTicketsPresenter.View> {

    public LotteryTicketsPresenter() {
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

    private void getLotteryTickets() {

        List<LotteryTicket> lotteryTickets = new ArrayList<>();

        LotteryTicket lotteryTicket = new LotteryTicket("Lottery ticket 1", 0, 1, 2);
        lotteryTickets.add(lotteryTicket);
        lotteryTicket = new LotteryTicket("Lottery ticket 1", 1, 1, 2);
        lotteryTickets.add(lotteryTicket);

        getView().hideLoading();
        getView().showLotteryTickets(lotteryTickets);

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