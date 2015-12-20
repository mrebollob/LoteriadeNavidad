package com.mrebollob.loteriadenavidad.presentation.modules.lotteryticketform;

import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CreateLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.UpdateLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.Presenter;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.PresentationLotteryTicketMapper;

/**
 * @author mrebollob
 */
public class LotteryTicketFormPresenter extends Presenter {

    private final CreateLotteryTicket createLotteryTicket;
    private final UpdateLotteryTicket updateLotteryTicket;
    private final LotteryTicketFormView view;
    private final PresentationLotteryTicketMapper lotteryTicketMapper;

    public LotteryTicketFormPresenter(CreateLotteryTicket createLotteryTicket,
                                     UpdateLotteryTicket updateLotteryTicket,
                                     LotteryTicketFormView view,
                                     PresentationLotteryTicketMapper lotteryTicketMapper) {
        this.createLotteryTicket = createLotteryTicket;
        this.updateLotteryTicket = updateLotteryTicket;
        this.view = view;
        this.lotteryTicketMapper = lotteryTicketMapper;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    public void createLotteryTicket(PresentationLotteryTicket LotteryTicket) {
        createLotteryTicket.setData(lotteryTicketMapper.dataToModel(LotteryTicket));
        createLotteryTicket.setCallback(createLotteryTicketCallback);
        createLotteryTicket.execute();
    }

    public void updateLotteryTicket(PresentationLotteryTicket LotteryTicket) {
        updateLotteryTicket.setData(lotteryTicketMapper.dataToModel(LotteryTicket));
        updateLotteryTicket.setCallback(updateLotteryTicketCallback);
        updateLotteryTicket.execute();
    }

    private final CreateLotteryTicket.Callback createLotteryTicketCallback =
            new CreateLotteryTicket.Callback() {

                @Override
                public void onSuccess() {
                    view.showCreateOrUpdateLotteryTicketSuccess();
                }

                @Override
                public void onError(Throwable error) {
                    view.showCreateLotteryTicketError();
                }
            };

    private final UpdateLotteryTicket.Callback updateLotteryTicketCallback =
            new UpdateLotteryTicket.Callback() {

                @Override
                public void onSuccess() {
                    view.showCreateOrUpdateLotteryTicketSuccess();
                }

                @Override
                public void onError(Throwable error) {
                    view.showUpdateLotteryTicketError();
                }
            };
}
