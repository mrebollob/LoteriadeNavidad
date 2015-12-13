package com.mrebollob.loteriadenavidad.domain.repository.datasources;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;

/**
 * @author mrebollob
 */
public interface LotteryNetworkDataSource {

    LotteryTicket checkChristmasLotteryTicket(LotteryTicket lotteryTicket);

    LotteryTicket checkChildLotteryTicket(LotteryTicket lotteryTicket);
}
