package com.veinhorn.rwbytickets.tickets;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.veinhorn.rwbytickets.action.SignInAction;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by veinhorn on 19.4.16.
 */
public class TicketsLoader extends AsyncTask<String, Void, String> {
    private static final String TAG = TicketsLoader.class.getName();

    private Context context;
    private Dialog dialog;

    public TicketsLoader(Context context, Dialog dialog) {
        this.context = context;
        this.dialog = dialog;
    }

    @Override protected String doInBackground(String... params) {
        try {
            Response signInResponse = new SignInAction(context).doAction(dialog);
            Integer test = 1232121;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(context, "Some exception by the way", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

}
