package com.mrebollob.loteriadenavidad.domain.repository;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.CreateLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.DeleteLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.GetLotteryTicketsException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.UpdateLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryBddDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryNetworkDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.CreateBddLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.DeleteBddLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.GetBddLotteryTicketsException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.UpdateBddLotteryTicketException;

import java.util.List;

/**
 * @author mrebollob
 */
public class LotteryRepositoryImp implements LotteryRepository {

    private final LotteryNetworkDataSource networkDataSource;
    private final LotteryBddDataSource bddDataSource;

    public LotteryRepositoryImp(LotteryNetworkDataSource networkDataSource,
                                LotteryBddDataSource bddDataSource) {
        this.networkDataSource = networkDataSource;
        this.bddDataSource = bddDataSource;
    }

    @Override
    public void createLotteryTicket(LotteryTicket lotteryTicket) throws CreateLotteryTicketException {
        try {
            bddDataSource.createLotteryTicket(lotteryTicket);
        } catch (CreateBddLotteryTicketException e) {
            throw new CreateLotteryTicketException();
        }
    }

    @Override
    public List<LotteryTicket> getLotteryTickets() throws GetLotteryTicketsException {
        List<LotteryTicket> lotteryTickets = null;
        try {
            lotteryTickets = bddDataSource.getLotteryTickets();
        } catch (GetBddLotteryTicketsException e) {
            throw new GetLotteryTicketsException();
        }
        return lotteryTickets;
    }

    @Override
    public void updateLotteryTicket(LotteryTicket lotteryTicket) throws UpdateLotteryTicketException {
        try {
            bddDataSource.updateLotteryTicket(lotteryTicket);
        } catch (UpdateBddLotteryTicketException e) {
            throw new UpdateLotteryTicketException();
        }
    }

    @Override
    public void deleteLotteryTicket(int lotteryTicketId) throws DeleteLotteryTicketException {
        try {
            bddDataSource.deleteLotteryTicket(lotteryTicketId);
        } catch (DeleteBddLotteryTicketException e) {
            throw new DeleteLotteryTicketException();
        }
    }
}
