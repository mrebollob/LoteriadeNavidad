package com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities;

import com.j256.ormlite.field.DatabaseField;

/**
 * @author mrebollob
 */
public class BddLotteryTicket {

    public static final String FIELD_ID = "id";

    @DatabaseField(generatedId = true, columnName = FIELD_ID)
    private int id;

    @DatabaseField
    private String label;

    @DatabaseField
    private int number;

    @DatabaseField
    private int bet;

    @DatabaseField
    private int prize;

    @DatabaseField
    private BddLotteryType lotteryType;

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

    public BddLotteryType getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(BddLotteryType lotteryType) {
        this.lotteryType = lotteryType;
    }
}
