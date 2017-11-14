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

package com.mrebollob.loteriadenavidad.ui.main

import android.arch.lifecycle.ViewModel
import com.mrebollob.loteriadenavidad.models.Ticket
import com.mrebollob.loteriadenavidad.persistence.TicketDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.Action
import io.reactivex.internal.operators.completable.CompletableFromAction

/**
 * View Model for the [MainActivity]
 */
class TicketViewModel(private val dataSource: TicketDao) : ViewModel() {

    /**
     * Get the number of the ticket.
     * @return a [Flowable] that will emit every time the ticket number has been updated.
     */
    // for every emission of the ticket, get the number
    fun number(): Flowable<Int> {
        return dataSource.getTicketById(TICKET_ID)
                .map { ticket -> ticket.number }
    }

    /**
     * Update the ticket number.
     * @param number the new ticket number
     * *
     * @return a [Completable] that completes when the ticket number is updated
     */
    fun updateNumber(number: Int): Completable {
        return CompletableFromAction(Action {
            val ticket = Ticket(TICKET_ID, number)
            dataSource.insertTicket(ticket)
        })
    }

    companion object {
        // using a hardcoded value for simplicity
        const val TICKET_ID = "1"
    }
}