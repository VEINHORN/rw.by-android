package com.veinhorn.rwbytickets;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.veinhorn.rwbytickets.action.AcceptRulesAction;
import com.veinhorn.rwbytickets.action.SignInAction;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by veinhorn on 21.3.16.
 */
public class RouteLoader extends AsyncTask<String, Void, String> {

    private static final String TAG = RouteLoader.class.getName();

    private Context context;
    private OkHttpClient httpClient;
    private PurchaseDialog purchaseDialog;

    public RouteLoader(Context context, PurchaseDialog purchaseDialog) {
        this.context = context;
        httpClient = TicketsApp.httpClient;
        this.purchaseDialog = purchaseDialog;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Response signInResponse = new SignInAction(context).doAction(purchaseDialog);
            Response acceptRulesResponse = new AcceptRulesAction().doAction(purchaseDialog);
            Integer test2 = 345;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(context, "Something went in pay dialog", Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}
