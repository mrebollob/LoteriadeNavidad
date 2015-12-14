package com.mrebollob.loteriadenavidad.app.util;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mrebollob.loteriadenavidad.R;

/**
 * @author mrebollob
 */
public class AnalyticsManager {

    private static Context mContext;
    private static Tracker mTracker;

    public AnalyticsManager(Context context) {
        mContext = context;
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(mContext);
        mTracker = analytics.newTracker(R.xml.global_tracker);
    }

    private boolean canSend() {
        return mContext != null && mTracker != null;
    }

    public void sendScreenView(String screenName) {
        if (canSend()) {
            mTracker.setScreenName(screenName);
            mTracker.send(new HitBuilders.AppViewBuilder().build());
        }
    }

    public void sendEvent(String category, String action, String label, long value) {
        if (canSend()) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory(category)
                    .setAction(action)
                    .setLabel(label)
                    .setValue(value)
                    .build());
        }
    }

    public void sendEvent(String category, String action, String label) {
        sendEvent(category, action, label, 0);
    }
}
