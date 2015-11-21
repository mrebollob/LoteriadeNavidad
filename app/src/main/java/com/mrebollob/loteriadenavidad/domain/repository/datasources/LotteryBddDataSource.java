package com.mrebollob.loteriadenavidad.domain.repository.datasources;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.CreateBddLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.DeleteBddLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.GetBddLotteryTicketsException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.UpdateBddLotteryTicketException;

import java.util.List;

/**
 * @author mrebollob
 */
public interface LotteryBddDataSource {

    void createLotteryTicket(LotteryTicket lotteryTicket) throws CreateBddLotteryTicketException;

    List<LotteryTicket> getLotteryTickets() throws GetBddLotteryTicketsException;

    void updateLotteryTicket(LotteryTicket lotteryTicket) throws UpdateBddLotteryTicketException;

    void deleteLotteryTicket(int lotteryTicketId) throws DeleteBddLotteryTicketException;
}
