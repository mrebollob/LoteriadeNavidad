package com.mrebollob.loteriadenavidad.app.modules.lotteryticketform;

import com.mrebollob.loteriadenavidad.app.di.ActivityModule;

import dagger.Module;

/**
 * @author mrebollob
 */
@Module(
        addsTo = ActivityModule.class,
        injects = LotteryTicketFormActivity.class)
public class LotteryTicketFormModule {

    public LotteryTicketFormModule() {
    }

}