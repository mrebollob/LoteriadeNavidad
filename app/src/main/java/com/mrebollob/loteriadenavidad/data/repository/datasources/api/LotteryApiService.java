package com.mrebollob.loteriadenavidad.data.repository.datasources.api;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author mrebollob
 */
public interface LotteryApiService {

    String CHRISTMAS_API = "/LoteriaNavidadPremiados";
    String CHILD_API = "/LoteriaNinoPremiados";

    @GET(CHRISTMAS_API)
    String  checkChristmasLotteryTicket(@Query("n") int number);

    @GET(CHILD_API)
    String checkChildLotteryTicket(@Query("n") int number);
}