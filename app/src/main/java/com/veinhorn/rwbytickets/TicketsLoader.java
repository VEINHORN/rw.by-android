package com.veinhorn.rwbytickets;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.veinhorn.rwbytickets.action.Action;
import com.veinhorn.rwbytickets.action.SignInAction;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Response;

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
            Response signInResponse = action.doAction(dialog);
            Integer test123 = new Integer(234);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(context, "Cannot sign in", Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}
