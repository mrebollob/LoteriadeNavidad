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

package com.mrebollob.loteriadenavidad.presentation.di.modules

import android.app.Application
import com.crashlytics.android.answers.Answers
import com.google.gson.Gson
import com.mrebollob.loteriadenavidad.domain.executor.PostExecutionThread
import com.mrebollob.loteriadenavidad.domain.executor.ThreadExecutor
import com.mrebollob.loteriadenavidad.presentation.executor.JobExecutor
import com.mrebollob.loteriadenavidad.presentation.executor.UIThread
import com.mrebollob.loteriadenavidad.utils.analytics.AnalyticsHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesAnalyticsHelper(): AnalyticsHelper {

        return AnalyticsHelper(Answers.getInstance())
    }

    @Provides
    @Singleton
    fun providesThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {

        return jobExecutor
    }

    @Provides
    @Singleton
    fun providesPostExecutionThread(uiThread: UIThread): PostExecutionThread {

        return uiThread
    }
}