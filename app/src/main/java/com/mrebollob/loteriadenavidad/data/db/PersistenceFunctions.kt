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

package com.mrebollob.loteriadenavidad.data.db


import android.content.Context
import com.mrebollob.loteriadenavidad.R
import com.mrebollob.loteriadenavidad.data.db.model.DbLotteryTicket
import com.mrebollob.loteriadenavidad.data.db.model.LOCAL_ID
import com.mrebollob.loteriadenavidad.data.db.model.POSITION
import io.realm.*

fun setupPersistence(context: Context, dbName: String = context.getString(R.string.database_name)) {
    configureDb(context, dbName)
}

private fun configureDb(context: Context, dbName: String = context.getString(R.string.database_name)) {
    Realm.init(context)
    val realmConfig = RealmConfiguration.Builder()
            .name(dbName)
            .deleteRealmIfMigrationNeeded()
            .build()
    Realm.setDefaultConfiguration(realmConfig)
}

fun Realm.queryAllDbLotteryTicketsSortedByPosition(): RealmResults<DbLotteryTicket> =
        this.where(DbLotteryTicket::class.java).findAll().sort(POSITION)

fun Realm.queryByLocalId(id: String): DbLotteryTicket? =
        this.where(DbLotteryTicket::class.java).equalTo(LOCAL_ID, id).findFirst()

fun DbLotteryTicket.insertOrUpdate(db: Realm): DbLotteryTicket {
    val managedItem = db.insertOrUpdateInTransaction(this)
    return managedItem
}

fun <T : RealmModel> Realm.insertOrUpdateInTransaction(model: T): T =
        with(this) {
            beginTransaction()
            val managedItem = copyToRealmOrUpdate(model)
            commitTransaction()
            return managedItem
        }

fun DbLotteryTicket.update(db: Realm, changes: (DbLotteryTicket.() -> Unit)): DbLotteryTicket {
    executeTransaction(db) { this.changes() }
    return this
}

private fun executeTransaction(db: Realm, changes: () -> Unit) {
    db.executeTransaction { changes() }
}

fun DbLotteryTicket.delete(db: Realm) {
    executeTransaction(db) { RealmObject.deleteFromRealm(this) }
}