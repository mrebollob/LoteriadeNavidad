package com.mrebollob.loteriadenavidad.presentation;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;

import java.util.Arrays;
import java.util.List;

/**
 * @author mrebollob
 */
public class DataHelper {

    public static List<LotteryTicket> makeLotteryTicketList() {
        return Arrays.asList(
                new LotteryTicket("Number 1", 1, 20f, 100f, LotteryType.CHRISTMAS),
                new LotteryTicket("Number 2", 2, 10f, 10f, LotteryType.CHRISTMAS),
                new LotteryTicket("Number 3", 1, 2.5f, 0f, LotteryType.CHRISTMAS),
                new LotteryTicket("Number 1", 1, 2.5f, 0f, LotteryType.CHILD),
                new LotteryTicket("Number 2", 2, 5f, 5f, LotteryType.CHILD)
        );
    }
}
