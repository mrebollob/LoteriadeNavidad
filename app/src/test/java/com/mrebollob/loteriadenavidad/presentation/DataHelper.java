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

package com.mrebollob.loteriadenavidad.presentation;

import android.support.annotation.NonNull;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;

import java.util.Arrays;
import java.util.List;

/**
 * @author mrebollob
 */
public class DataHelper {

    @NonNull
    public static List<LotteryTicket> makeLotteryTicketList() {
        return Arrays.asList(
                new LotteryTicket("Number 1", 1, 20f, 100f)
        );
    }
}
