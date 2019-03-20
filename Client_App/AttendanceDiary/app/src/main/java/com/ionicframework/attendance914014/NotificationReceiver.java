package com.ionicframework.attendance914014;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Calendar;

public class NotificationReceiver extends BroadcastReceiver {

    public static final String NOTIFICATION_ID = "notification-id";
    public static final String NOTIFICATION = "notification";
    public static final String NOTIFICATION_CANCEL = "notification-cancel";

    public static final String TAG = "Debugging not pupdated today";

    public NotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager managerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification =  intent.getParcelableExtra(NOTIFICATION);

        int id = intent.getIntExtra(NOTIFICATION_ID,0);
        Boolean cancel = intent.getBooleanExtra(NOTIFICATION_CANCEL,true);

        SharedPreferences settings = context.getSharedPreferences(UpdateActivity.notifications,0);

       /* if (id == 810) {
            Log.d(TAG,"Setting id at 6am");
*//*
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(UpdateActivity.usedToday,false);
            editor.commit();*//*

        } else {*/

            if (cancel) {
                managerCompat.cancel(id);
            } else {
                Log.e(TAG,"Setting notifications");

                managerCompat.notify(id, notification);
            }

      //  }

    }
}
