package com.mrebollob.loteriadenavidad.domain.entities;

/**
 * @author mrebollob
 */
public class LotteryTicket {

    private int id;
    private int number;
    private int bet;
    private int prize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
