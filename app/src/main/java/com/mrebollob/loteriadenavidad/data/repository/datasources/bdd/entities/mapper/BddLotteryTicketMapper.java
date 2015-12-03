package com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.mapper;

import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.BddLotteryTicket;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.BddLotteryType;
import com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities.mapper.base.Mapper;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryType;

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
        lotteryTicket.setLabel(model.getLabel());
        lotteryTicket.setNumber(model.getNumber());
        lotteryTicket.setBet(model.getBet());
        lotteryTicket.setPrize(model.getPrize());
        lotteryTicket.setLotteryType(BddLotteryType.valueOf(model.getLotteryType().toString()));

        return lotteryTicket;
    }

    @Override
    public LotteryTicket dataToModel(BddLotteryTicket data) {
        if (data == null) {
            return null;
        }

        LotteryTicket lotteryTicket = new LotteryTicket();
        lotteryTicket.setId(data.getId());
        lotteryTicket.setLabel(data.getLabel());
        lotteryTicket.setNumber(data.getNumber());
        lotteryTicket.setBet(data.getBet());
        lotteryTicket.setPrize(data.getPrize());
        lotteryTicket.setLotteryType(LotteryType.valueOf(data.getLotteryType().toString()));

        return lotteryTicket;
    }
}
