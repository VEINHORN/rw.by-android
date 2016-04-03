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
public class AcceptRulesAction extends BaseAction {

    private static final String RW_INPUT_CHECKBOX_ID = "viewns_7_48QFVAUK6HA180IQAQVJU80004_:form1:conf";

    private OkHttpClient httpClient;

    public AcceptRulesAction() {
        httpClient = TicketsApp.httpClient;
    }

    @Override public Response doAction(PurchaseDialog dialog) throws IOException {
        Response signInResponse = dialog.getCurrentResponse(); // get sign in response
        //String acceptRulesUrl = createAcceptRulesUrl();
        //Request acceptRulesReq = createAcceptRulesRequest(acceptRulesUrl);
        //Response acceptResponse = httpClient.newCall(acceptRulesReq).execute();
        // Request request = new Request.Builder().url("https://vk.com").build();
        // Response response = httpClient.newCall(request).execute();
        return signInResponse;
    }
}
