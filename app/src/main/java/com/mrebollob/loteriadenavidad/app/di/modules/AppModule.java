/*
 * Copyright 2016 Manuel Rebollo Báez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrebollob.loteriadenavidad.app.di.modules;

import android.content.SharedPreferences;

import com.mrebollob.loteriadenavidad.app.LotteryApplication;
import com.mrebollob.loteriadenavidad.data.repository.LotteryRepositoryImp;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class AppModule {
    private final LotteryApplication mLotteryApplication;

    public AppModule(LotteryApplication lotteryApplication) {
        this.mLotteryApplication = lotteryApplication;
    }

    @Provides
    @Singleton
    LotteryApplication provideLotteryApplicationContext() {
        return mLotteryApplication;
    }

    @Provides
    @Singleton
    public SessionManager provideSessionManager(SharedPreferences sharedPreferences) {
        return new SessionManager(sharedPreferences);
    }

    @Provides
    @Singleton
    LotteryRepository provideLotteryRepositoryImp(LotteryRepositoryImp lotteryRepository) {
        return lotteryRepository;
    }

    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.newThread();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}
