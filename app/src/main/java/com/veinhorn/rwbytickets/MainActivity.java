package com.veinhorn.rwbytickets;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.DrawerBuilder;
import com.veinhorn.rwbytickets.purchase.PurchasePagerAdapter;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private PurchaseDialog purchaseDialog; // init when user click on continue button

    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.viewPager) protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle("Purchase");
        setSupportActionBar(toolbar);
        // Build navigation drawer
        new DrawerBuilder().withActivity(this)
                .withToolbar(toolbar).build();

        PurchasePagerAdapter adapter = new PurchasePagerAdapter(
                getSupportFragmentManager(), purchaseDialog);
        viewPager.setAdapter(adapter);
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
