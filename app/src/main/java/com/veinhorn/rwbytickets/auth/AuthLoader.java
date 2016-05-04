package com.veinhorn.rwbytickets.auth;

import android.content.Context;
import android.os.AsyncTask;

import com.veinhorn.rwbytickets.action.SignInAction;
import com.veinhorn.rwbytickets.auth.creds.DefaultCreds;
import com.veinhorn.rwbytickets.auth.creds.ICreds;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.purchase.dialog.factory.DialogFactory;

import okhttp3.Response;

/**
 * Created by veinhorn on 3.5.16.
 */
public class AuthLoader extends AsyncTask<String, Void, Boolean> {

    private Context context;
    private Dialog dialog;
    private ICreds credentials;

    public AuthLoader(Context context, ICreds credentials) {
        this.context = context;
        dialog = DialogFactory.newInstance();
        this.credentials = credentials;
    }

    @Override protected Boolean doInBackground(String... params) {
        try {
            Response signInResponse = new SignInAction(context, credentials)
                    .doAction(dialog);
            Integer test = 234234;
        } catch (Exception e) {
            Integer test2 = 23423;
        }
        return true;
    }

    protected void onPostExecute(Boolean result) {

    }
}
