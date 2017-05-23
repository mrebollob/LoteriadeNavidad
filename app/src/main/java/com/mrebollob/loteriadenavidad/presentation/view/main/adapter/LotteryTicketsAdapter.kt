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

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.mrebollob.loteriadenavidad.R
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import com.mrebollob.loteriadenavidad.presentation.presenter.main.MainPresenter
import com.mrebollob.loteriadenavidad.utils.extensions.inflate
import kotlin.properties.Delegates


class LotteryTicketsAdapter constructor(val presenter: MainPresenter)
    : RecyclerView.Adapter<LotteryTicketViewHolder>() {

    var lotteryTickets: List<LotteryTicket> by Delegates.observable(emptyList())
    { prop, old, new -> notifyDataSetChange() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotteryTicketViewHolder? {
        val view = parent.inflate(R.layout.item_lottery_ticket)
        return LotteryTicketViewHolder(view, presenter)
    }

    override fun onBindViewHolder(holder: LotteryTicketViewHolder, position: Int) {
        val lotteryTicket = lotteryTickets[position]
        holder.bind(lotteryTicket)
    }

    override fun getItemCount(): Int {
        return lotteryTickets.count()
    }

    fun notifyDataSetChange() {
        notifyDataSetChanged()
    }
}