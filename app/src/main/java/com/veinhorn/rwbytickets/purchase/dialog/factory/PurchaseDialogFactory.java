package com.veinhorn.rwbytickets.purchase.dialog.factory;

import com.veinhorn.rwbytickets.purchase.dialog.DialogStatus;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

/**
 * Created by veinhorn on 19.4.16.
 */
public class PurchaseDialogFactory {

    private PurchaseDialogFactory() {}

    public static PurchaseDialog newInstance() {
        PurchaseDialog dialog = new PurchaseDialog();
        dialog.setDialogStatus(DialogStatus.SIGN_IN);
        return dialog;
    }

}
