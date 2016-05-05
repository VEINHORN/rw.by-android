package com.veinhorn.rwbytickets.auth.creds;

import android.content.Context;
import android.content.SharedPreferences;

import com.veinhorn.rwbytickets.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by veinhorn on 5.5.16.
 */
public class CredsStorage {
    private static final String LOGIN_KEY = "user.login";
    private static final String PASSWORD_KEY = "user.password";
    private static final String SIGNIN_DATE_KEY = "user.signedin_date";

    /** Returns valid creds or null (if login or password is empty) */
    public static ICreds readCredentials(Context context) {
        SharedPreferences prefs = openSharedPreferences(context);
        String login = prefs.getString(LOGIN_KEY, "");
        String password = prefs.getString(PASSWORD_KEY, "");
        if ("".equals(login) || "".equals(password)) return null;
        return new Creds(login, password);
    }

    /** Saves user credentials in shared preferences */
    public static void saveCredentials(Context context, ICreds creds) {
        SharedPreferences prefs = openSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LOGIN_KEY, creds.getLogin());
        editor.putString(PASSWORD_KEY, creds.getPassword());
        editor.putString(SIGNIN_DATE_KEY, new Date().toString());
        editor.apply();
    }

    public static void clearCredentials(Context context) {
        SharedPreferences prefs = openSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LOGIN_KEY, "");
        editor.putString(PASSWORD_KEY, "");
        editor.putString(SIGNIN_DATE_KEY, "");
        editor.apply();
    }

    public Date getSignInDate(Context context) throws ParseException {
        SharedPreferences prefs = openSharedPreferences(context);
        return new SimpleDateFormat().parse(prefs.getString(SIGNIN_DATE_KEY, ""));
    }

    private static SharedPreferences openSharedPreferences(Context context) {
        return  context.getSharedPreferences(context.getString(R.string.creds_file_key),
                Context.MODE_PRIVATE);
    }
}
