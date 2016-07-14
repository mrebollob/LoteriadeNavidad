package com.mrebollob.loteriadenavidad.data.net;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author mrebollob
 */
public interface LotteryApi {

    @GET("/v1/public/characters")
    Observable<List<LotteryTicket>> getLotteryTickets();
}
