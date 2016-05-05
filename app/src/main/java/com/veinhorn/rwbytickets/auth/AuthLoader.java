package com.veinhorn.rwbytickets.auth;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.veinhorn.rwbytickets.action.SignInAction;
import com.veinhorn.rwbytickets.auth.creds.CredentialsStorage;
import com.veinhorn.rwbytickets.auth.creds.DefaultCredentials;
import com.veinhorn.rwbytickets.auth.creds.ICredentials;
import com.veinhorn.rwbytickets.purchase.dialog.factory.DialogFactory;

import okhttp3.Response;

/**
 * Created by veinhorn on 3.5.16.
 */
public class AuthLoader extends AsyncTask<String, Void, Boolean> {
    private static final String BUY_URL = "https://poezd.rw.by/wps/myportal/home/rp/buyTicket";
    private Activity activity;
    private ICredentials credentials;

    public AuthLoader(Activity activity, ICredentials credentials) {
        this.activity = activity;
        if (credentials.getLogin().equals("") || credentials.getPassword().equals("")) {
            this.credentials = new DefaultCredentials(activity);
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
        return res.code() == 200 && BUY_URL.equals(res.request().url().toString());
    }

    @Override protected void onPostExecute(Boolean isSignInSuccessful) {
        if (isSignInSuccessful) {
            CredentialsStorage.saveCredentials(activity, credentials);
            activity.moveTaskToBack(false);
            activity.finish();
        } else {
            Toast.makeText(activity, "Cannot sign in. Try again", Toast.LENGTH_SHORT).show();
        }
    }
}
