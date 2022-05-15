package com.mrebollob.loteria.domain.entity

data class Ticket(
    val id: Long,
    val name: String,
    val number: Int,
    val bet: Float
)
