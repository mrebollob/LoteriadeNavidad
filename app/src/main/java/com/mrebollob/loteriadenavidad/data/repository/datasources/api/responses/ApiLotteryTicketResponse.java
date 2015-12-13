package com.mrebollob.loteriadenavidad.data.repository.datasources.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mrebollob.loteriadenavidad.data.repository.datasources.api.entities.ApiLotteryTicket;

/**
 * @author mrebollob
 */
public class ApiLotteryTicketResponse extends ApiLotteryTicket {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("error")
    @Expose
    private int error;

    public int getStatus() {
        return status;
    }

    public int getError() {
        return error;
    }
}
