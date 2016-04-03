package com.veinhorn.rwbytickets.purchase.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.RouteLoader;
import com.veinhorn.rwbytickets.purchase.PurchasePagerAdapter;
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

    private StationAutoCompleteAdapter fromStationAdapter;
    private StationAutoCompleteAdapter toStationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_route, container, false);
        ButterKnife.bind(this, rootView);
        Activity activity = getActivity();

        fromStationView.setThreshold(DEFAULT_THRESHOLD);
        fromStationAdapter = new StationAutoCompleteAdapter(activity);
        fromStationView.setAdapter(fromStationAdapter);
        fromStationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station station = (Station) parent.getItemAtPosition(position);
                fromStationView.setText(station.getName());
            }
        });

        toStationView.setThreshold(DEFAULT_THRESHOLD);
        toStationAdapter = new StationAutoCompleteAdapter(activity);
        toStationView.setAdapter(toStationAdapter);
        toStationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station station = (Station) parent.getItemAtPosition(position);
                toStationView.setText(station.getName());
            }
        });

        return rootView;
    }

    /** Make auth, confirm rules, and make search requests for stations */
    @OnClick(R.id.continueButton) public void continuePurchase() {
        String fromStationInput = fromStationView.getText().toString();
        String toStationInput = toStationView.getText().toString();
        if ("".equals(fromStationInput)) {
            Toast.makeText(getActivity(), "Enter from station", Toast.LENGTH_SHORT).show();
        } else if ("".equals(toStationInput)) {
            Toast.makeText(getActivity(), "Enter destination station", Toast.LENGTH_SHORT).show();
        } else {
            PurchaseDialog purchaseDialog = PurchasePagerAdapter.getPurchaseDialog();
            fillPurchaseDialog(purchaseDialog, fromStationInput, toStationInput);
            new RouteLoader(getActivity(), purchaseDialog).execute();
        }
    }

    private void fillPurchaseDialog(PurchaseDialog purchaseDialog,
                                    String fromStationInput, String toStationInput) {
        Station fromStation = fromStationAdapter.getStations().getStation(fromStationInput);
        Station toStation = toStationAdapter.getStations().getStation(toStationInput);
        purchaseDialog.setFromStation(fromStation);
        purchaseDialog.setToStation(toStation);
        // TODO: Add here additional parameters3
    }

    public static RouteFragment newInstance(PurchaseDialog purchaseDialog) {
        RouteFragment fragment = new RouteFragment();
        // Bundle bundle = new Bundle();
        // bundle.put
        return fragment;
    }
}
