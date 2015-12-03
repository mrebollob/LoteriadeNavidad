package com.mrebollob.loteriadenavidad.presentation.modules.main;

import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;

import java.util.List;

/**
 * @author mrebollob
 */
public interface MainView {

    void refreshLotteryTicketsList(List<PresentationLotteryTicket> lotteryTickets);

    void refreshUi();

    void showGetLotteryTicketsError();

    void showDeleteLotteryTicketError();

    void showSortLotteryTicketsError();
}
