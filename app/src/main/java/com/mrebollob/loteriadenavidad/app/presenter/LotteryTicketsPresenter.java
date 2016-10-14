/*
 * Copyright 2016 Manuel Rebollo Báez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrebollob.loteriadenavidad.app.presenter;

import com.mrebollob.loteriadenavidad.app.di.qualifiers.PerActivity;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.GetLotteryTickets;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

@PerActivity
public class LotteryTicketsPresenter extends Presenter<LotteryTicketsPresenter.View> {

    private final GetLotteryTickets mGetLotteryTickets;
    private Subscription mLotteryTicketsSubscription;

    @Inject
    public LotteryTicketsPresenter(GetLotteryTickets getLotteryTickets) {
        this.mGetLotteryTickets = getLotteryTickets;
    }

    @Override
    public void initialize() {
        super.initialize();
        getLotteryTickets();
    }

    @Override
    public void update() {
        super.update();
        getLotteryTickets();
    }

    @Override
    public void finish() {
        super.finish();
        mLotteryTicketsSubscription.unsubscribe();
    }

    private void getLotteryTickets() {
        mLotteryTicketsSubscription = mGetLotteryTickets.execute().subscribe(lotteryTickets -> {
            getView().hideLoading();
            if (lotteryTickets.isEmpty()) {
                getView().showEmptyCase();
            } else {
                getView().showLotteryTickets(lotteryTickets);
            }
        }, throwable -> {
            getView().hideLoading();
        });
    }

    public void onLotteryTicketClicked(LotteryTicket lotteryTicket) {
        getView().openLotteryTicketScreen(lotteryTicket);
    }

    public interface View extends Presenter.View {

        void showLotteryTickets(List<LotteryTicket> lotteryTickets);

        void showEmptyCase();

        void openLotteryTicketScreen(LotteryTicket lotteryTicket);
    }
}