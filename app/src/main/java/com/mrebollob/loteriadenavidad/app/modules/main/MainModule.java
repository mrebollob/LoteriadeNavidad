package com.mrebollob.loteriadenavidad.app.modules.main;

import com.mrebollob.loteriadenavidad.app.di.ActivityModule;
import com.mrebollob.loteriadenavidad.app.navigator.Navigator;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CheckLotteryStatus;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.CheckLotteryTicketsPrize;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.DeleteLotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.GetLastUpdatedTime;
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
    MainPresenter provideMainPresenter(Navigator navigator, GetLotteryTickets getLotteryTickets,
                                       DeleteLotteryTicket deleteLotteryTicket,
                                       CheckLotteryTicketsPrize checkLotteryTicketsPrize,
                                       GetLastUpdatedTime getLastUpdatedTime,
                                       CheckLotteryStatus checkLotteryStatus,
                                       ListMapper<LotteryTicket, PresentationLotteryTicket> lotteryTicketsListMapper) {
        return new MainPresenter(navigator, getLotteryTickets, deleteLotteryTicket,
                checkLotteryTicketsPrize, getLastUpdatedTime, checkLotteryStatus, view, lotteryTicketsListMapper);
    }
}