package com.mrebollob.loteriadenavidad.presentation.modules.main;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicketInteractor;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTicketsInteractor;
import com.mrebollob.loteriadenavidad.presentation.Presenter;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.base.ListMapper;

import java.util.List;

/**
 * @author mrebollob
 */
public class MainPresenter extends Presenter {

    private final GetLotteryTicketsInteractor getLotteryTicketsInteractor;
    private final DeleteLotteryTicketInteractor deleteLotteryTicketInteractor;
    private final MainView view;
    private final ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper;

    public MainPresenter(GetLotteryTicketsInteractor getLotteryTicketsInteractor,
                         DeleteLotteryTicketInteractor deleteLotteryTicketInteractor,
                         MainView view,
                         ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper) {
        this.getLotteryTicketsInteractor = getLotteryTicketsInteractor;
        this.deleteLotteryTicketInteractor = deleteLotteryTicketInteractor;
        this.view = view;
        this.lotteryTicketsListMapper = lotteryTicketsListMapper;
    }

    @Override
    public void onResume() {
        onRefresh();
    }

    @Override
    public void onPause() {
    }

    public void onRefresh() {
        view.refreshUi();
        getLotteryTicketsInteractor.setCallback(getLotteryTicketsCallback);
        getLotteryTicketsInteractor.execute();
    }

    public void deleteLotteryTicket(int lotteryTicketId) {
        deleteLotteryTicketInteractor.setData(lotteryTicketId);
        deleteLotteryTicketInteractor.setCallback(deleteLotteryTicketCallback);
        deleteLotteryTicketInteractor.execute();
    }

    private final GetLotteryTicketsInteractor.Callback getLotteryTicketsCallback =
            new GetLotteryTicketsInteractor.Callback() {

                @Override
                public void onSuccess(List<LotteryTicket> lotteryTickets) {


                    LotteryTicket lotteryTicket = new LotteryTicket();
                    lotteryTicket.setNumber(2134);


                    lotteryTickets.add(lotteryTicket);


                    view.refreshLotteryTicketsList(lotteryTicketsListMapper.modelToData(lotteryTickets));

                }

                @Override
                public void onError(Throwable error) {
                    view.showGetLotteryTicketsError();
                }
            };

    private final DeleteLotteryTicketInteractor.Callback deleteLotteryTicketCallback =
            new DeleteLotteryTicketInteractor.Callback() {

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
