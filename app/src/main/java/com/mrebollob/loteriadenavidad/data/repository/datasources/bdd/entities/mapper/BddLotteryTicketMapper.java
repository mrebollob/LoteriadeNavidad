package com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.mapper;

import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.BddLotteryTicket;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.mapper.base.Mapper;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;

/**
 * @author mrebollob
 */
public class BddLotteryTicketMapper implements Mapper<LotteryTicket, BddLotteryTicket> {


    @Override
    public BddLotteryTicket modelToData(LotteryTicket model) {
        if (model == null) {
            return null;
        }

        BddLotteryTicket lotteryTicket = new BddLotteryTicket();
        lotteryTicket.setId(model.getId());
        lotteryTicket.setNumber(model.getNumber());
        lotteryTicket.setBet(model.getBet());
        lotteryTicket.setPrize(model.getPrize());

        return lotteryTicket;
    }

    @Override
    public LotteryTicket dataToModel(BddLotteryTicket data) {
        if (data == null) {
            return null;
        }

        LotteryTicket lotteryTicket = new LotteryTicket();
        lotteryTicket.setId(data.getId());
        lotteryTicket.setNumber(data.getNumber());
        lotteryTicket.setBet(data.getBet());
        lotteryTicket.setPrize(data.getPrize());

        return lotteryTicket;
    }
}
