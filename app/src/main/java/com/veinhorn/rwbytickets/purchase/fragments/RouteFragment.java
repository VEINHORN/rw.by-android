package com.veinhorn.rwbytickets.purchase.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.RouteLoader;
import com.veinhorn.rwbytickets.purchase.PurchasePagerAdapter;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;
import com.veinhorn.rwbytickets.search.StationAutoCompleteAdapter;
import com.veinhorn.rwbytickets.search.rest.model.Station;
import com.veinhorn.rwbytickets.search.view.DelayAutoCompleteTextView;

import java.util.Calendar;

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
    @Bind(R.id.whenView) protected EditText whenView;

    @Bind(R.id.adultSpinner) protected Spinner adultSpinner;
    @Bind(R.id.childWithPlaceSpinner) protected Spinner childWithPlaceSpinner;
    @Bind(R.id.childWithoutPlaceSpinner) protected Spinner childWithoutPlaceSpinner;

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

        // Add spinner for adult passengers
        ArrayAdapter<CharSequence> adultAdapter = ArrayAdapter.createFromResource(activity,
                R.array.adult, android.R.layout.simple_spinner_item);
        adultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adultSpinner.setAdapter(adultAdapter);

        // Add spinner for child with places passengers
        ArrayAdapter<CharSequence> childWithPlacesAdapter = ArrayAdapter.createFromResource(activity,
                R.array.child_with_place, android.R.layout.simple_spinner_item);
        childWithPlacesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        childWithPlaceSpinner.setAdapter(childWithPlacesAdapter);

        // Add spinner for child without places passengers
        ArrayAdapter<CharSequence> childWithoutPlacesAdapter = ArrayAdapter.createFromResource(activity,
                R.array.child_without_place, android.R.layout.simple_spinner_item);
        childWithoutPlacesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        childWithoutPlaceSpinner.setAdapter(childWithoutPlacesAdapter);

        return rootView;
    }

    @OnClick(R.id.whenView) public void selectDate() {
        CalendarDatePickerDialogFragment dialog = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                        PurchaseDialog purchaseDialog = PurchasePagerAdapter.getPurchaseDialog();
                        purchaseDialog.setWhenDate(year + "." + monthOfYear + "." + dayOfMonth);
                        whenView.setText(year + " / " + monthOfYear + " / " + dayOfMonth);
                    }
                })
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setThemeDark(true);
        dialog.show(getFragmentManager(), "Select date");
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
        purchaseDialog.setAdults(1);
        purchaseDialog.setChildren(0);
        purchaseDialog.setChildrenNoPlaces(0);
        // TODO: Add here additional parameters3
    }

    public static RouteFragment newInstance(PurchaseDialog purchaseDialog) {
        RouteFragment fragment = new RouteFragment();
        // Bundle bundle = new Bundle();
        // bundle.put
        return fragment;
    }
}
