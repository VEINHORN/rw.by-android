package com.veinhorn.rwbytickets.action;

import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by veinhorn on 30.3.16.
 */
public interface Action {
    Response doAction(PurchaseDialog dialog) throws IOException;
}
