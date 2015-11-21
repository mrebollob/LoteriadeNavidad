package com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.persistors;

import com.mrebollob.loteriadenavidad.data.DatabaseHelper;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.BddLotteryTicket;

import java.sql.SQLException;

/**
 * @author mrebollob
 */
public class LotteryTicketPersistor implements Persistor<BddLotteryTicket> {

    private DatabaseHelper helper;

    public LotteryTicketPersistor(DatabaseHelper helper) {
        this.helper = helper;
    }

    @Override
    public void create(BddLotteryTicket data) throws SQLException {
        if (data != null) {
            helper.getLotteryTicketDao().create(data);
        }
    }

    @Override
    public void update(BddLotteryTicket data) throws SQLException {
        if (data != null) {
            helper.getLotteryTicketDao().update(data);
        }
    }
}