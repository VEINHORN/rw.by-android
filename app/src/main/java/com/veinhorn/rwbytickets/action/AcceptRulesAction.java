package com.veinhorn.rwbytickets.action;

import com.veinhorn.rwbytickets.TicketsApp;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by veinhorn on 1.4.16.
 */
public class AcceptRulesAction implements Action {

    private OkHttpClient httpClient;

    public AcceptRulesAction() {
        httpClient = TicketsApp.httpClient;
    }

    @Override public Response doAction(PurchaseDialog dialog) throws IOException {
        Request request = new Request.Builder().url("https://vk.com").build();
        Response response = httpClient.newCall(request).execute();
        return response;
    }
}
