package com.supermeetup.supermeetup;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.supermeetup.supermeetup.network.MeetupClient;

import io.fabric.sdk.android.Fabric;

public class MeetupApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());

        MeetupApp.context = this;
    }

    public static MeetupClient getRestClient(@NonNull Context context) {
        MeetupClient meetupClient = (MeetupClient) MeetupClient.getInstance(MeetupClient.class, MeetupApp.context);

        return meetupClient;
    }
}
