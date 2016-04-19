package com.veinhorn.rwbytickets.action;

import android.content.Context;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.TicketsApp;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.purchase.dialog.DialogStatus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by veinhorn on 30.3.16.
 */
public class SignInAction implements Action<Dialog> {

    private static final String SIGN_IN_PAGE_URL = "https://poezd.rw.by/wps/portal/home/login_main";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REMEMBER = "on";

    // From this "input" names form send values to the rw.by server
    private static final String RW_LOGIN_FORM_ID = "login";
    private static final String RW_PASSWORD_FORM_ID = "password";
    private static final String RW_REMEMBER_FORM_ID = "rememberUser";

    private Context context;
    private OkHttpClient httpClient;

    public SignInAction(Context context) {
        this.context = context;
        httpClient = TicketsApp.httpClient;
    }

    // TODO: Add creds object to PurchaseDialog and than pass it here against passing context
    // TODO: Maybe return null if some exception was thrown
    @Override public Response doAction(Dialog dialog) throws IOException {
        // Get credentials from somewhere
        Map<String, String> creds = loadCredentials();
        // Get correct sign in url
        String signInUrl = createSignInUrl(fetchSignInActionUrl());
        // Sign In request creation
        Request signInRequest = createSignInRequest(signInUrl, creds);
        // Do Sign In request
        Response signInResponse = httpClient.newCall(signInRequest).execute();
        // Fill purchase dialog
        fillPurchaseDialog(dialog, signInResponse, creds);

        return signInResponse;
    }

    /** Change dialog status and several other parameters */
    private void fillPurchaseDialog(Dialog dialog,
                                    Response signInResponse, Map<String, String> creds) {
        dialog.setCurrentResponse(signInResponse);
        dialog.setDialogStatus(DialogStatus.ACCEPT_RULES);
        dialog.setCredentials(creds);
    }

    private Request createSignInRequest(String signInUrl, Map<String, String> creds) {
        return new Request.Builder()
                .url(signInUrl)
                .header("Content-Type", "application/x-www-form-urlencoded") // be on the safe side
                .post(createSignInFormBody(creds))
                .build();
    }

    private RequestBody createSignInFormBody(Map<String, String> creds) {
        return new FormBody.Builder()
                .add(RW_LOGIN_FORM_ID, creds.get(LOGIN))
                .add(RW_PASSWORD_FORM_ID, creds.get(PASSWORD))
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

    private Map<String, String> loadCredentials() throws IOException {
        // Read credentials from property file (just for test now)
        // TODO: Make some form for creds input when start application
        Properties props = new Properties();
        props.load(context.getResources().openRawResource(R.raw.creds));
        return createCredsMap(props.getProperty(LOGIN), props.getProperty(PASSWORD));
    }

    private Map<String, String> createCredsMap(String login, String password) {
        Map<String, String> creds = new HashMap<>();
        creds.put(LOGIN, login);
        creds.put(PASSWORD, password);
        return creds;
    }
}
