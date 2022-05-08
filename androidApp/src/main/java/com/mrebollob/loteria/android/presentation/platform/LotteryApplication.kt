package com.mrebollob.loteria.android.presentation.platform

import android.app.Application
import co.touchlab.kermit.Logger
import com.mrebollob.loteria.android.BuildConfig
import com.mrebollob.loteria.android.di.appModule
import com.mrebollob.loteria.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class LotteryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            // https://github.com/InsertKoinIO/koin/issues/1188
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@LotteryApplication)
            modules(appModule)
        }

        Logger.d { "LotteryApplication" }
    }
}
