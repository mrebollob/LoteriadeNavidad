package com.mrebollob.loteriadenavidad.app.di;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.PresentationLotteryTicketMapper;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.base.ListMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author mrebollob
 */

@Module(
        complete = false,
        library = true
)
public class PresentationModule {

    @Provides
    @Singleton
    PresentationLotteryTicketMapper providePresentationLotteryTicketMapper() {
        return new PresentationLotteryTicketMapper();
    }

    @Provides
    @Singleton
    ListMapper<LotteryTicket, PresentationLotteryTicket> providePresentationLotteryTicketsListMapper(
            PresentationLotteryTicketMapper presentationLotteryTicketMapper) {
        return new ListMapper<>(presentationLotteryTicketMapper);
    }
}