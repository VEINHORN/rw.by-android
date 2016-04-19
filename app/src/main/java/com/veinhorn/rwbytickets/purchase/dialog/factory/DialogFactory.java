package com.veinhorn.rwbytickets.purchase.dialog.factory;

import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.purchase.dialog.DialogStatus;

/**
 * Created by veinhorn on 19.4.16.
 */
public class DialogFactory {

    private DialogFactory() {}

    public static Dialog newInstance() {
        Dialog dialog = new Dialog();
        dialog.setDialogStatus(DialogStatus.SIGN_IN);
        return dialog;
    }
}
