package com.veinhorn.rwbytickets.action;

import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

import okhttp3.Response;

/**
 * Created by veinhorn on 3.4.16.
 */
public class SelectRouteAction extends BaseAction {

    @Override public Response doAction(PurchaseDialog dialog) throws Exception {
        Response confirmRulesResponse = dialog.getCurrentResponse();

        return confirmRulesResponse;
    }
}
