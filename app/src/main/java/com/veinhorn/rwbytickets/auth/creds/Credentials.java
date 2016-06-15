package com.veinhorn.rwbytickets.auth.creds;

import java.util.Date;

/**
 * Created by veinhorn on 4.5.16.
 */
public class Credentials implements ICredentials {
    private String login;
    private String password;
    private Date loggedIn;

    public Credentials(String login, String password, Date loggedIn) {
        this.login = login;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    @Override public String getLogin() {
        return login;
    }

    @Override public String getPassword() {
        return password;
    }

    @Override public Date getLoggedIn() {
        return loggedIn;
    }
}
