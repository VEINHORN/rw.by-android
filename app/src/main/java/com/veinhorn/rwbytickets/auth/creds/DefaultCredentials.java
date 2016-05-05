package com.veinhorn.rwbytickets.auth.creds;

import android.content.Context;

import com.veinhorn.rwbytickets.R;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by veinhorn on 4.5.16.
 */
public class DefaultCredentials implements ICredentials {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private Properties props;

    public DefaultCredentials(Context context) {
        props = new Properties();
        try {
            props.load(context.getResources().openRawResource(R.raw.creds));
        } catch (IOException e) {
            System.out.println("Cannot load credentials from file");
        }
    }

    @Override public String getLogin() {
        return props.getProperty(LOGIN);
    }

    @Override public String getPassword() {
        return props.getProperty(PASSWORD);
    }
}
