package com.veinhorn.rwbytickets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.veinhorn.rwbytickets.auth.LoginActivity;
import com.veinhorn.rwbytickets.auth.creds.Creds;
import com.veinhorn.rwbytickets.auth.creds.ICreds;
import com.veinhorn.rwbytickets.purchase.PurchasePagerAdapter;
import com.veinhorn.rwbytickets.tickets.TicketsActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.viewPager) protected ViewPager viewPager;
    private Drawer drawer;
    private ICreds credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Make auth logic here
        // Start log in activity
        SharedPreferences prefs = getSharedPreferences(getString(R.string.creds_file_key),
                Context.MODE_PRIVATE);
        String login = prefs.getString("user.login", "");
        String password = prefs.getString("user.password", "");
        if ("".equals(login) || "".equals(password)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        fillCredentials();
        //

        toolbar.setBackgroundColor(Color.parseColor("#212121"));
        toolbar.setTitle("Purchase");
        setSupportActionBar(toolbar);
        // Build navigation drawer
        PrimaryDrawerItem primaryItem = new PrimaryDrawerItem().withName("Purchase");
        SecondaryDrawerItem ticketsItem = new SecondaryDrawerItem().withName("Tickets");
        drawer = new DrawerBuilder().withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(primaryItem, ticketsItem)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                startActivity(new Intent(MainActivity.this, TicketsActivity.class));
                                break;
                        }
                        return false;
                    }
                })
                .build();
        viewPager.setAdapter(new PurchasePagerAdapter(getSupportFragmentManager()));
    }

    @Override protected void onResume() {
        super.onResume();
        fillCredentials();
    }

    private void fillCredentials() {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.creds_file_key),
                Context.MODE_PRIVATE);
        String login = prefs.getString("user.login", "");
        String password = prefs.getString("user.password", "");
        if (!"".equals(login) && !"".equals(password)) {
            credentials = new Creds(prefs.getString("user.login", ""),
                    prefs.getString("user.password", ""));
        }
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
