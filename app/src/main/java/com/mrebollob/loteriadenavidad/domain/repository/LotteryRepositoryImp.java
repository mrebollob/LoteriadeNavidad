package com.mrebollob.loteriadenavidad.domain.repository;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryType;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.CreateLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.DeleteLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.GetLastUpdatedException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.GetLotteryTicketsException;
import com.mrebollob.loteriadenavidad.domain.interactors.lotterytickets.exceptions.UpdateLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryBddDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryNetworkDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotterySPDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.CreateBddLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.DeleteBddLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.GetBddLotteryTicketsException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.GetSPUpdatedTimeException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.NetworkException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.SetSPUpdatedTimeException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.UpdateBddLotteryTicketException;

import java.util.Date;
import java.util.List;

/**
 * @author mrebollob
 */
public class LotteryRepositoryImp implements LotteryRepository {

    private final LotteryNetworkDataSource networkDataSource;
    private final LotteryBddDataSource bddDataSource;
    private final LotterySPDataSource spDataSource;

    public LotteryRepositoryImp(LotteryNetworkDataSource networkDataSource,
                                LotteryBddDataSource bddDataSource,
                                LotterySPDataSource spDataSource) {
        this.networkDataSource = networkDataSource;
        this.bddDataSource = bddDataSource;
        this.spDataSource = spDataSource;
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
    public List<LotteryTicket> getChristmasLotteryTickets() throws GetLotteryTicketsException {
        List<LotteryTicket> lotteryTickets = null;
        try {
            lotteryTickets = bddDataSource.getChristmasLotteryTickets();
        } catch (GetBddLotteryTicketsException e) {
            throw new GetLotteryTicketsException();
        }
        return lotteryTickets;
    }

    @Override
    public List<LotteryTicket> getChildLotteryTickets() throws GetLotteryTicketsException {
        List<LotteryTicket> lotteryTickets = null;
        try {
            lotteryTickets = bddDataSource.getChildLotteryTickets();
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

    @Override
    public LotteryTicket checkLotteryTicketPrize(LotteryTicket lotteryTicket) throws NetworkException {

        LotteryTicket updatedLotteryTicket = null;

        try {
            if (lotteryTicket.getLotteryType() == LotteryType.CHRISTMAS) {
                updatedLotteryTicket = networkDataSource.checkChristmasLotteryTicketPrize(lotteryTicket);
            } else {
                updatedLotteryTicket = networkDataSource.checkChildLotteryTicketPrize(lotteryTicket);
            }
            bddDataSource.updateLotteryTicket(updatedLotteryTicket);
        } catch (UpdateBddLotteryTicketException e) {
            e.printStackTrace();
        }

        try {
            spDataSource.setLastUpdatedTime(new Date());
        } catch (SetSPUpdatedTimeException e) {
            e.printStackTrace();
        }

        return updatedLotteryTicket;
    }

    @Override
    public Date getLastUpdatedTime() throws GetLastUpdatedException {
        try {
            return spDataSource.getLastUpdatedTime();
        } catch (GetSPUpdatedTimeException e) {
            throw new GetLastUpdatedException();
        }
    }

    @Override
    public int checkLotteryStatus() throws NetworkException {
        return networkDataSource.checkLotteryStatus();
    }
}
