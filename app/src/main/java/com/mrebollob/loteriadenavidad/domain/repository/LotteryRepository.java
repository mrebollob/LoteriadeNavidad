package com.mrebollob.loteriadenavidad.domain.repository;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.CreateLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.DeleteLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.GetLastUpdatedException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.GetLotteryTicketsException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.UpdateLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.NetworkException;

import java.util.Date;
import java.util.List;

/**
 * @author mrebollob
 */
public interface LotteryRepository {

    void createLotteryTicket(LotteryTicket lotteryTicket) throws CreateLotteryTicketException;

    List<LotteryTicket> getLotteryTickets() throws GetLotteryTicketsException;

    List<LotteryTicket> getChristmasLotteryTickets() throws GetLotteryTicketsException;

    List<LotteryTicket> getChildLotteryTickets() throws GetLotteryTicketsException;

    void updateLotteryTicket(LotteryTicket lotteryTicket) throws UpdateLotteryTicketException;

    void deleteLotteryTicket(int lotteryTicketId) throws DeleteLotteryTicketException;

    LotteryTicket checkLotteryTicketPrize(LotteryTicket lotteryTicket) throws NetworkException;

    Date getLastUpdatedTime() throws GetLastUpdatedException;

    int checkLotteryStatus() throws NetworkException;
}
