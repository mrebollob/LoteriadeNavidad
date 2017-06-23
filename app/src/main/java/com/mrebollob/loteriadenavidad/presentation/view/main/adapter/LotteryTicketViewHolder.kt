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
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.mrebollob.loteriadenavidad.R
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import com.mrebollob.loteriadenavidad.presentation.presenter.main.MainPresenter
import com.mrebollob.loteriadenavidad.utils.extensions.changeBackgroundColor
import com.mrebollob.loteriadenavidad.utils.extensions.toColorResource
import org.jetbrains.anko.find

internal interface ItemTouchHelperViewHolder {
    fun onItemSelected()
    fun onItemClear()
}

class LotteryTicketViewHolder(view: View, private val presenter: MainPresenter,
                              private val reorderItems: () -> Unit)
    : RecyclerView.ViewHolder(view), ItemTouchHelperViewHolder {

    private lateinit var currentLotteryTicket: LotteryTicket
    val itemContentLayout: RelativeLayout = itemView.find(R.id.contentLayout)
    val labelTextView: TextView = itemView.find(R.id.labelTextView)

    fun bindItem(lotteryTicket: LotteryTicket) =
            with(lotteryTicket) {
                currentLotteryTicket = this
                bindViewContent(this)
                bindClickHandlers(this)
            }

    private fun bindViewContent(lotteryTicket: LotteryTicket) =
            with(lotteryTicket) {
                labelTextView.text = number?.toString()
                itemContentLayout.changeBackgroundColor(color.toColorResource())
            }

    private fun bindClickHandlers(lotteryTicket: LotteryTicket) {
        itemContentLayout.setOnClickListener { presenter.onLotteryTicketClick(lotteryTicket) }
    }

    override fun onItemSelected() {
        itemContentLayout.changeBackgroundColor(R.color.item_selected)
    }

    override fun onItemClear() {
        itemContentLayout.changeBackgroundColor(currentLotteryTicket.color.toColorResource())
        reorderItems()
    }
}