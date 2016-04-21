package com.veinhorn.rwbytickets.action;

import com.veinhorn.rwbytickets.purchase.dialog.Dialog;

import java.io.IOException;

/**
 * Created by veinhorn on 30.3.16.
 */
public interface Action <E, T> {
    String BASE_URL = "https://poezd.rw.by";
    String RW_ACTION_ATTR_NAME = "action";

    /** Makes things right */
    E doAction(T dialog) throws IOException, Exception;
}
