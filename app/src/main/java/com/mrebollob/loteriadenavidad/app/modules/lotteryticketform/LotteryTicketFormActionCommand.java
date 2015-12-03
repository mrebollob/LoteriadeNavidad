package com.mrebollob.loteriadenavidad.app.modules.lotteryticketform;

import android.app.Activity;
import android.content.Intent;

import com.mrebollob.loteriadenavidad.app.ui.ActionCommand;
import com.mrebollob.loteriadenavidad.presentation.model.PresentationLotteryTicket;

/**
 * @author mrebollob
 */
public class LotteryTicketFormActionCommand implements ActionCommand {

    private Activity activity;
    private PresentationLotteryTicket lotteryTicket;

    public LotteryTicketFormActionCommand(Activity activity) {
        this.activity = activity;
    }

    public LotteryTicketFormActionCommand(Activity activity, PresentationLotteryTicket lotteryTicket) {
        this.activity = activity;
        this.lotteryTicket = lotteryTicket;
    }

    @Override
    public void execute() {
        Intent intent = new Intent(activity, LotteryTicketFormActivity.class);
        if (lotteryTicket != null)
            intent.putExtra(LotteryTicketFormActivity.LOTTERY_TICKET_EXTRA, lotteryTicket);
        activity.startActivity(intent);
    }
}