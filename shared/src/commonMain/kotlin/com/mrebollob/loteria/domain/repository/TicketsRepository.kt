package com.mrebollob.loteria.domain.repository

import com.mrebollob.loteria.domain.entity.Ticket

interface TicketsRepository {

    suspend fun getTickets(): Result<List<Ticket>>

    suspend fun createTicket(ticket: Ticket): Result<Ticket>

    suspend fun deleteTicket(ticket: Ticket): Result<Unit>
}
