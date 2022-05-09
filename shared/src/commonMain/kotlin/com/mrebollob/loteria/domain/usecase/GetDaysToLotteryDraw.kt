package com.mrebollob.loteria.domain.usecase

import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit

class GetDaysToLotteryDraw {

    fun execute(): Int {
        val now = LocalDate.now()
        val lotteryDrawDate = LocalDate.of(2022, Month.DECEMBER, 22)
        return ChronoUnit.DAYS.between(now, lotteryDrawDate).toInt()
    }
}
