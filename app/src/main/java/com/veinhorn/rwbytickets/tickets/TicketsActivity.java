package com.veinhorn.rwbytickets.tickets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.purchase.dialog.factory.DialogFactory;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by veinhorn on 13.4.16.
 */
public class TicketsActivity extends AppCompatActivity {
    @Bind(R.id.ordersListView) protected ListView ordersListView;
    @Bind(R.id.progressBar) protected ProgressBar progressBar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        ButterKnife.bind(this);
        TicketsAdapter ticketsAdapter = new TicketsAdapter(this);
        ordersListView.setAdapter(ticketsAdapter);

        Dialog dialog = DialogFactory.newInstance();
        new TicketsLoader(this, dialog, ticketsAdapter, progressBar).execute();
    }

}
