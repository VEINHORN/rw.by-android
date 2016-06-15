package com.veinhorn.rwbytickets;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.Drawer;
import com.veinhorn.rwbytickets.auth.LoginActivity;
import com.veinhorn.rwbytickets.auth.creds.CredentialsStorage;
import com.veinhorn.rwbytickets.auth.creds.ICredentials;
import com.veinhorn.rwbytickets.purchase.PurchasePagerAdapter;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.viewPager) protected ViewPager viewPager;
    private Drawer drawer;
    private ICredentials credentials;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Start LoginActivity if user is not signed in
        ICredentials creds = CredentialsStorage.readCredentials(this);
        if (creds == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            // Check if logged in date is expired (period: 14 days)
            final int ONE_DAY_MILLIS = 86400 * 1000;
            final long deltaMillis = new Date().getTime() - creds.getLoggedIn().getTime();
            if (deltaMillis < 14 * ONE_DAY_MILLIS) { // not expired
                credentials = creds;
            } else { // expired
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }

        }

        // toolbar.setBackgroundColor(Color.parseColor(""));
        toolbar.setTitle(getString(R.string.activity_main_toolbar_title));
        setSupportActionBar(toolbar);
        // Build navigation drawer
        drawer = new NavigationDrawer(this, toolbar, credentials).create();

        viewPager.setAdapter(new PurchasePagerAdapter(getSupportFragmentManager()));
    }

    /** Reassign user credentials after user is successfully signed in */
    @Override protected void onResume() {
        super.onResume();
        ICredentials creds = CredentialsStorage.readCredentials(this);
        if (creds != null) credentials = creds;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
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
