package com.mrebollob.loteriadenavidad.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author mrebollob
 */
public class PresentationLotteryTicket implements Parcelable {

    private int id;
    private int number;
    private int bet;
    private int prize;

    public PresentationLotteryTicket() {
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeInt(number);
        out.writeInt(bet);
        out.writeInt(prize);
    }

    public static final Parcelable.Creator<PresentationLotteryTicket> CREATOR
            = new Parcelable.Creator<PresentationLotteryTicket>() {
        public PresentationLotteryTicket createFromParcel(Parcel in) {
            return new PresentationLotteryTicket(in);
        }

        public PresentationLotteryTicket[] newArray(int size) {
            return new PresentationLotteryTicket[size];
        }
    };

    private PresentationLotteryTicket(Parcel in) {
        id = in.readInt();
        number = in.readInt();
        bet = in.readInt();
        prize = in.readInt();
    }
}
