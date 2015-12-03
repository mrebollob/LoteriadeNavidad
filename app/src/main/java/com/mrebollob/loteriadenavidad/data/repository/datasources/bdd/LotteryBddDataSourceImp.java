package com.mrebollob.loteriadenavidad.data.repository.datasources.bdd;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.BddLotteryTicket;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.BddLotteryType;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.mapper.BddLotteryTicketMapper;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.mapper.base.ListMapper;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.persistors.Persistor;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryBddDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.CreateBddLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.DeleteBddLotteryTicketException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.GetBddLotteryTicketsException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.UpdateBddLotteryTicketException;

import java.util.List;

/**
 * @author mrebollob
 */
public class LotteryBddDataSourceImp implements LotteryBddDataSource {

    private static final BddLotteryTicketMapper
            BDD_LOTTERY_TICKET_MAPPER = new BddLotteryTicketMapper();
    private static final ListMapper<LotteryTicket, BddLotteryTicket>
            BDD_LOTTERY_TICKET_LIST_MAPPER = new ListMapper<>(BDD_LOTTERY_TICKET_MAPPER);

    private final Dao<BddLotteryTicket, Integer> daoLotteryTickets;
    private final Persistor<BddLotteryTicket> persistor;

    public LotteryBddDataSourceImp(Persistor<BddLotteryTicket> persistor,
                                   Dao<BddLotteryTicket, Integer> daoLotteryTickets) {
        this.daoLotteryTickets = daoLotteryTickets;
        this.persistor = persistor;
    }

    @Override
    public void createLotteryTicket(LotteryTicket lotteryTicket)
            throws CreateBddLotteryTicketException {
        try {
            BddLotteryTicket bddLotteryTicket =
                    BDD_LOTTERY_TICKET_MAPPER.modelToData(lotteryTicket);
            persistor.create(bddLotteryTicket);
        } catch (Throwable e) {
            throw new CreateBddLotteryTicketException();
        }
    }

    @Override
    public List<LotteryTicket> getLotteryTickets() throws GetBddLotteryTicketsException {
        try {
            List<BddLotteryTicket> lotteryTickets = daoLotteryTickets.queryForAll();
            return BDD_LOTTERY_TICKET_LIST_MAPPER.dataToModel(lotteryTickets);
        } catch (Throwable e) {
            throw new GetBddLotteryTicketsException();
        }
    }

    @Override
    public List<LotteryTicket> getChristmasLotteryTickets() throws GetBddLotteryTicketsException {
        try {
            List<BddLotteryTicket> lotteryTickets =
                    daoLotteryTickets.queryForEq(BddLotteryTicket.FIELD_TYPE, BddLotteryType.CHRISTMAS);
            return BDD_LOTTERY_TICKET_LIST_MAPPER.dataToModel(lotteryTickets);
        } catch (Throwable e) {
            throw new GetBddLotteryTicketsException();
        }
    }

    @Override
    public List<LotteryTicket> getChildLotteryTickets() throws GetBddLotteryTicketsException {
        try {
            List<BddLotteryTicket> lotteryTickets =
                    daoLotteryTickets.queryForEq(BddLotteryTicket.FIELD_TYPE, BddLotteryType.CHILD);
            return BDD_LOTTERY_TICKET_LIST_MAPPER.dataToModel(lotteryTickets);
        } catch (Throwable e) {
            throw new GetBddLotteryTicketsException();
        }
    }

    @Override
    public void updateLotteryTicket(LotteryTicket lotteryTicket)
            throws UpdateBddLotteryTicketException {
        try {
            BddLotteryTicket bddLotteryTicket =
                    BDD_LOTTERY_TICKET_MAPPER.modelToData(lotteryTicket);
            persistor.update(bddLotteryTicket);
        } catch (Throwable e) {
            throw new UpdateBddLotteryTicketException();
        }
    }

    @Override
    public void deleteLotteryTicket(int lotteryTicketId) throws DeleteBddLotteryTicketException {
        try {
            DeleteBuilder<BddLotteryTicket, Integer> deleteBuilder =
                    daoLotteryTickets.deleteBuilder();
            deleteBuilder.where().in(BddLotteryTicket.FIELD_ID, lotteryTicketId);
            deleteBuilder.delete();
        } catch (Throwable e) {
            throw new DeleteBddLotteryTicketException();
        }
    }
}
