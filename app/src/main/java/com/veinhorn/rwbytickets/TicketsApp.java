package com.veinhorn.rwbytickets;

import android.app.Application;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

/**
 * Created by veinhorn on 21.3.16.
 */
public class TicketsApp extends Application {
    public static OkHttpClient httpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        // Set up persistent cache that will be stored in shared prefs
        CookieJar cookieJar = new PersistentCookieJar(
                new SetCookieCache(), new SharedPrefsCookiePersistor(getBaseContext())
        );
        // Set up singleton http client
        httpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
    }
}
