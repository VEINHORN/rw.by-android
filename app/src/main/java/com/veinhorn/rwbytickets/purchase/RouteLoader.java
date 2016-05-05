package com.veinhorn.rwbytickets.purchase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.veinhorn.rwbytickets.action.ConfirmRulesAction;
import com.veinhorn.rwbytickets.action.SelectRouteAction;
import com.veinhorn.rwbytickets.action.SignInAction;
import com.veinhorn.rwbytickets.auth.creds.DefaultCredentials;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;
import com.veinhorn.rwbytickets.purchase.fetchers.AvailableTrainsFetcher;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by veinhorn on 21.3.16.
 */
public class RouteLoader extends AsyncTask<String, Void, String> {
    private static final String TAG = RouteLoader.class.getName();

    private Context context;
    private PurchaseDialog dialog;

    public RouteLoader(Context context, PurchaseDialog dialog) {
        this.context = context;
        this.dialog = dialog;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Response signInResponse = new SignInAction(context, new DefaultCredentials(context))
                    .doAction(dialog);
            Response acceptRulesResponse = new ConfirmRulesAction().doAction(dialog);
            Response selectRouteResponse = new SelectRouteAction().doAction(dialog);
            AvailableTrainsFetcher.fetchAvailableTrains(selectRouteResponse.body().string());
            Integer test2 = 345;
            // Here we must parse availible trains

            //
            Integer test3 = 456;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(context, "Jsoup parse error. rw.by page structure changed",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(context, "Smth went wrong in pay dialog", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(context, "Parse error by the way", Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}
