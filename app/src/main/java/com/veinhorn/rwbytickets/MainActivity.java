package com.veinhorn.rwbytickets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.veinhorn.rwbytickets.rest.RetrofitCreator;
import com.veinhorn.rwbytickets.rest.RwStationsService;
import com.veinhorn.rwbytickets.rest.callback.RestCallback;

import butterknife.Bind;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.fromStationEditText) protected EditText fromStationView;
    private AutoCompleteTextView toStationView; // butterknife cannot bind this ??

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = RetrofitCreator.create();

        setSupportActionBar(toolbar);

        //toStationAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.toStationAutoCompleteTextView);
        //toStationAutoCompleteTextView.addTextChangedListener();

        // String[] languages = {"Java", "Java 1", "Java 2", "Java 3", "Java 4", "Java 5", "Scala", "Erlang", "lol", "ale", "nigger"};
        // ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
        //        android.R.layout.select_dialog_item, languages);
        // autoCompleteTextView.setThreshold(1);
        // toStationAutoCompleteTextView.setAdapter(adapter);
        // new TicketsLoader(this).execute();

        fromStationView.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override public void afterTextChanged(Editable s) {}
        });

        RwStationsService service = retrofit.create(RwStationsService.class);
        service.searchStations("ru", "минск").enqueue(new RestCallback());
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
