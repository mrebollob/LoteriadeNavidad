package com.mrebollob.loteriadenavidad.domain.repository.datasources;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.NetworkException;

/**
 * @author mrebollob
 */
public interface LotteryNetworkDataSource {

    LotteryTicket checkChristmasLotteryTicketPrize(LotteryTicket lotteryTicket)throws NetworkException;

    LotteryTicket checkChildLotteryTicketPrize(LotteryTicket lotteryTicket)throws NetworkException;
}
