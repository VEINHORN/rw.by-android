package com.veinhorn.rwbytickets.auth;

import android.content.Context;
import android.os.AsyncTask;

import com.veinhorn.rwbytickets.action.SignInAction;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.purchase.dialog.factory.DialogFactory;

import okhttp3.Response;

/**
 * Created by veinhorn on 3.5.16.
 */
public class AuthLoader extends AsyncTask<String, Void, Boolean> {

    private Context context;
    private Dialog dialog;

    public AuthLoader(Context context) {
        this.context = context;
        dialog = DialogFactory.newInstance();
    }

    @Override protected Boolean doInBackground(String... params) {
        try {
            Response signInResponse = new SignInAction(context).doAction(dialog);
        } catch (Exception e) {

        }
        return true;
    }

    protected void onPostExecute(Boolean result) {

    }
}
