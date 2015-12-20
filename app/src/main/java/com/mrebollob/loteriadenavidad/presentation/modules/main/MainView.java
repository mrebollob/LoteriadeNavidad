package com.mrebollob.loteriadenavidad.presentation.modules.main;

import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;

import java.util.Date;
import java.util.List;

/**
 * @author mrebollob
 */
public interface MainView {

    void refreshLotteryTicketsList(List<PresentationLotteryTicket> lotteryTickets);

    void showLastUpdate(Date lastUpdate);

    void showLotteryStatus(int status);

    void showLotteryNotStarted();

    void showAd();

    void refreshUi();

    void showGetLotteryTicketsError();

    void showNoNumbersError();

    void showDeleteLotteryTicketError();

    void showSortLotteryTicketsError();

    void showUpdatePrizesError();

    void showLotteryStatusError();
}
