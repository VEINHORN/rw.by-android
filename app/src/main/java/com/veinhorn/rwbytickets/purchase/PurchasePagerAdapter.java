package com.veinhorn.rwbytickets.purchase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;

import com.veinhorn.rwbytickets.purchase.dialog.DialogStatus;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;
import com.veinhorn.rwbytickets.purchase.fragments.RouteFragment;
import com.veinhorn.rwbytickets.purchase.fragments.TrainFragment;

/**
 * Created by veinhorn on 31.3.16.
 */
public class PurchasePagerAdapter extends FragmentPagerAdapter {

    private static PurchaseDialog purchaseDialog;

    public PurchasePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        initPurchaseDialog();
    }

    private void initPurchaseDialog() {
        purchaseDialog = new PurchaseDialog();
        purchaseDialog.setDialogStatus(DialogStatus.SIGN_IN);
    }

    public static PurchaseDialog getPurchaseDialog() {
        return purchaseDialog;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RouteFragment.newInstance(purchaseDialog);
            case 1:
                return TrainFragment.newInstance(purchaseDialog);
            default:
                return RouteFragment.newInstance(purchaseDialog);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
