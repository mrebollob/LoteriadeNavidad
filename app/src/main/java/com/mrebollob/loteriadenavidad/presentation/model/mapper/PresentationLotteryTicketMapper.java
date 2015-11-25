package com.mrebollob.loteriadenavidad.presentation.model.mapper;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;
import com.mrebollob.loteriadenavidad.presentation.model.mapper.base.Mapper;

/**
 * @author mrebollob
 */
public class PresentationLotteryTicketMapper implements Mapper<LotteryTicket, PresentationLotteryTicket> {


    @Override
    public PresentationLotteryTicket modelToData(LotteryTicket model) {
        if (model == null) {
            return null;
        }

        PresentationLotteryTicket lotteryTicket = new PresentationLotteryTicket();
        lotteryTicket.setId(model.getId());
        lotteryTicket.setLabel(model.getLabel());
        lotteryTicket.setNumber(model.getNumber());
        lotteryTicket.setBet(model.getBet());
        lotteryTicket.setPrize(model.getPrize());

        return lotteryTicket;
    }

    @Override
    public LotteryTicket dataToModel(PresentationLotteryTicket data) {
        if (data == null) {
            return null;
        }

        LotteryTicket lotteryTicket = new LotteryTicket();
        lotteryTicket.setId(data.getId());
        lotteryTicket.setLabel(data.getLabel());
        lotteryTicket.setNumber(data.getNumber());
        lotteryTicket.setBet(data.getBet());
        lotteryTicket.setPrize(data.getPrize());

        return lotteryTicket;
    }
}
