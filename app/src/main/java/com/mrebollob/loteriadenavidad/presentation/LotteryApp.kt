/*
 * Copyright (c) 2017. Manuel Rebollo Báez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrebollob.loteriadenavidad.presentation

import android.app.Application
import android.os.StrictMode
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.core.CrashlyticsCore
import com.mrebollob.loteriadenavidad.BuildConfig
import com.mrebollob.loteriadenavidad.R
import com.mrebollob.loteriadenavidad.presentation.di.components.AppComponent
import com.mrebollob.loteriadenavidad.presentation.di.components.DaggerAppComponent
import com.mrebollob.loteriadenavidad.presentation.di.modules.AppModule
import io.fabric.sdk.android.Fabric
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class LotteryApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }
        initializeCalligraphy()
        initializeCrashlytics()
    }

    private fun initializeCrashlytics() {
        val crashlyticsCore = CrashlyticsCore.Builder()
                .disabled("debug" == BuildConfig.BUILD_TYPE)
                .build()

        val crashlytics = Crashlytics.Builder()
                .core(crashlyticsCore)
                .build()

        val answers = Answers()

        val fabric = Fabric.Builder(applicationContext)
                .kits(crashlytics, answers)
                .debuggable(BuildConfig.DEBUG)
                .build()

        Fabric.with(fabric)

        Crashlytics.setString("GIT_SHA_KEY", BuildConfig.GIT_SHA)
        Crashlytics.setString("BUILD_TIME", BuildConfig.BUILD_TIME)
    }

    private fun initializeCalligraphy() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build())
    }
}