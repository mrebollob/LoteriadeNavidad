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

package com.mrebollob.loteriadenavidad.domain.entities

import com.mrebollob.loteriadenavidad.utils.PositionsFactory
import java.util.*

const val LOCAL_ID = "localId"

fun generateLocalId(): String = LOCAL_ID + "_" + UUID.randomUUID().toString().replace("-".toRegex(), "")

enum class Color {
    RED, YELLOW, GREEN, BLUE, WHITE
}

data class LotteryTicket(val localId: String = generateLocalId(),
                         val label: String = "",
                         val number: Int? = null,
                         val bet: Float? = null,
                         val prize: Float? = null,
                         val color: Color = Color.WHITE,
                         val position: Long = object : PositionsFactory {}.newPosition()) {

}
