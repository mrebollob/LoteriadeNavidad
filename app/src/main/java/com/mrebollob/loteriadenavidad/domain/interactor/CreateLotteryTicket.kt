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

package com.mrebollob.loteriadenavidad.domain.interactor

import com.mrebollob.loteriadenavidad.domain.datasources.DbDataSource
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import com.mrebollob.loteriadenavidad.domain.executor.PostExecutionThread
import com.mrebollob.loteriadenavidad.domain.executor.ThreadExecutor
import io.reactivex.Observable
import javax.inject.Inject


class CreateLotteryTicket @Inject constructor(val dbDataSource: DbDataSource,
                                              threadExecutor: ThreadExecutor,
                                              postExecutionThread: PostExecutionThread)
    : AbstractInteractor<Unit, LotteryTicket>(threadExecutor, postExecutionThread) {

    override fun buildInteractorObservable(lotteryTicket: LotteryTicket): Observable<Unit> {

        return dbDataSource.createLotteryTicket(lotteryTicket)
    }
}