package com.veinhorn.rwbytickets.search.action;

import android.content.Context;

import com.veinhorn.rwbytickets.PurchaseDialog;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by veinhorn on 30.3.16.
 */
public interface Action {
    Response doAction(PurchaseDialog dialog) throws IOException;
}
