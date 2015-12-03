package com.mrebollob.loteriadenavidad.presentation.modules.main;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryType;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTickets;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.SortLotteryTicketsListByLabel;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.SortLotteryTicketsListByNumber;
import com.mrebollob.loteriadenavidad.presentation.Presenter;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryType;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.base.ListMapper;

import java.util.List;

/**
 * @author mrebollob
 */
public class MainPresenter extends Presenter {

    private final GetLotteryTickets getLotteryTickets;
    private final DeleteLotteryTicket deleteLotteryTicket;
    private final SortLotteryTicketsListByLabel sortLotteryTicketsListByLabel;
    private final SortLotteryTicketsListByNumber sortLotteryTicketsListByNumber;
    private final MainView view;
    private final ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper;

    private PresentationLotteryType mLotteryType;
    private List<LotteryTicket> mLotteryTickets;
    private int sortType;

    public MainPresenter(GetLotteryTickets getLotteryTickets,
                         DeleteLotteryTicket deleteLotteryTicket,
                         SortLotteryTicketsListByLabel sortLotteryTicketsListByLabel,
                         SortLotteryTicketsListByNumber sortLotteryTicketsListByNumber,
                         MainView view,
                         ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper) {
        this.getLotteryTickets = getLotteryTickets;
        this.deleteLotteryTicket = deleteLotteryTicket;
        this.sortLotteryTicketsListByLabel = sortLotteryTicketsListByLabel;
        this.sortLotteryTicketsListByNumber = sortLotteryTicketsListByNumber;
        this.view = view;
        this.lotteryTicketsListMapper = lotteryTicketsListMapper;
    }

    @Override
    public void onResume() {
        if (mLotteryType == null)
            onRefresh();
        else
            onRefresh(mLotteryType);
    }

    @Override
    public void onPause() {
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
        this.mLotteryType = null;
        getLotteryTickets.setCallback(getLotteryTicketsCallback);
        getLotteryTickets.setType(LotteryType.ALL);
        getLotteryTickets.execute();
    }

    public void onRefresh(PresentationLotteryType lotteryType) {
        view.refreshUi();
        this.mLotteryType = lotteryType;
        getLotteryTickets.setCallback(getLotteryTicketsCallback);
        getLotteryTickets.setType(LotteryType.valueOf(lotteryType.toString()));
        getLotteryTickets.execute();
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
                    onRefresh();
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
}
