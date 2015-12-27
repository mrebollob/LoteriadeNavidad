package com.mrebollob.loteriadenavidad.presentation.model;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;

import java.util.List;

/**
 * @author mrebollob
 */
public class LotterySummary {

    private float totalBet;
    private float totalWin;
    private float profit;

    public LotterySummary(List<LotteryTicket> lotteryTickets) {

        for (LotteryTicket lotteryTicket : lotteryTickets) {
            totalBet += lotteryTicket.getBet();
            totalWin += lotteryTicket.getBet() * lotteryTicket.getPrize() / 20;
        }

        profit = totalWin - totalBet;
    }

    public float getTotalBet() {
        return totalBet;
    }

    public float getTotalWin() {
        return totalWin;
    }

    public float getProfit() {
        return profit;
    }
}
