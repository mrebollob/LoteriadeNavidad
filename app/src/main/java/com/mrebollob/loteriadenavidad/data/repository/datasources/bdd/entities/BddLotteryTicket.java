package com.mrebollob.loteriadenavidad.data.repository.datasources.bdd.entities;

import com.j256.ormlite.field.DatabaseField;

/**
 * @author mrebollob
 */
public class BddLotteryTicket {

    public static final String FIELD_ID = "id";
    public static final String FIELD_TYPE = "type";

    @DatabaseField(generatedId = true, columnName = FIELD_ID)
    private int id;

    @DatabaseField
    private String label;

    @DatabaseField
    private int number;

    @DatabaseField
    private float bet;

    @DatabaseField
    private float prize;

    @DatabaseField(columnName = FIELD_TYPE)
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

    public BddLotteryType getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(BddLotteryType lotteryType) {
        this.lotteryType = lotteryType;
    }
}
