package com.mrebollob.loteria.di

import com.mrebollob.loteria.data.SettingsRepositoryImp
import com.mrebollob.loteria.data.TicketsRepositoryImp
import com.mrebollob.loteria.data.mapper.TicketMapper
import com.mrebollob.loteria.domain.repository.SettingsRepository
import com.mrebollob.loteria.domain.repository.TicketsRepository
import com.mrebollob.loteria.domain.usecase.GetDaysToLotteryDraw
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs = enableNetworkLogs), platformModule())
    }

// called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun commonModule(enableNetworkLogs: Boolean) = module {
    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    // Repository
    single<TicketsRepository> { TicketsRepositoryImp(get(), get()) }
    single<SettingsRepository> { SettingsRepositoryImp() }

    // Use case
    single { GetDaysToLotteryDraw() }

    // Mapper
    single { TicketMapper() }
}
