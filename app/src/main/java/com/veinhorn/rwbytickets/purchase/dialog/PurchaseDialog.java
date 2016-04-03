package com.veinhorn.rwbytickets.purchase.dialog;

import com.veinhorn.rwbytickets.search.rest.model.Station;

import okhttp3.Response;

/**
 * Created by veinhorn on 30.3.16.
 */
public class PurchaseDialog {

    private Station fromStation;
    private Station toStation;

    private Response currentResponse;
    private DialogStatus dialogStatus;

    public Response getCurrentResponse() {
        return currentResponse;
    }

    public void setCurrentResponse(Response currentResponse) {
        this.currentResponse = currentResponse;
    }

    public DialogStatus getDialogStatus() {
        return dialogStatus;
    }

    public void setDialogStatus(DialogStatus dialogStatus) {
        this.dialogStatus = dialogStatus;
    }
}
