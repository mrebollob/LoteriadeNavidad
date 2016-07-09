package com.mrebollob.loteriadenavidad.domain.repository;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;

import java.util.List;

import rx.Observable;

/**
 * @author mrebollob
 */
public interface LotteryRepository {

    Observable<List<LotteryTicket>> lotteryTickets();

    Observable<LotteryTicket> lotteryTicket(final int lotteryTicketId);
}
