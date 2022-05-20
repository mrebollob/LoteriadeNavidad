package com.mrebollob.loteria.domain.usecase.ticket

import com.mrebollob.loteria.domain.entity.Ticket
import com.mrebollob.loteria.domain.repository.TicketsRepository

class DeleteTicket(
    private val ticketsRepository: TicketsRepository
) {

    suspend fun execute(
        ticket: Ticket
    ) = ticketsRepository.deleteTicket(
        ticket = ticket
    )
}
