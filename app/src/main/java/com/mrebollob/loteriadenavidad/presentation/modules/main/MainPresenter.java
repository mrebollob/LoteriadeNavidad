package com.mrebollob.loteriadenavidad.presentation.modules.main;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryType;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTickets;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTicketsByType;
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
    private final GetLotteryTicketsByType getLotteryTicketsByType;
    private final DeleteLotteryTicket deleteLotteryTicket;
    private final MainView view;
    private final ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper;

    private PresentationLotteryType mLotteryType;

    public MainPresenter(GetLotteryTickets getLotteryTickets,
                         GetLotteryTicketsByType getLotteryTicketsByType,
                         DeleteLotteryTicket deleteLotteryTicket,
                         MainView view,
                         ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper) {
        this.getLotteryTickets = getLotteryTickets;
        this.getLotteryTicketsByType = getLotteryTicketsByType;
        this.deleteLotteryTicket = deleteLotteryTicket;
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

    public void onRefresh() {
        view.refreshUi();
        getLotteryTickets.setCallback(getLotteryTicketsCallback);
        getLotteryTickets.execute();
    }

    public void onRefresh(PresentationLotteryType lotteryType) {
        view.refreshUi();
        this.mLotteryType = lotteryType;
        getLotteryTicketsByType.setCallback(getLotteryTicketsByTypeCallback);
        getLotteryTicketsByType.setType(LotteryType.valueOf(lotteryType.toString()));
        getLotteryTicketsByType.execute();
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
                    view.refreshLotteryTicketsList(lotteryTicketsListMapper.modelToData(lotteryTickets));

                }

                @Override
                public void onError(Throwable error) {
                    view.showGetLotteryTicketsError();
                }
            };

    private final GetLotteryTicketsByType.Callback getLotteryTicketsByTypeCallback =
            new GetLotteryTicketsByType.Callback() {

                @Override
                public void onSuccess(List<LotteryTicket> lotteryTickets) {
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
}
