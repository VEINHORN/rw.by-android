package com.veinhorn.rwbytickets.auth.creds;

import java.util.Date;

/**
 * Created by veinhorn on 4.5.16.
 */
public interface ICredentials {
    String getLogin();
    String getPassword();
    Date getLoggedIn();
}
