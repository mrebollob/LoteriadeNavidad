package com.mrebollob.loteria.di

import com.mrb.loteria.db.LotteryDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver

import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        val driver =
            AndroidSqliteDriver(LotteryDatabase.Schema, get(), "lottery.db")
        LotteryDatabaseWrapper(LotteryDatabase(driver))
    }
}
