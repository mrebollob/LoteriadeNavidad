package com.mrebollob.loteriadenavidad.presentation.modules.main;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryType;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CheckLotteryStatus;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CheckLotteryTicketsPrize;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLastUpdatedTime;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTickets;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.SortLotteryTicketsListByLabel;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.SortLotteryTicketsListByNumber;
import com.mrebollob.loteriadenavidad.presentation.Presenter;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryType;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.base.ListMapper;

import java.util.Date;
import java.util.List;

/**
 * @author mrebollob
 */
public class MainPresenter extends Presenter {

    private final GetLotteryTickets getLotteryTickets;
    private final DeleteLotteryTicket deleteLotteryTicket;
    private final SortLotteryTicketsListByLabel sortLotteryTicketsListByLabel;
    private final SortLotteryTicketsListByNumber sortLotteryTicketsListByNumber;
    private final CheckLotteryTicketsPrize checkLotteryTicketsPrize;
    private final GetLastUpdatedTime getLastUpdatedTime;
    private final CheckLotteryStatus checkLotteryStatus;
    private final MainView view;
    private final ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper;

    private PresentationLotteryType mLotteryType;
    private List<LotteryTicket> mLotteryTickets;
    private int sortType;

    public MainPresenter(GetLotteryTickets getLotteryTickets,
                         DeleteLotteryTicket deleteLotteryTicket,
                         SortLotteryTicketsListByLabel sortLotteryTicketsListByLabel,
                         SortLotteryTicketsListByNumber sortLotteryTicketsListByNumber,
                         CheckLotteryTicketsPrize checkLotteryTicketsPrize,
                         GetLastUpdatedTime getLastUpdatedTime,
                         CheckLotteryStatus checkLotteryStatus,
                         MainView view,
                         ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper) {
        this.getLotteryTickets = getLotteryTickets;
        this.deleteLotteryTicket = deleteLotteryTicket;
        this.sortLotteryTicketsListByLabel = sortLotteryTicketsListByLabel;
        this.sortLotteryTicketsListByNumber = sortLotteryTicketsListByNumber;
        this.checkLotteryTicketsPrize = checkLotteryTicketsPrize;
        this.getLastUpdatedTime = getLastUpdatedTime;
        this.checkLotteryStatus = checkLotteryStatus;
        this.view = view;
        this.lotteryTicketsListMapper = lotteryTicketsListMapper;
    }

    @Override
    public void onResume() {
        view.refreshUi();
        getLotteryTickets.setCallback(getLotteryTicketsCallback);
        getLotteryTickets.setType(LotteryType.ALL);
        getLotteryTickets.execute();

        getLastUpdatedTime.setCallback(getLastUpdatedTimeCallback);
        getLastUpdatedTime.execute();
    }

    @Override
    public void onPause() {
    }

    public void onSelectLotteryType(PresentationLotteryType lotteryType) {
        view.refreshUi();
        this.mLotteryType = lotteryType;
        getLotteryTickets.setCallback(getLotteryTicketsCallback);
        getLotteryTickets.setType(LotteryType.valueOf(mLotteryType.toString()));
        getLotteryTickets.execute();
    }

    public void sortLotteryTickets() {
        if (sortType == 0) {
            sortLotteryTicketsListByLabel.setData(mLotteryTickets);
            sortLotteryTicketsListByLabel.setCallback(sortLotteryTicketsListByLabelCallback);
            sortLotteryTicketsListByLabel.execute();
        } else {
            sortLotteryTicketsListByNumber.setData(mLotteryTickets);
            sortLotteryTicketsListByNumber.setCallback(sortLotteryTicketsListByNumberCallback);
            sortLotteryTicketsListByNumber.execute();
        }
    }

    public void onRefresh() {
        view.refreshUi();

        checkLotteryStatus.setCallback(checkLotteryStatusCallback);
        checkLotteryStatus.execute();
    }

    public void deleteLotteryTicket(int lotteryTicketId) {
        deleteLotteryTicket.setData(lotteryTicketId);
        deleteLotteryTicket.setCallback(deleteLotteryTicketCallback);
        deleteLotteryTicket.execute();
    }

    private final GetLotteryTickets.Callback getLotteryTicketsCallback =
            new GetLotteryTickets.Callback() {

                @Override
                public void onSuccess(List<LotteryTicket> lotteryTickets) {
                    mLotteryTickets = lotteryTickets;
                    view.refreshLotteryTicketsList(lotteryTicketsListMapper.modelToData(lotteryTickets));

                }

                @Override
                public void onError(Throwable error) {
                    view.showGetLotteryTicketsError();
                }
            };

    private final DeleteLotteryTicket.Callback deleteLotteryTicketCallback =
            new DeleteLotteryTicket.Callback() {

                @Override
                public void onSuccess() {
                    getLotteryTickets.setCallback(getLotteryTicketsCallback);
                    getLotteryTickets.setType(LotteryType.valueOf(mLotteryType.toString()));
                    getLotteryTickets.execute();
                }

                @Override
                public void onError(Throwable error) {
                    view.showDeleteLotteryTicketError();
                }
            };

    private final SortLotteryTicketsListByLabel.Callback sortLotteryTicketsListByLabelCallback =
            new SortLotteryTicketsListByLabel.Callback() {
                @Override
                public void onSuccess(List<LotteryTicket> lotteryTickets) {
                    sortType = 1;
                    mLotteryTickets = lotteryTickets;
                    view.refreshLotteryTicketsList(lotteryTicketsListMapper.modelToData(lotteryTickets));
                }

                @Override
                public void onError(Throwable error) {
                    view.showSortLotteryTicketsError();
                }
            };

    private final SortLotteryTicketsListByNumber.Callback sortLotteryTicketsListByNumberCallback =
            new SortLotteryTicketsListByNumber.Callback() {
                @Override
                public void onSuccess(List<LotteryTicket> lotteryTickets) {
                    sortType = 0;
                    mLotteryTickets = lotteryTickets;
                    view.refreshLotteryTicketsList(lotteryTicketsListMapper.modelToData(lotteryTickets));
                }

                @Override
                public void onError(Throwable error) {
                    view.showSortLotteryTicketsError();
                }
            };

    private final CheckLotteryTicketsPrize.Callback checkLotteryTicketsPrizeCallback =
            new CheckLotteryTicketsPrize.Callback() {

                @Override
                public void onSuccess(List<LotteryTicket> lotteryTickets) {
                    mLotteryTickets = lotteryTickets;
                    view.refreshLotteryTicketsList(lotteryTicketsListMapper.modelToData(mLotteryTickets));
                    getLastUpdatedTime.setCallback(getLastUpdatedTimeCallback);
                    getLastUpdatedTime.execute();
                }

                @Override
                public void onError(Throwable error) {
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
                }
            };

    private final CheckLotteryStatus.Callback checkLotteryStatusCallback =
            new CheckLotteryStatus.Callback() {

                @Override
                public void onSuccess(int lotteryStatus) {
                    view.showLotteryStatus(lotteryStatus);

                    if (lotteryStatus > 0) {
                        checkLotteryTicketsPrize.setData(mLotteryTickets);
                        checkLotteryTicketsPrize.setCallback(checkLotteryTicketsPrizeCallback);
                        checkLotteryTicketsPrize.execute();
                    }
                }

                @Override
                public void onError(Throwable error) {
                    view.showUpdatePrizesError();
                }
            };
}
