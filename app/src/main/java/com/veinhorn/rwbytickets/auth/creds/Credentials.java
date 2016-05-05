package com.veinhorn.rwbytickets.auth.creds;

/**
 * Created by veinhorn on 4.5.16.
 */
public class Credentials implements ICredentials {
    private String login;
    private String password;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override public String getLogin() {
        return login;
    }

    @Override public String getPassword() {
        return password;
    }
}
