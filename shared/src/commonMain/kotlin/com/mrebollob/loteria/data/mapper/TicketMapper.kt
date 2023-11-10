package com.mrebollob.loteria.data.mapper

import com.mrb.loteria.db.DbTicket
import com.mrebollob.loteria.domain.entity.Ticket

class TicketMapper {

    fun toDomain(dbTicket: DbTicket): Ticket {
        return Ticket(
            dbTicket.id,
            dbTicket.name,
            dbTicket.number.toInt(),
            dbTicket.bet.toFloat(),
        )
    }
}
