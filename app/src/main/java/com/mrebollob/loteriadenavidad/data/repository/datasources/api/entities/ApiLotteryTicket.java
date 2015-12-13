package com.mrebollob.loteriadenavidad.data.repository.datasources.api.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author mrebollob
 */
public class ApiLotteryTicket {

    @SerializedName("numero")
    @Expose
    private int number;

    @SerializedName("premio")
    @Expose
    private float prize;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }
}
