package com.veinhorn.rwbytickets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.veinhorn.rwbytickets.search.StationAutoCompleteAdapter;
import com.veinhorn.rwbytickets.search.rest.model.Station;
import com.veinhorn.rwbytickets.search.view.DelayAutoCompleteTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final int DEFAULT_THRESHOLD = 2;
    private PurchaseDialog purchaseDialog;

    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.continueButton) protected Button continueButton;

    @Bind(R.id.fromStationView) protected DelayAutoCompleteTextView fromStationView;
    @Bind(R.id.toStationView) protected DelayAutoCompleteTextView toStationView; // butterknife cannot bind this ??

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        // Init purchase dialog
        purchaseDialog = new PurchaseDialog();

        fromStationView.setThreshold(DEFAULT_THRESHOLD);
        fromStationView.setAdapter(new StationAutoCompleteAdapter(this));
        fromStationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station station = (Station) parent.getItemAtPosition(position);
                fromStationView.setText(station.getName());
            }
        });

        toStationView.setThreshold(DEFAULT_THRESHOLD);
        toStationView.setAdapter(new StationAutoCompleteAdapter(this));
        toStationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station station = (Station) parent.getItemAtPosition(position);
                toStationView.setText(station.getName());
            }
        });
    }

    @OnClick(R.id.continueButton) public void continuePurchase() {
        new TicketsLoader(this, purchaseDialog).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
