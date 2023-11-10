package com.mrebollob.loteria.di

import com.mrebollob.loteria.data.mapper.TicketMapper
import com.mrebollob.loteria.data.preferences.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(), platformModule())
    }

// called by iOS etc
fun initKoin() = initKoin {}

fun commonModule() = module {
    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    // Preferences
    single { Preferences("lottery_app") }

    // Mapper
    single { TicketMapper() }
}
