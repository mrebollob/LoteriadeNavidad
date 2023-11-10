package com.mrebollob.loteria.domain.usecase.draw

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayAt

class GetDaysToLotteryDraw {

    fun execute(): Int {
        val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
        val lotteryDrawDate = LocalDate(2023, Month.DECEMBER, 22)

        return today.daysUntil(lotteryDrawDate)
    }
}
