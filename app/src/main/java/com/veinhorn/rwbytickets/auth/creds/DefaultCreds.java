package com.veinhorn.rwbytickets.auth.creds;

import android.content.Context;

import com.veinhorn.rwbytickets.R;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by veinhorn on 4.5.16.
 */
public class DefaultCreds implements ICreds {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private Properties props;

    public DefaultCreds(Context context) {
        props = new Properties();
        try {
            props.load(context.getResources().openRawResource(R.raw.creds));
        } catch (IOException e) {
            System.out.println("Cannot load creds from file");
        }
    }

    @Override public String getLogin() {
        return props.getProperty(LOGIN);
    }

    @Override public String getPassword() {
        return props.getProperty(PASSWORD);
    }
}
