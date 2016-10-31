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

package com.mrebollob.loteriadenavidad.app.di.components;

import com.mrebollob.loteriadenavidad.app.LotteryApplication;
import com.mrebollob.loteriadenavidad.app.di.modules.AppModule;
import com.mrebollob.loteriadenavidad.app.di.modules.DbModule;
import com.mrebollob.loteriadenavidad.app.di.modules.SpModule;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = {AppModule.class, DbModule.class, SpModule.class})
public interface AppComponent {

    LotteryApplication app();

    LotteryRepository dataRepository();

    @Named("ui_thread")
    Scheduler uiThread();

    @Named("executor_thread")
    Scheduler executorThread();
}
