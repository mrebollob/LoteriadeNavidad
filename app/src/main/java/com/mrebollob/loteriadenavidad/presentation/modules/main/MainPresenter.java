package com.mrebollob.loteriadenavidad.presentation.modules.main;

import android.util.Log;

import com.mrebollob.loteriadenavidad.app.navigator.Navigator;
import com.mrebollob.loteriadenavidad.domain.comparator.LotteryTicketLabelComparator;
import com.mrebollob.loteriadenavidad.domain.comparator.LotteryTicketNumberComparator;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryType;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CheckLotteryStatus;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CheckLotteryTicketsPrize;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLastUpdatedTime;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTickets;
import com.mrebollob.loteriadenavidad.presentation.Presenter;
import com.mrebollob.loteriadenavidad.presentation.model.LotterySummary;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryType;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.base.ListMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author mrebollob
 */
public class MainPresenter extends Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private final Navigator navigator;
    private final GetLotteryTickets getLotteryTickets;
    private final DeleteLotteryTicket deleteLotteryTicket;
    private final CheckLotteryTicketsPrize checkLotteryTicketsPrize;
    private final GetLastUpdatedTime getLastUpdatedTime;
    private final CheckLotteryStatus checkLotteryStatus;
    private final MainView view;
    private final ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper;

    private List<LotteryTicket> lotteryTickets = new ArrayList<>();
    private PresentationLotteryType lotteryType = PresentationLotteryType.ALL;
    private int numberOfChecks = 0;

    public MainPresenter(Navigator navigator, GetLotteryTickets getLotteryTickets,
                         DeleteLotteryTicket deleteLotteryTicket,
                         CheckLotteryTicketsPrize checkLotteryTicketsPrize,
                         GetLastUpdatedTime getLastUpdatedTime,
                         CheckLotteryStatus checkLotteryStatus,
                         MainView view,
                         ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper) {
        this.navigator = navigator;
        this.getLotteryTickets = getLotteryTickets;
        this.deleteLotteryTicket = deleteLotteryTicket;
        this.checkLotteryTicketsPrize = checkLotteryTicketsPrize;
        this.getLastUpdatedTime = getLastUpdatedTime;
        this.checkLotteryStatus = checkLotteryStatus;
        this.view = view;
        this.lotteryTicketsListMapper = lotteryTicketsListMapper;
    }

    public void setlotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    private void getLotteryTickets() {
        view.showLoading();
        getLotteryTickets.setCallback(getLotteryTicketsCallback);
        getLotteryTickets.setType(LotteryType.valueOf(lotteryType.toString()));
        getLotteryTickets.execute();
    }

    private void updateLotteryTicketsView() {
        view.showLotteryTicketList(lotteryTicketsListMapper.modelToData(lotteryTickets));

        LotterySummary lotterySummary = new LotterySummary(lotteryTickets);
        view.showLotterySummary(lotterySummary.getTotalBet(),
                lotterySummary.getTotalWin(), lotterySummary.getProfit());
    }

    private void getLastUpdatedTime() {
        getLastUpdatedTime.setCallback(getLastUpdatedTimeCallback);
        getLastUpdatedTime.execute();
    }

    private void checkLotteryTicketsPrize() {
        checkLotteryTicketsPrize.setData(lotteryTickets);
        checkLotteryTicketsPrize.setCallback(checkLotteryTicketsPrizeCallback);
        checkLotteryTicketsPrize.execute();
    }

    private void checkLotteryStatusAndTicketsPrizes() {
        checkLotteryStatus.setCallback(checkLotteryStatusCallback);
        checkLotteryStatus.execute();
    }

    @Override
    public void onResume() {
        getLotteryTickets();
        getLastUpdatedTime();
    }

    @Override
    public void onPause() {
    }

    public void onAddLotteryTicketClick() {
        navigator.goToAddLotteryTicket();
    }

    public void onEditLotteryTicketClick(PresentationLotteryTicket lotteryTicket) {
        navigator.goToEditLotteryTicket(lotteryTicket);
    }

    public void onDeleteLotteryTicketClick(int lotteryTicketId) {
        deleteLotteryTicket.setData(lotteryTicketId);
        deleteLotteryTicket.setCallback(deleteLotteryTicketCallback);
        deleteLotteryTicket.execute();
    }

    public void onSelectLotteryType(PresentationLotteryType lotteryType) {
        this.lotteryType = lotteryType;
        getLotteryTickets();
    }

    public void sortLotteryTicketsByLabel() {
        Collections.sort(lotteryTickets, new LotteryTicketLabelComparator());
        updateLotteryTicketsView();
    }

    public void sortLotteryTicketsByNumber() {
        Collections.sort(lotteryTickets, new LotteryTicketNumberComparator());
        updateLotteryTicketsView();
    }

    public void onAboutClick() {
        navigator.goToAbout();
    }

    public void onRefresh() {
        view.showLoading();
        if (lotteryTickets.isEmpty()) {
            view.showNoLotteryTicketsError();
        } else {
            checkLotteryStatusAndTicketsPrizes();
        }
    }

    public void onAdClosed() {

    }

    private final GetLotteryTickets.Callback getLotteryTicketsCallback =
            new GetLotteryTickets.Callback() {

                @Override
                public void onSuccess(List<LotteryTicket> lotteryTickets) {
                    view.hideLoading();
                    setlotteryTickets(lotteryTickets);
                    updateLotteryTicketsView();
                    if (numberOfChecks == 0) {
                        checkLotteryStatusAndTicketsPrizes();
                    }
                }

                @Override
                public void onError(Throwable error) {
                    Log.e(TAG, "Error getting lottery tickets", error);
                    view.hideLoading();
                    view.showGetLotteryTicketsError();
                }
            };

    private final DeleteLotteryTicket.Callback deleteLotteryTicketCallback =
            new DeleteLotteryTicket.Callback() {

                @Override
                public void onSuccess() {
                    getLotteryTickets();
                }

                @Override
                public void onError(Throwable error) {
                    Log.e(TAG, "Error removing lottery ticket", error);
                    view.hideLoading();
                    view.showDeleteLotteryTicketError();
                }
            };

    private final CheckLotteryTicketsPrize.Callback checkLotteryTicketsPrizeCallback =
            new CheckLotteryTicketsPrize.Callback() {

                @Override
                public void onSuccess(List<LotteryTicket> lotteryTickets) {
                    view.hideLoading();
                    numberOfChecks += 1;
                    setlotteryTickets(lotteryTickets);
                    updateLotteryTicketsView();
                    getLastUpdatedTime();
                }

                @Override
                public void onError(Throwable error) {
                    Log.e(TAG, "Error checking lottery prize", error);
                    view.hideLoading();
                    view.showUpdatePrizesError();
                }
            };

    private final GetLastUpdatedTime.Callback getLastUpdatedTimeCallback =
            new GetLastUpdatedTime.Callback() {
                @Override
                public void onSuccess(Date updateTime) {
                    view.showLastUpdate(updateTime);
                }

                @Override
                public void onError(Throwable error) {
                    Log.e(TAG, "Error getting last updated time", error);
                }
            };

    private final CheckLotteryStatus.Callback checkLotteryStatusCallback =
            new CheckLotteryStatus.Callback() {

                @Override
                public void onSuccess(int lotteryStatus) {
                    view.showLotteryStatus(lotteryStatus);
                    if (lotteryStatus > 0) {
                        checkLotteryTicketsPrize();
                    } else {
                        view.showLotteryNotStarted();
                    }
                }

                @Override
                public void onError(Throwable error) {
                    Log.e(TAG, "Error checking lottery status", error);
                    view.hideLoading();
                    view.showLotteryStatusError();
                }
            };
}
