package com.veinhorn.rwbytickets.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;
import android.widget.Toast;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.auth.creds.Creds;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by veinhorn on 3.5.16.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.loginEditText) protected EditText loginEditText;
    @Bind(R.id.passwordEditText) protected EditText passwordEditText;
    @Bind(R.id.loginButton) protected AppCompatButton loginButton;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginButton) protected void signIn() {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        logIn(login, password);
    }

    @Override public void onBackPressed() {
        moveTaskToBack(true); // disable going back to the MainActivity
    }

    private void logIn(String login, String password) {
        if (validate(login, password)) {
            new AuthLoader(this, new Creds(login, password)).execute();
        } else {
            Toast.makeText(this, "Empty login/password", Toast.LENGTH_SHORT).show();
            loginEditText.setText("");
            passwordEditText.setText("");
        }
    }

    private boolean validate(String login, String password) {
        return !"".equals(login) && !"".equals(password);
    }
}
