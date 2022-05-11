package com.mrebollob.loteria

import com.mrb.loteria.db.LotteryDatabase
import com.mrebollob.loteria.di.LotteryDatabaseWrapper
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        val driver = NativeSqliteDriver(LotteryDatabase.Schema, "loteria.db")
        LotteryDatabaseWrapper(LotteryDatabase(driver))
    }
}
