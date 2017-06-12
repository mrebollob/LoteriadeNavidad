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

package com.mrebollob.loteriadenavidad.data.db.model

import com.mrebollob.loteriadenavidad.domain.entities.Color
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

internal const val LOCAL_ID = "localId"
internal const val POSITION = "position"


@RealmClass
open class DbLotteryTicket() : RealmObject() {

    constructor(localId: String, label: String, number: Int?, bet: Float?, prize: Float?,
                colorEnum: Color = Color.WHITE, position: Long) : this() {
        this.localId = localId
        this.label = label
        this.number = number
        this.bet = bet
        this.prize = prize
        this.color = colorEnum.name
        this.position = position
    }


    @PrimaryKey open var localId: String = ""
    open var label: String = ""
    open var number: Int? = null
    open var bet: Float? = null
    open var prize: Float? = null
    @Ignore private var colorEnum: Color = Color.WHITE
    open var color: String = colorEnum.name
    open var position: Long = 0

    fun getColorAsEnum(): Color = Color.valueOf(color)

    fun setColorAsEnum(color: Color) {
        this.color = color.name
    }
}
