package com.veinhorn.rwbytickets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.veinhorn.rwbytickets.search.StationAutoCompleteAdapter;
import com.veinhorn.rwbytickets.search.rest.model.Station;
import com.veinhorn.rwbytickets.search.view.DelayAutoCompleteTextView;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.fromStationEditText) protected EditText fromStationView;
    private AutoCompleteTextView toStationView; // butterknife cannot bind this ??

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);

        toStationView = (DelayAutoCompleteTextView) findViewById(R.id.toStationAutoCompleteTextView);
        toStationView.setThreshold(2);
        toStationView.setAdapter(new StationAutoCompleteAdapter(this));
        toStationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station station = (Station) parent.getItemAtPosition(position);
                toStationView.setText(station.getName());
            }
        });
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
