package com.mrebollob.loteriadenavidad.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.UUID;

public class SessionManager {
    private final SharedPreferences mSharedPreferences;
    private static final String USER_ID_KEY = "user_id";

    private String mUserId;

    public SessionManager(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public String getUserId() {

        if (TextUtils.isEmpty(mUserId)) {
            return refreshUserId();
        } else {
            return mUserId;
        }
    }

    private String refreshUserId() {
        String userId = mSharedPreferences.getString(USER_ID_KEY, "");
        if (TextUtils.isEmpty(userId)) {
            userId = generateUUID();
            setUserId(userId);
            return userId;
        } else {
            return userId;
        }
    }

    private void setUserId(String userId) {
        mUserId = userId;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_ID_KEY, mUserId);
        editor.apply();
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
