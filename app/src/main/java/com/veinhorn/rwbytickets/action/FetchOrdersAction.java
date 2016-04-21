package com.veinhorn.rwbytickets.action;

import com.veinhorn.rwbytickets.TicketsApp;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.purchase.dialog.DialogStatus;
import com.veinhorn.rwbytickets.tickets.fetchers.OrdersFetcher;
import com.veinhorn.rwbytickets.tickets.model.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by veinhorn on 19.4.16.
 */
public class FetchOrdersAction implements Action<List<Order>, Dialog> {
    private static final String PERSONAL_ACCOUNT_PATH = "/wps/myportal/home/rp/private";

    private OkHttpClient httpClient;

    public FetchOrdersAction() {
        httpClient = TicketsApp.httpClient;
    }

    public List<Order> doAction(Dialog dialog) throws IOException {
        String personalAccountUrl = createPersonalAccountUrl();
        Request personalAccountReq = createPersonalAccountRequest(personalAccountUrl);
        Response personalAccountRes = httpClient.newCall(personalAccountReq).execute();
        return new OrdersFetcher().fetch(personalAccountRes.body().string());
    }

    private void fillDialog(Dialog dialog, Response personalAccountResponse) {
        dialog.setCurrentResponse(personalAccountResponse);
        dialog.setDialogStatus(DialogStatus.PERSONAL_ACCOUNT);
    }

    private Request createPersonalAccountRequest(String personalAccountUrl) {
        return new Request.Builder().url(personalAccountUrl).build();
    }

    private String createPersonalAccountUrl() {
        return BASE_URL + PERSONAL_ACCOUNT_PATH;
    }

}
