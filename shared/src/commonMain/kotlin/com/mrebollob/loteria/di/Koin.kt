package com.mrebollob.loteria.di

import com.mrebollob.loteria.data.TicketsRepositoryImp
import com.mrebollob.loteria.domain.repository.TicketsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs = enableNetworkLogs))
    }

// called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun commonModule(enableNetworkLogs: Boolean) = module {
    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    single<TicketsRepository> { TicketsRepositoryImp() }
}
