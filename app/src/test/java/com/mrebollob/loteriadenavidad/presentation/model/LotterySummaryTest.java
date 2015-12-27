package com.mrebollob.loteriadenavidad.presentation.model;

import com.mrebollob.loteriadenavidad.presentation.DataHelper;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * @author mrebollob
 */
public class LotterySummaryTest {

    private static final float TOTAL_BET = 40f;
    private static final float TOTAL_WIN = 106.25f;
    private static final float PROFIT = TOTAL_WIN - TOTAL_BET;

    @Test
    public void calculateTotalBet() {

        LotterySummary lotterySummary = new LotterySummary(DataHelper.makeLotteryTicketList());

        assertThat(lotterySummary.getTotalBet(), equalTo(TOTAL_BET));
    }

    @Test
    public void calculateTotalWin() {

        LotterySummary lotterySummary = new LotterySummary(DataHelper.makeLotteryTicketList());

        assertThat(lotterySummary.getTotalWin(), equalTo(TOTAL_WIN));
    }

    @Test
    public void calculateProfit() {

        LotterySummary lotterySummary = new LotterySummary(DataHelper.makeLotteryTicketList());

        assertThat(lotterySummary.getProfit(), equalTo(PROFIT));
    }
}
