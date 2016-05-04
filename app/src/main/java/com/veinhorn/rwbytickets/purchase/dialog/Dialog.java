package com.veinhorn.rwbytickets.purchase.dialog;

import com.veinhorn.rwbytickets.auth.creds.ICreds;

import java.util.Map;

import okhttp3.Response;

/**
 * Created by veinhorn on 19.4.16.
 */
public class Dialog {

    /** Represents a response from rw.by on current step **/
    private Response currentResponse;

    /** Stores the current status of the dialog */
    private DialogStatus dialogStatus;

    private ICreds credentials;

    public void setCurrentResponse(Response currentResponse) {
        this.currentResponse = currentResponse;
    }

    public Response getCurrentResponse() {
        return currentResponse;
    }

    public void setDialogStatus(DialogStatus dialogStatus) {
        this.dialogStatus = dialogStatus;
    }

    public DialogStatus getDialogStatus() {
        return dialogStatus;
    }

    public void setCredentials(ICreds credentials) {
        this.credentials = credentials;
    }

    public ICreds getCredentials() {
        return credentials;
    }
}
