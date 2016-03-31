package com.veinhorn.rwbytickets.purchase.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

/**
 * Created by veinhorn on 31.3.16.
 */
public class TrainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_train, container, false);

        return rootView;
    }

    public static TrainFragment newInstance(PurchaseDialog purchaseDialog) {
        TrainFragment fragment = new TrainFragment();
        // Bundle bundle = new Bundle();
        // bundle.put
        return fragment;
    }
}
