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

package com.mrebollob.loteriadenavidad.presentation.presenter.form

import com.mrebollob.loteriadenavidad.domain.entities.Color
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import com.mrebollob.loteriadenavidad.domain.interactor.CreateLotteryTicket
import com.mrebollob.loteriadenavidad.domain.interactor.DefaultObserver
import com.mrebollob.loteriadenavidad.presentation.presenter.Presenter
import com.mrebollob.loteriadenavidad.presentation.view.form.FormMvpView
import javax.inject.Inject


class FormPresenter @Inject constructor(private val createLotteryTicket: CreateLotteryTicket)
    : Presenter<FormMvpView> {

    private var mView: FormMvpView? = null
    private var currentLotteryTicket = LotteryTicket()
    var lotteryTicketId: String? = null

    override fun attachView(view: FormMvpView, isNew: Boolean) {
        mView = view
    }

    fun updateColor(color: Color) {
        currentLotteryTicket = currentLotteryTicket.copy(color = color)
    }

    fun createOrUpdateLotteryTicket(label: String, number: Int, bet: Float) {
        currentLotteryTicket = currentLotteryTicket.copy(label = label, number = number, bet = bet)
        createLotteryTicket.execute(CreateCreditCardObserver(), currentLotteryTicket)
    }

    override fun detachView() {
        createLotteryTicket.dispose()
    }

    private inner class CreateCreditCardObserver : DefaultObserver<Unit>() {

        override fun onNext(value: Unit) {
        }

        override fun onComplete() {
            mView?.showLotteryTickets()
        }

        override fun onError(e: Throwable?) {
            mView?.showCreateLotteryTicketError()
        }
    }
}