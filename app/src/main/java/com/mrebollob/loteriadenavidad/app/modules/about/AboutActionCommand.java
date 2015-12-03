package com.mrebollob.loteriadenavidad.app.modules.about;

import android.app.Activity;
import android.content.Intent;

import com.mrebollob.loteriadenavidad.app.ui.ActionCommand;

/**
 * @author mrebollob
 */
public class AboutActionCommand implements ActionCommand {

    private Activity activity;

    public AboutActionCommand(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void execute() {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }
}