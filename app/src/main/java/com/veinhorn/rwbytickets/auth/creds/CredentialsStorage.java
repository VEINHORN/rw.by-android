package com.veinhorn.rwbytickets.auth.creds;

import android.content.Context;
import android.content.SharedPreferences;

import com.veinhorn.rwbytickets.R;

import java.util.Date;

/**
 * Created by veinhorn on 5.5.16.
 */
public class CredentialsStorage {
    private static final String LOGIN_KEY = "user.login";
    private static final String PASSWORD_KEY = "user.password";
    private static final String LOGGED_IN_KEY = "user.loggedin";
    private static final String SIGNIN_DATE_KEY = "user.signedin_date";

    /** Returns valid creds or null (if login or password is empty) */
    public static ICredentials readCredentials(Context context) {
        SharedPreferences prefs = openSharedPreferences(context);
        String login = prefs.getString(LOGIN_KEY, "");
        String password = prefs.getString(PASSWORD_KEY, "");
        Date loggedIn = new Date(prefs.getLong(LOGGED_IN_KEY, new Date().getTime()));
        if ("".equals(login) || "".equals(password)) return null;
        return new Credentials(login, password, loggedIn);
    }

    /** Saves user credentials in shared preferences */
    public static void saveCredentials(Context context, ICredentials creds) {
        SharedPreferences prefs = openSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LOGIN_KEY, creds.getLogin());
        editor.putString(PASSWORD_KEY, creds.getPassword());
        editor.putString(SIGNIN_DATE_KEY, creds.getLogin());
        editor.apply();
    }

    public static void clearCredentials(Context context) {
        SharedPreferences prefs = openSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(LOGIN_KEY);
        editor.remove(PASSWORD_KEY);
        editor.remove(SIGNIN_DATE_KEY);
        editor.apply();
    }

    private static SharedPreferences openSharedPreferences(Context context) {
        return  context.getSharedPreferences(context.getString(R.string.creds_file_key),
                Context.MODE_PRIVATE);
    }
}
