package com.mrebollob.loteriadenavidad.app.modules.lotteryticketform;

import com.mrebollob.loteriadenavidad.app.di.ActivityModule;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CreateLotteryTicketInteractor;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.UpdateLotteryTicketInteractor;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.PresentationLotteryTicketMapper;
import com.mrebollob.loteriadenavidad.presentation.modules.lotteryticketform.LotteryTicketFormPresenter;
import com.mrebollob.loteriadenavidad.presentation.modules.lotteryticketform.LotteryTicketFormView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */
@Module(
        addsTo = ActivityModule.class,
        injects = LotteryTicketFormActivity.class)
public class LotteryTicketFormModule {

    private LotteryTicketFormView view;

    public LotteryTicketFormModule(LotteryTicketFormView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LotteryTicketFormPresenter provideLotteryTicketFormPresenter(
            CreateLotteryTicketInteractor createLotteryTicketInteractor,
            UpdateLotteryTicketInteractor updateLotteryTicketInteractor,
            PresentationLotteryTicketMapper lotteryTicketMapper) {
        return new LotteryTicketFormPresenter(createLotteryTicketInteractor, updateLotteryTicketInteractor,
                view, lotteryTicketMapper);
    }
}