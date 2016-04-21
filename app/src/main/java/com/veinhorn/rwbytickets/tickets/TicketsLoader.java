package com.veinhorn.rwbytickets.tickets;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.veinhorn.rwbytickets.action.FetchOrdersAction;
import com.veinhorn.rwbytickets.action.SignInAction;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.tickets.model.Order;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

/**
 * Created by veinhorn on 19.4.16.
 */
public class TicketsLoader extends AsyncTask<String, Void, List<Order>> {
    private static final String TAG = TicketsLoader.class.getName();

    private Context context;
    private Dialog dialog;
    private TicketsAdapter ticketsAdapter;
    private ProgressBar progressBar;

    public TicketsLoader(Context context, Dialog dialog, TicketsAdapter ticketsAdapter,
                         ProgressBar progressBar) {
        this.context = context;
        this.dialog = dialog;
        this.ticketsAdapter = ticketsAdapter;
        this.progressBar = progressBar;
    }

    @Override protected void onPreExecute () {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override protected List<Order> doInBackground(String... params) {
        try {
            Response signInResponse = new SignInAction(context).doAction(dialog);
            return new FetchOrdersAction().doAction(dialog);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(context, "Some exception by the way", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override protected void onPostExecute(List<Order> orders) {
        progressBar.setVisibility(View.INVISIBLE);
        if (orders != null) {
            ticketsAdapter.updateOrders(orders);
            ticketsAdapter.notifyDataSetChanged();
        }
    }
}
