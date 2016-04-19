package com.veinhorn.rwbytickets.tickets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.purchase.dialog.factory.DialogFactory;

import butterknife.ButterKnife;

/**
 * Created by veinhorn on 13.4.16.
 */
public class TicketsActivity extends AppCompatActivity {
    private Dialog dialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        ButterKnife.bind(this);

        dialog = DialogFactory.newInstance();
        new TicketsLoader(this, dialog).execute();
    }

}
