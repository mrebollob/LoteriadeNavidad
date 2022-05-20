package com.mrebollob.loteria.android.di

import com.mrebollob.loteria.data.SettingsRepositoryImp
import com.mrebollob.loteria.data.TicketsRepositoryImp
import com.mrebollob.loteria.domain.repository.SettingsRepository
import com.mrebollob.loteria.domain.repository.TicketsRepository
import com.mrebollob.loteria.domain.usecase.draw.GetDaysToLotteryDraw
import com.mrebollob.loteria.domain.usecase.settings.GetSortingMethod
import com.mrebollob.loteria.domain.usecase.settings.SaveSortingMethod
import com.mrebollob.loteria.domain.usecase.ticket.CreateTicket
import com.mrebollob.loteria.domain.usecase.ticket.DeleteTicket
import com.mrebollob.loteria.domain.usecase.ticket.GetTickets
import org.koin.dsl.module

val dataModule = module {

    // Repository
    single<TicketsRepository> { TicketsRepositoryImp() }
    single<SettingsRepository> { SettingsRepositoryImp() }

    // Use case
    single { GetDaysToLotteryDraw() }

    single { CreateTicket(get()) }
    single { DeleteTicket(get()) }
    single { GetTickets(get()) }

    single { GetSortingMethod(get()) }
    single { SaveSortingMethod(get()) }
}
