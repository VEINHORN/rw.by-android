package com.veinhorn.rwbytickets;

import android.app.Application;

import okhttp3.OkHttpClient;

/**
 * Created by veinhorn on 21.3.16.
 */
public class TicketsApp extends Application {
    public static OkHttpClient httpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        httpClient = new OkHttpClient();
    }
}
