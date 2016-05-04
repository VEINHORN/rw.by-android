package com.veinhorn.rwbytickets.action;

import android.content.Context;

import com.veinhorn.rwbytickets.TicketsApp;
import com.veinhorn.rwbytickets.auth.creds.ICreds;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.purchase.dialog.DialogStatus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by veinhorn on 30.3.16.
 */
public class SignInAction implements Action<Response, Dialog> {
    private static final String SIGN_IN_PAGE_URL = "https://poezd.rw.by/wps/portal/home/login_main";
    private static final String REMEMBER = "on";

    // From this "input" names form send values to the rw.by server
    private static final String RW_LOGIN_FORM_ID = "login";
    private static final String RW_PASSWORD_FORM_ID = "password";
    private static final String RW_REMEMBER_FORM_ID = "rememberUser";

    private Context context;
    private OkHttpClient httpClient;
    private ICreds credentials;

    public SignInAction(Context context, ICreds credentials) {
        this.context = context;
        httpClient = TicketsApp.httpClient;
        this.credentials = credentials;
    }

    // TODO: Add creds object to PurchaseDialog and than pass it here against passing context
    // TODO: Maybe return null if some exception was thrown
    @Override public Response doAction(Dialog dialog) throws IOException {
        // Get correct sign in url
        String signInUrl = createSignInUrl(fetchSignInActionUrl());
        // Sign In request creation
        Request signInRequest = createSignInRequest(signInUrl);
        // Do Sign In request
        Response signInResponse = httpClient.newCall(signInRequest).execute();
        // Fill purchase dialog
        fillDialog(dialog, signInResponse);

        return signInResponse;
    }

    /** Change dialog status and several other parameters */
    private void fillDialog(Dialog dialog,
                                    Response signInResponse) {
        dialog.setCurrentResponse(signInResponse);
        dialog.setDialogStatus(DialogStatus.ACCEPT_RULES);
        dialog.setCredentials(credentials);
    }

    private Request createSignInRequest(String signInUrl) {
        return new Request.Builder()
                .url(signInUrl)
                .header("Content-Type", "application/x-www-form-urlencoded") // be on the safe side
                .post(createSignInFormBody())
                .build();
    }

    private RequestBody createSignInFormBody() {
        return new FormBody.Builder()
                .add(RW_LOGIN_FORM_ID, credentials.getLogin())
                .add(RW_PASSWORD_FORM_ID, credentials.getPassword())
                // .add(RW_REMEMBER_FORM_ID, creds.get(REMEMBER)) // "on" or don't send
                .build();
    }

    private String createSignInUrl(String signInAction) {
        return BASE_URL + signInAction;
    }

    private String fetchSignInActionUrl() throws IOException {
        Request req = new Request.Builder().url(SIGN_IN_PAGE_URL).build();
        Response res = httpClient.newCall(req).execute();
        Document document = Jsoup.parse(res.body().string());
        Element loginForm = document.getElementById(RW_LOGIN_FORM_ID);
        return loginForm.attr(RW_ACTION_ATTR_NAME);
    }
}
