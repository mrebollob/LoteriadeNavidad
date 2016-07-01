package com.mrebollob.loteriadenavidad.presentation.modules.lotteryticketform;

import android.util.Log;

import com.mrebollob.loteriadenavidad.app.navigator.Navigator;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CreateLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.UpdateLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.Presenter;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.PresentationLotteryTicketMapper;

/**
 * @author mrebollob
 */
public class LotteryTicketFormPresenter extends Presenter {

    private static final String TAG = LotteryTicketFormPresenter.class.getSimpleName();

    private final Navigator navigator;
    private final CreateLotteryTicket createLotteryTicket;
    private final UpdateLotteryTicket updateLotteryTicket;
    private final LotteryTicketFormView view;
    private final PresentationLotteryTicketMapper lotteryTicketMapper;

    public LotteryTicketFormPresenter(Navigator navigator,
                                      CreateLotteryTicket createLotteryTicket,
                                      UpdateLotteryTicket updateLotteryTicket,
                                      LotteryTicketFormView view,
                                      PresentationLotteryTicketMapper lotteryTicketMapper) {
        this.navigator = navigator;
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

    public void createLotteryTicket(PresentationLotteryTicket lotteryTicket) {
        view.showLoading();
        createLotteryTicket.setData(lotteryTicketMapper.dataToModel(lotteryTicket));
        createLotteryTicket.setCallback(createLotteryTicketCallback);
        createLotteryTicket.execute();
    }

    public void updateLotteryTicket(PresentationLotteryTicket lotteryTicket) {
        view.showLoading();
        updateLotteryTicket.setData(lotteryTicketMapper.dataToModel(lotteryTicket));
        updateLotteryTicket.setCallback(updateLotteryTicketCallback);
        updateLotteryTicket.execute();
    }

    public void onBackPressed() {
        navigator.goBackToLotteryTicketList();
    }

    private final CreateLotteryTicket.Callback createLotteryTicketCallback =
            new CreateLotteryTicket.Callback() {

                @Override
                public void onSuccess() {
                    view.hideLoading();
                    navigator.goBackToLotteryTicketList();
                }

                @Override
                public void onError(Throwable error) {
                    Log.e(TAG, "Error saving lottery ticket", error);
                    view.hideLoading();
                    view.showCreateLotteryTicketError();
                }
            };

    private final UpdateLotteryTicket.Callback updateLotteryTicketCallback =
            new UpdateLotteryTicket.Callback() {

                @Override
                public void onSuccess() {
                    view.hideLoading();
                    navigator.goBackToLotteryTicketList();
                }

                @Override
                public void onError(Throwable error) {
                    Log.e(TAG, "Error updating lottery ticket", error);
                    view.hideLoading();
                    view.showUpdateLotteryTicketError();
                }
            };
}
