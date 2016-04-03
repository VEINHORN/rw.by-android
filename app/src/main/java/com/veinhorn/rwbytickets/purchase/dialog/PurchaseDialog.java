package com.veinhorn.rwbytickets.purchase.dialog;

import com.veinhorn.rwbytickets.search.rest.model.Station;

import java.util.Map;

import okhttp3.Response;

/**
 * Created by veinhorn on 30.3.16.
 */
public class PurchaseDialog {

    private Station fromStation;
    private Station toStation;

    private Response currentResponse;
    private DialogStatus dialogStatus;

    private Map<String, String> credentials;

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public Response getCurrentResponse() {
        return currentResponse;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Station getFromStation() {
        return fromStation;
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
