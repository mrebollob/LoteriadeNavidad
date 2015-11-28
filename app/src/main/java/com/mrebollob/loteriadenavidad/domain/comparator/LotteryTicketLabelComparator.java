package com.mrebollob.loteriadenavidad.domain.comparator;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;

import java.util.Comparator;

/**
 * @author mrebollob
 */
public class LotteryTicketLabelComparator implements Comparator<LotteryTicket> {

    @Override
    public int compare(LotteryTicket lhs, LotteryTicket rhs) {
        return lhs.getLabel().compareTo(rhs.getLabel());
    }
}