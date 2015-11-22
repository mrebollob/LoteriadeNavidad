package com.mrebollob.loteriadenavidad.presentation.modules.lotteryticketform;

import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CreateLotteryTicketInteractor;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.UpdateLotteryTicketInteractor;
import com.mrebollob.loteriadenavidad.presentation.Presenter;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.PresentationLotteryTicketMapper;

/**
 * @author mrebollob
 */
public class LotteryTicketFormPresenter extends Presenter {

    private final CreateLotteryTicketInteractor createLotteryTicketInteractor;
    private final UpdateLotteryTicketInteractor updateLotteryTicketInteractor;
    private final LotteryTicketFormView view;
    private final PresentationLotteryTicketMapper lotteryTicketMapper;

    public LotteryTicketFormPresenter(CreateLotteryTicketInteractor createLotteryTicketInteractor,
                                      UpdateLotteryTicketInteractor updateLotteryTicketInteractor,
                                      LotteryTicketFormView view,
                                      PresentationLotteryTicketMapper lotteryTicketMapper) {
        this.createLotteryTicketInteractor = createLotteryTicketInteractor;
        this.updateLotteryTicketInteractor = updateLotteryTicketInteractor;
        this.view = view;
        this.lotteryTicketMapper = lotteryTicketMapper;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    public void createLotteryTicket(PresentationLotteryTicket lotteryTicket) {
        createLotteryTicketInteractor.setData(lotteryTicketMapper.dataToModel(lotteryTicket));
        createLotteryTicketInteractor.setCallback(createLotteryTicketCallback);
        createLotteryTicketInteractor.execute();
    }

    public void updateLotteryTicket(PresentationLotteryTicket lotteryTicket) {
        updateLotteryTicketInteractor.setData(lotteryTicketMapper.dataToModel(lotteryTicket));
        updateLotteryTicketInteractor.setCallback(updateLotteryTicketCallback);
        updateLotteryTicketInteractor.execute();
    }

    private final CreateLotteryTicketInteractor.Callback createLotteryTicketCallback =
            new CreateLotteryTicketInteractor.Callback() {

                @Override
                public void onSuccess() {
                    view.showCreateOrUpdateLotteryTicketSuccess();
                }

                @Override
                public void onError(Throwable error) {
                    view.showCreateLotteryTicketError();
                }
            };

    private final UpdateLotteryTicketInteractor.Callback updateLotteryTicketCallback =
            new UpdateLotteryTicketInteractor.Callback() {

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
