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

package com.mrebollob.loteriadenavidad.domain.datasource

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import io.reactivex.Observable

interface LotteryTicketDataSource {

    fun createLotteryTicket(lotteryTicket: LotteryTicket): Observable<Unit>

    fun readLotteryTickets(): Observable<List<LotteryTicket>>

    fun updateLotteryTicket(lotteryTicket: LotteryTicket): Observable<Unit>

    fun updateLotteryTickets(lotteryTickets: List<LotteryTicket>): Observable<Unit>

    fun deleteLotteryTicket(id: String): Observable<Unit>
}