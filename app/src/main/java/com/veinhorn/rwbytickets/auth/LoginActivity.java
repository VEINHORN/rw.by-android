package com.veinhorn.rwbytickets.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.veinhorn.rwbytickets.R;

import butterknife.ButterKnife;

/**
 * Created by veinhorn on 3.5.16.
 */
public class LoginActivity extends AppCompatActivity {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

}
