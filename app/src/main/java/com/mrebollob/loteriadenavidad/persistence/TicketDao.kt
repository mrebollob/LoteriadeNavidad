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

package com.mrebollob.loteriadenavidad.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable


/**
 * Data Access Object for the tickets table.
 */
@Dao
interface TicketDao {

    /**
     * Get a ticket by id.
     * @return the ticket from the table with a specific id.
     */
    @Query("SELECT * FROM Tickets WHERE ticketid = :id")
    fun getTicketById(id: String): Flowable<Ticket>

    /**
     * Insert a ticket in the database. If the ticket already exists, replace it.
     * @param ticket the ticket to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTicket(ticket: Ticket)

    /**
     * Delete all tickets.
     */
    @Query("DELETE FROM Tickets")
    fun deleteAllTickets()
}