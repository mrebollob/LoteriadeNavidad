package com.mrebollob.loteriadenavidad.presentation.modules.main;

import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;

import java.util.Date;
import java.util.List;

/**
 * @author mrebollob
 */
public interface MainView {

    void showLoading();

    void hideLoading();

    void showLotteryTicketList(List<PresentationLotteryTicket> lotteryTickets);

    void showLotterySummary(float totalBet, float totalWin, float profit);

    void showLastUpdate(Date lastUpdate);

    void showLotteryStatus(int status);

    void showLotteryNotStarted();

    void showGetLotteryTicketsError();

    void showNoLotteryTicketsError();

    void showDeleteLotteryTicketError();

    void showUpdatePrizesError();

    void showLotteryStatusError();
}
