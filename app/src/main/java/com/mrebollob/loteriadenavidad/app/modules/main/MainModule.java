package com.mrebollob.loteriadenavidad.app.modules.main;

import com.mrebollob.loteriadenavidad.app.di.ActivityModule;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLotteryTickets;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.base.ListMapper;
import com.mrebollob.loteriadenavidad.presentation.modules.main.MainPresenter;
import com.mrebollob.loteriadenavidad.presentation.modules.main.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */
@Module(
        addsTo = ActivityModule.class,
        injects = MainActivity.class)
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    MainPresenter provideMainPresenter(GetLotteryTickets getLotteryTickets,
                                       DeleteLotteryTicket deleteLotteryTicket,
                                       ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper) {
        return new MainPresenter(getLotteryTickets, deleteLotteryTicket,
                view, lotteryTicketsListMapper);
    }
}