package com.mrebollob.loteriadenavidad.data.repository.datasources.api;

import com.mrebollob.loteriadenavidad.data.repository.datasources.api.responses.ApiLotteryTicketResponse;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.model.NetworkException;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryNetworkDataSource;

/**
 * @author mrebollob
 */
public class LotteryNetworkDataSourceImp implements LotteryNetworkDataSource {

    private LotteryApiService apiService;

    public LotteryNetworkDataSourceImp(LotteryApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public LotteryTicket checkChristmasLotteryTicket(LotteryTicket lotteryTicket) {
        try {
            ApiLotteryTicketResponse apiLotteryTicketResponse =
                    apiService.checkChristmasLotteryTicket(lotteryTicket.getNumber());

            if (apiLotteryTicketResponse.getError() != 0) {
                lotteryTicket.setPrize(-1);
            } else {
                lotteryTicket.setPrize(apiLotteryTicketResponse.getPrize());
            }
            return lotteryTicket;
        } catch (Throwable e) {
            throw new NetworkException();
        }
    }

    @Override
    public LotteryTicket checkChildLotteryTicket(LotteryTicket lotteryTicket) {
        return null;
    }
}
