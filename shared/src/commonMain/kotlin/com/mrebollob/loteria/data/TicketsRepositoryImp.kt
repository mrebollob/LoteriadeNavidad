package com.mrebollob.loteria.data

import com.mrebollob.loteria.data.mapper.TicketMapper
import com.mrebollob.loteria.di.LotteryDatabaseWrapper
import com.mrebollob.loteria.domain.entity.Ticket
import com.mrebollob.loteria.domain.repository.TicketsRepository

class TicketsRepositoryImp(
    lotteryDatabaseWrapper: LotteryDatabaseWrapper,
    private val ticketMapper: TicketMapper
) : TicketsRepository {

    private val lotteryQueries = lotteryDatabaseWrapper.instance?.lotteryQueries

    override suspend fun getTickets(): Result<List<Ticket>> {
        val dbTickets = lotteryQueries?.selectAll()?.executeAsList() ?: emptyList()
        return Result.success(dbTickets.map { ticketMapper.toDomain(it) })
    }

    override suspend fun createTicket(
        name: String,
        number: Int,
        bet: Float
    ): Result<Unit> {
        lotteryQueries?.insertItem(
            id = null,
            name = name,
            number = number.toLong(),
            bet = bet.toDouble()
        )

        return Result.success(Unit)
    }

    override suspend fun deleteTicket(ticket: Ticket): Result<Unit> {
        lotteryQueries?.deleteById(id = ticket.id)
        return Result.success(Unit)
    }
}
