package com.mrebollob.loteria.domain.usecase.ticket

import com.mrebollob.loteria.domain.entity.Ticket
import com.mrebollob.loteria.domain.repository.TicketsRepository

class GetTickets(
    private val ticketsRepository: TicketsRepository
) {

    suspend fun execute(): List<Ticket> = ticketsRepository.getTickets()
}
