package com.veinhorn.rwbytickets.search.rest;

import com.veinhorn.rwbytickets.TicketsApp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by veinhorn on 24.3.16.
 */
public final class RetrofitCreator {
    private static final String BASE_URL = "https://poezd.rw.by/wps/PA_eTicketInquire/";

    public static Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(TicketsApp.httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
