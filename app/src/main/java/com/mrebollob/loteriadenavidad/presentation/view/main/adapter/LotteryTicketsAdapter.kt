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

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mrebollob.loteriadenavidad.R
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import com.mrebollob.loteriadenavidad.presentation.presenter.main.MainPresenter
import com.mrebollob.loteriadenavidad.utils.extensions.getStableId
import java.util.*


internal interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
}

class LotteryTicketsAdapter(private var lotteryTickets: List<LotteryTicket>,
                            private val presenter: MainPresenter)
    : RecyclerView.Adapter<LotteryTicketViewHolder>(), ItemTouchHelperAdapter {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LotteryTicketViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_lottery_ticket,
                parent, false)
        return LotteryTicketViewHolder(view, presenter)
    }

    override fun onBindViewHolder(itemViewHolder: LotteryTicketViewHolder, position: Int) {
        itemViewHolder.bindItem(lotteryTickets[position])
    }

    override fun getItemCount(): Int = lotteryTickets.size

    fun getItem(position: Int): LotteryTicket = lotteryTickets[position]

    override fun getItemId(position: Int): Long = getItem(position).getStableId()

    fun updateItems(newItems: List<LotteryTicket>) {
        val oldItems = lotteryTickets
        lotteryTickets = newItems
        applyDiff(oldItems, lotteryTickets)
    }

    private fun applyDiff(oldItems: List<LotteryTicket>, newItems: List<LotteryTicket>) {
        val diffResult = DiffUtil.calculateDiff(LotteryTicketsDiffCallback(oldItems, newItems))
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        swapItems(fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        presenter.updateLotteryTicketPositions(lotteryTickets)
    }

    fun swapItems(fromPosition: Int, toPosition: Int) = if (fromPosition < toPosition) {
        (fromPosition..toPosition - 1).forEach { i ->
            swapPositions(i, i + 1)
            Collections.swap(lotteryTickets, i, i + 1)
        }
    } else {
        (fromPosition downTo toPosition + 1).forEach { i ->
            swapPositions(i, i - 1)
            Collections.swap(lotteryTickets, i, i - 1)
        }
    }

    fun swapPositions(position1: Int, position2: Int) {
        val item1 = lotteryTickets[position1]
        val item2 = lotteryTickets[position2]
        lotteryTickets = lotteryTickets.map {
            if (it.localId == item1.localId) it.copy(position = item2.position)
            else if (it.localId == item2.localId) it.copy(position = item1.position)
            else it
        }
    }
}