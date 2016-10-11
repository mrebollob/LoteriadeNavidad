/*
 * Copyright 2016 Manuel Rebollo Báez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrebollob.loteriadenavidad.app.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

public class FeedbackUtils {
    private static final String FEEDBACK_CHOOSER_TITLE = "Envianos un email con tus comentarios";
    private static final String EMAIL_ADDRESS = "mrebollob.developer@gmail.com";

    public static void askForFeedback(Context context) {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, getFeedbackEmailAddress());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                getFeedbackEmailSubject(context));
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                getFeedbackDeviceInformation(context));
        context.startActivity(Intent.createChooser(emailIntent,
                FEEDBACK_CHOOSER_TITLE));
    }

    private static String[] getFeedbackEmailAddress() {
        return new String[]{EMAIL_ADDRESS};
    }

    private static String getFeedbackEmailSubject(Context context) {
        return getApplicationName(context) + "v" + getAppVersion(context);
    }

    private static String getApplicationName(Context context) {
        return context.getString(context.getApplicationInfo().labelRes);
    }

    private static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "vX.XX";
        }
    }

    private static String getFeedbackDeviceInformation(Context context) {
        StringBuilder emailMessage = new StringBuilder();
        emailMessage.append("\n\n_________________");
        emailMessage.append("\n\nInformación del dispositivo:\n\n");
        emailMessage.append(getHandsetInformation(context));
        emailMessage
                .append("\nPor favor, deje estos datos en el correo electrónico para ayudarnos con los problemas "
                        + "en la aplicación.\nPuedes escribir por encima o por debajo. \n\n");
        emailMessage.append("_________________\n\n");
        return emailMessage.toString();
    }

    private static String getHandsetInformation(Context context) {
        StringBuilder handsetInfoBuilder = new StringBuilder();
        handsetInfoBuilder.append("Bootloader: " + Build.BOOTLOADER);
        handsetInfoBuilder.append("\nBrand: " + Build.BRAND);
        handsetInfoBuilder.append("\nDevice: " + Build.DEVICE);
        handsetInfoBuilder.append("\nManufacturer: " + Build.MANUFACTURER);
        handsetInfoBuilder.append("\nModel: " + Build.MODEL);
        handsetInfoBuilder.append("\nScreen Density: "
                + getDeviceDensity(context));
        handsetInfoBuilder
                .append("\nVersion SDK int: " + Build.VERSION.SDK_INT);
        handsetInfoBuilder.append("\nVersion codename: "
                + Build.VERSION.CODENAME);
        handsetInfoBuilder.append("\nVersion incremental: "
                + Build.VERSION.INCREMENTAL);
        handsetInfoBuilder.append("\n");
        return handsetInfoBuilder.toString();
    }

    private static float getDeviceDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}