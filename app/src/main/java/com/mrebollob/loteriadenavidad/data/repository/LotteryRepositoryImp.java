package com.mrebollob.loteriadenavidad.data.repository;

import com.mrebollob.loteriadenavidad.domain.entities.LotteryTicket;
import com.mrebollob.loteriadenavidad.domain.repository.LotteryRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author mrebollob
 */
public class LotteryRepositoryImp implements LotteryRepository {

    @Inject
    public LotteryRepositoryImp() {
    }

    @Override
    public Observable<List<LotteryTicket>> getLotteryTickets() {
        return Observable.create((Observable.OnSubscribe<List<LotteryTicket>>) subscriber -> {
            try {
                List<LotteryTicket> lotteryTickets = new ArrayList<>();

                LotteryTicket lotteryTicket = new LotteryTicket("Lottery ticket 1", 0, 1, 2);
                lotteryTickets.add(lotteryTicket);
                lotteryTicket = new LotteryTicket("Lottery ticket 2", 2, 2, 4);
                lotteryTickets.add(lotteryTicket);

                subscriber.onNext(lotteryTickets);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
