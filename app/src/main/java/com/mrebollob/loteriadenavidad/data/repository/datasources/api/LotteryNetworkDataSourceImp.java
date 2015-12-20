package com.mrebollob.loteriadenavidad.data.repository.datasources.api;

import com.google.gson.Gson;
import com.mrebollob.loteriadenavidad.data.repository.datasources.api.responses.ApiLotteryTicketResponse;
import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.LotteryNetworkDataSource;
import com.mrebollob.loteriadenavidad.domain.repository.datasources.exceptions.NetworkException;

/**
 * @author mrebollob
 */
public class LotteryNetworkDataSourceImp implements LotteryNetworkDataSource {

    private LotteryApiService apiService;
    private Gson gson;

    public LotteryNetworkDataSourceImp(LotteryApiService apiService, Gson gson) {
        this.apiService = apiService;
        this.gson = gson;
    }

    @Override
    public LotteryTicket checkChristmasLotteryTicketPrize(LotteryTicket lotteryTicket) {
        try {
            String apiResponse =
                    apiService.checkChristmasLotteryTicket(lotteryTicket.getNumber());

            apiResponse = apiResponse.replace("busqueda=", "");

            ApiLotteryTicketResponse apiLotteryTicketResponse =
                    gson.fromJson(apiResponse, ApiLotteryTicketResponse.class);

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
    public LotteryTicket checkChildLotteryTicketPrize(LotteryTicket lotteryTicket) {
        try {
            String apiResponse =
                    apiService.checkChildLotteryTicket(lotteryTicket.getNumber());

            apiResponse = apiResponse.replace("busqueda=", "");

            ApiLotteryTicketResponse apiLotteryTicketResponse =
                    gson.fromJson(apiResponse, ApiLotteryTicketResponse.class);

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
    public int checkLotteryStatus() throws NetworkException {
        try {
            String apiResponse =
                    apiService.checkChristmasLotteryTicket(7);

            apiResponse = apiResponse.replace("busqueda=", "");

            ApiLotteryTicketResponse apiLotteryTicketResponse =
                    gson.fromJson(apiResponse, ApiLotteryTicketResponse.class);

            return apiLotteryTicketResponse.getStatus();
        } catch (Throwable e) {
            throw new NetworkException();
        }
    }
}
