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
