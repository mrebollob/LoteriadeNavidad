package com.mrebollob.loteria.domain.usecase


class GetDaysToLotteryDraw {

    fun execute(): Int {
//        val now = LocalDate.now()
//        val lotteryDrawDate = LocalDate.of(2022, Month.DECEMBER, 22)
        return 1 //ChronoUnit.DAYS.between(now, lotteryDrawDate).toInt()
    }
}
