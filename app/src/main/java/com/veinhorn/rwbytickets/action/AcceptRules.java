package com.veinhorn.rwbytickets.action;

import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

import okhttp3.Response;

/**
 * Created by veinhorn on 1.4.16.
 */
public class AcceptRules implements Action {

    @Override public Response doAction(PurchaseDialog dialog) {
        return new Response();
    }
}
