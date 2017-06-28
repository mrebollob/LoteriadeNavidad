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

package com.mrebollob.loteriadenavidad.data

import com.mrebollob.loteriadenavidad.data.db.*
import com.mrebollob.loteriadenavidad.domain.datasource.LotteryTicketDataSource
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket
import io.reactivex.Observable
import io.realm.Realm
import javax.inject.Inject


class LotteryTicketDataSourceImp @Inject constructor() : LotteryTicketDataSource {

    override fun createLotteryTicket(lotteryTicket: LotteryTicket): Observable<Unit> {

        val db = Realm.getDefaultInstance()
        lotteryTicket.toDbLotteryTicket().insertOrUpdate(db)
        db.close()

        return Observable.just(Unit)
    }

    override fun readLotteryTickets(): Observable<List<LotteryTicket>> {

        val db = Realm.getDefaultInstance()
        val persistenceItems = db.queryAllDbLotteryTicketsSortedByPosition()
        val lotteryTickets = persistenceItems.toLotteryTicketList()
        db.close()

        return Observable.just(lotteryTickets)
    }

    override fun updateLotteryTicket(lotteryTicket: LotteryTicket): Observable<Unit> {

        val db = Realm.getDefaultInstance()
        val dbLotteryTicket = db.queryByLocalId(lotteryTicket.localId)
        dbLotteryTicket?.update(db) {
            label = lotteryTicket.label
            number = lotteryTicket.number
            bet = lotteryTicket.bet
            prize = lotteryTicket.prize
            setColorAsEnum(lotteryTicket.color)
            position = lotteryTicket.position

        }
        db.close()

        return Observable.just(Unit)
    }

    override fun updateLotteryTickets(lotteryTickets: List<LotteryTicket>): Observable<Unit> {

        val db = Realm.getDefaultInstance()

        lotteryTickets.map {
            val dbLotteryTicket = db.queryByLocalId(it.localId)
            dbLotteryTicket?.update(db) {
                label = it.label
                number = it.number
                bet = it.bet
                prize = it.prize
                setColorAsEnum(it.color)
                position = it.position
            }
        }

        return Observable.just(Unit)
    }

    override fun deleteLotteryTicket(id: String): Observable<Unit> {

        val db = Realm.getDefaultInstance()
        val managedItem = db.queryByLocalId(id)
        managedItem?.delete(db)
        db.close()

        return Observable.just(Unit)
    }
}