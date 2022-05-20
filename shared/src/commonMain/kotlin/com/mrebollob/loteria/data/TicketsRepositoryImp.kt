package com.mrebollob.loteria.data

import com.mrebollob.loteria.data.mapper.TicketMapper
import com.mrebollob.loteria.di.LotteryDatabaseWrapper
import com.mrebollob.loteria.domain.entity.Ticket
import com.mrebollob.loteria.domain.repository.TicketsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TicketsRepositoryImp : KoinComponent, TicketsRepository {

    private val lotteryDatabaseWrapper: LotteryDatabaseWrapper by inject()
    private val ticketMapper: TicketMapper by inject()

    private val lotteryQueries = lotteryDatabaseWrapper.instance?.lotteryQueries

    override suspend fun getTickets(): List<Ticket> {
        val dbTickets = lotteryQueries?.selectAll()?.executeAsList() ?: emptyList()
        return dbTickets.map { ticketMapper.toDomain(it) }
    }

    override suspend fun createTicket(
        name: String,
        number: Int,
        bet: Float
    ) {
        lotteryQueries?.insertItem(
            id = null,
            name = name,
            number = number.toLong(),
            bet = bet.toDouble()
        )
    }

    override suspend fun deleteTicket(ticket: Ticket) {
        lotteryQueries?.deleteById(id = ticket.id)
    }
}
