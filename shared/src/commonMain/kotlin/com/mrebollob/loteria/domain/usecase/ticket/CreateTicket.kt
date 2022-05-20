package com.mrebollob.loteria.domain.usecase.ticket

import com.mrebollob.loteria.domain.repository.TicketsRepository

class CreateTicket(
    private val ticketsRepository: TicketsRepository
) {

    suspend fun execute(
        name: String,
        number: Int,
        bet: Float
    ) = ticketsRepository.createTicket(
        name = name,
        number = number,
        bet = bet
    )
}
