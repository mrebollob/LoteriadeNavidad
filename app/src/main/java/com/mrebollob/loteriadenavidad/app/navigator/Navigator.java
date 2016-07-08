package com.mrebollob.loteriadenavidad.app.navigator;

import android.app.Activity;
import android.content.Intent;

import com.mrebollob.loteriadenavidad.R;
import com.mrebollob.loteriadenavidad.app.modules.about.AboutActivity;
import com.mrebollob.loteriadenavidad.app.modules.lotteryticketform.LotteryTicketFormActivity;
import com.mrebollob.loteriadenavidad.app.modules.main.MainActivity;

/**
 * @author mrebollob
 */
public class Navigator {

    private Activity activity;

    public Navigator() {
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void goToLotteryTicketList() {
        finishCurrentActivity();
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    public void goBackToLotteryTicketList() {
        finishCurrentActivity();
        Intent intent = new Intent(activity, MainActivity.class);
        activity.overridePendingTransition(R.anim.enter_left, R.anim.leave_left);
        activity.startActivity(intent);
    }

    public void goToAddLotteryTicket() {
        finishCurrentActivity();
        Intent intent = new Intent(activity, LotteryTicketFormActivity.class);
        activity.overridePendingTransition(R.anim.enter_right, R.anim.leave_right);
        activity.startActivity(intent);
    }

    public void goToAbout() {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.overridePendingTransition(R.anim.enter_right, R.anim.leave_right);
        activity.startActivity(intent);
    }

    private void finishCurrentActivity() {
        activity.finish();
    }
}