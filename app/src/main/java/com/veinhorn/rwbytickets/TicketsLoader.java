package com.veinhorn.rwbytickets;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.veinhorn.rwbytickets.search.action.Action;
import com.veinhorn.rwbytickets.search.action.SignInAction;

import java.io.IOException;

import okhttp3.OkHttpClient;

/**
 * Created by veinhorn on 21.3.16.
 */
public class TicketsLoader extends AsyncTask<String, Void, String> {

    private static final String TAG = TicketsLoader.class.getName();

    private static final String BASE_URL = "https://poezd.rw.by";
    private static final String SIGN_IN_PAGE_URL = "https://poezd.rw.by/wps/portal/home/login_main";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private Context context;
    private OkHttpClient httpClient;
    private PurchaseDialog dialog;

    public TicketsLoader(Context context, PurchaseDialog dialog) {
        this.context = context;
        httpClient = TicketsApp.httpClient;
        this.dialog = dialog;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Action action = new SignInAction(context);
            action.doAction(dialog);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(context, "Cannot sign in", Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}
