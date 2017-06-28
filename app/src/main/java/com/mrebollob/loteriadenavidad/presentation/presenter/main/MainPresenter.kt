/*
 * Copyright (c) 2017. Manuel Rebollo Báez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrebollob.loteriadenavidad.presentation.presenter.main

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import com.mrebollob.loteriadenavidad.domain.interactor.DefaultObserver
import com.mrebollob.loteriadenavidad.domain.interactor.GetLotteryTickets
import com.mrebollob.loteriadenavidad.domain.interactor.UnitObserver
import com.mrebollob.loteriadenavidad.domain.interactor.UpdateLotteryTickets
import com.mrebollob.loteriadenavidad.presentation.presenter.Presenter
import com.mrebollob.loteriadenavidad.presentation.view.main.MainMvpView
import javax.inject.Inject

class MainPresenter @Inject constructor(private val getLotteryTickets: GetLotteryTickets,
                                        private val updateLotteryTickets: UpdateLotteryTickets)
    : Presenter<MainMvpView> {

    private var mView: MainMvpView? = null

    override fun attachView(view: MainMvpView, isNew: Boolean) {
        mView = view
        getLotteryTickets()
    }

    fun onRefreshClick() {
        getLotteryTickets()
    }

    fun onLotteryTicketClick(lotteryTicket: LotteryTicket) {

    }

    fun updateLotteryTicketPositions(lotteryTickets: List<LotteryTicket>) {
        updateLotteryTickets.execute(UnitObserver(), lotteryTickets)
    }

    fun onDeleteLotteryTicket(lotteryTicket: LotteryTicket) {

    }

    fun onNewLotteryTicketClick() {
        mView?.showNewLotteryTicketForm()
    }

    private fun getLotteryTickets() {
        getLotteryTickets.execute(CreditCardObserver(), Unit)
    }

    override fun detachView() {
        getLotteryTickets.dispose()
        updateLotteryTickets.dispose()
    }

    private inner class CreditCardObserver : DefaultObserver<List<LotteryTicket>>() {

        override fun onNext(value: List<LotteryTicket>) {
            mView?.showLotteryTickets(value)
        }

        override fun onComplete() {
            mView?.hideLoading()
        }

        override fun onError(e: Throwable?) {
            mView?.hideLoading()
            mView?.showGetLotteryTicketsError()
        }
    }
}