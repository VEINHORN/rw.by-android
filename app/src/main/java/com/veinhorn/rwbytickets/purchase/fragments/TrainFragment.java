package com.veinhorn.rwbytickets.purchase.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.purchase.AvailibleTrainsAdapter;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 31.3.16.
 */
public class TrainFragment extends Fragment {

    @Bind(R.id.availableTrainsListView) protected ListView availableTrainsListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_train, container, false);
        ButterKnife.bind(this, rootView);

        availableTrainsListView.setAdapter(new AvailibleTrainsAdapter(getActivity()));

        return rootView;
    }

    public static TrainFragment newInstance(PurchaseDialog purchaseDialog) {
        TrainFragment fragment = new TrainFragment();
        // Bundle bundle = new Bundle();
        // bundle.put
        return fragment;
    }
}
