package com.mrebollob.loteriadenavidad.domain.entities;

/**
 * @author mrebollob
 */
public class LotteryTicket {

    private int id;
    private String label;
    private int number;
    private float bet;
    private float prize;

    public LotteryTicket() {
    }

    public LotteryTicket(String label, int number, float bet, float prize) {
        this.label = label;
        this.number = number;
        this.bet = bet;
        this.prize = prize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getBet() {
        return bet;
    }

    public void setBet(float bet) {
        this.bet = bet;
    }

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }
}
