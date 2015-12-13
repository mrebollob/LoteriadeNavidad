package com.mrebollob.loteriadenavidad.data.repository.datasources.api;

import com.mrebollob.loteriadenavidad.data.repository.datasources.api.responses.ApiLotteryTicketResponse;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author mrebollob
 */
public interface LotteryApiService {

    String CHRISTMAS_API = "/LoteriaNavidadPremiados";
    String CHILD_API = "/LoteriaNinoPremiados";

    @GET(CHRISTMAS_API + "?n={number}")
    ApiLotteryTicketResponse checkChristmasLotteryTicket(@Path("number") int number);

    @GET(CHILD_API + "?n={number}")
    ApiLotteryTicketResponse checkChildLotteryTicket(@Path("number") int number);
}