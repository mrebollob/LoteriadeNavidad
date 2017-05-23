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

package com.mrebollob.loteriadenavidad.presentation.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.mrebollob.loteriadenavidad.R
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import com.mrebollob.loteriadenavidad.presentation.presenter.main.MainPresenter

class LotteryTicketViewHolder constructor(itemView: View, val presenter: MainPresenter)
    : RecyclerView.ViewHolder(itemView) {

    private val labelTextView by lazy { itemView.findViewById(R.id.labelTextView) as TextView }
    private val prizeTextView by lazy { itemView.findViewById(R.id.prizeTextView) as TextView }

    fun bind(lotteryTicket: LotteryTicket) {

        hookListeners(lotteryTicket)
        val totalPrize = lotteryTicket.bet * lotteryTicket.prize / 20

        labelTextView.text = lotteryTicket.label
        prizeTextView.text = getContext().getString(R.string.money_format, totalPrize)
    }

    private fun getContext(): Context {
        return itemView.context
    }

    private fun hookListeners(lotteryTicket: LotteryTicket) {
        itemView.setOnClickListener { v -> presenter.onLotteryTicketClick(lotteryTicket) }
    }
}