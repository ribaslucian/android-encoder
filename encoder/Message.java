package com.example.lucian.sqlite.encoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.lucian.sqlite.EncoderApp;

/**
 * Created by Lucian on 08/06/2017.
 */

public class Message {

    public static AlertDialog.Builder get(Activity mainActivity) {
        return new AlertDialog.Builder(mainActivity)
                .setTitle("Your Alert")
                .setMessage("Your Message")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.log("OK bb");
                    }
                });
    }

    public static void notification(Activity activity, String title, String text) {
        int idNotificacao = 12345;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(activity);
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_info_details);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(text);
        Intent resultIntent = new Intent(activity, activity.getClass());
        PendingIntent resultPendingIntent = PendingIntent.getActivity(EncoderApp.context, 0, resultIntent, 0);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(idNotificacao, mBuilder.build());
    }

}
