package com.mrebollob.loteria.data

import com.mrebollob.loteria.domain.entity.Ticket
import com.mrebollob.loteria.domain.repository.TicketsRepository

class TicketsRepositoryImp : TicketsRepository {

    override suspend fun getTickets(): Result<List<Ticket>> {

        return Result.success(
            listOf(
                Ticket(name = "Test ticket 1", number = 0, bet = 20f),
                Ticket(name = "Test ticket 2", number = 99999, bet = 200f)
            )
        )
    }

    override suspend fun createTicket(
        name: String,
        number: Int,
        bet: Float
    ): Result<Ticket> {

        return Result.success(
            Ticket(
                name = name,
                number = number,
                bet = bet
            )
        )
    }

    override suspend fun deleteTicket(ticket: Ticket): Result<Unit> {
        TODO("Not yet implemented")
    }
}
