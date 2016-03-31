package com.veinhorn.rwbytickets.purchase.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;
import com.veinhorn.rwbytickets.search.StationAutoCompleteAdapter;
import com.veinhorn.rwbytickets.search.rest.model.Station;
import com.veinhorn.rwbytickets.search.view.DelayAutoCompleteTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by veinhorn on 31.3.16.
 */
public class RouteFragment extends Fragment {
    private static final int DEFAULT_THRESHOLD = 2;

    @Bind(R.id.fromStationView) protected DelayAutoCompleteTextView fromStationView;
    @Bind(R.id.toStationView) protected DelayAutoCompleteTextView toStationView;
    @Bind(R.id.continueButton) protected Button continueButton;

    //private PurchaseDialog purchaseDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_route, container, false);
        ButterKnife.bind(this, rootView);

        fromStationView.setThreshold(DEFAULT_THRESHOLD);
        fromStationView.setAdapter(new StationAutoCompleteAdapter(getActivity()));
        fromStationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station station = (Station) parent.getItemAtPosition(position);
                fromStationView.setText(station.getName());
            }
        });

        toStationView.setThreshold(DEFAULT_THRESHOLD);
        toStationView.setAdapter(new StationAutoCompleteAdapter(getActivity()));
        toStationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station station = (Station) parent.getItemAtPosition(position);
                toStationView.setText(station.getName());
            }
        });

        return rootView;
    }

    @OnClick(R.id.continueButton) public void continuePurchase() {

        // Init purchase dialog
        // purchaseDialog = new PurchaseDialog();
        // new TicketsLoader(this, purchaseDialog).execute();
    }

    public static RouteFragment newInstance(PurchaseDialog purchaseDialog) {
        RouteFragment fragment = new RouteFragment();
        // Bundle bundle = new Bundle();
        // bundle.put
        return fragment;
    }
}
