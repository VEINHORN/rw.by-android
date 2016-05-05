package com.veinhorn.rwbytickets.auth;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.veinhorn.rwbytickets.R;
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

    private Activity activity;
    private ICreds credentials;

    public AuthLoader(Activity activity, ICreds credentials) {
        this.activity = activity;
        if (credentials.getLogin().equals("") || credentials.getPassword().equals("")) {
            this.credentials = new DefaultCreds(activity);
        } else {
            this.credentials = credentials;
        }
    }

    @Override protected Boolean doInBackground(String... params) {
        try {
            Response signInResponse = new SignInAction(activity, credentials)
                    .doAction(DialogFactory.newInstance());
            return isSignInSuccessful(signInResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isSignInSuccessful(Response res) {
        return res.code() == 200 &&
            "https://poezd.rw.by/wps/myportal/home/rp/buyTicket".equals(res.request().url().toString());
    }

    protected void onPostExecute(Boolean isSignInSuccessful) {
        if (isSignInSuccessful) {
            SharedPreferences prefs = activity.getSharedPreferences(
                    activity.getString(R.string.creds_file_key), activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user.login", credentials.getLogin());
            editor.putString("user.password", credentials.getPassword());
            editor.commit();
            activity.moveTaskToBack(false);
            activity.finish();
        } else {
            Toast.makeText(activity, "Cannot sign in. Try again", Toast.LENGTH_SHORT).show();
        }
    }
}
