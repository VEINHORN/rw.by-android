package com.veinhorn.rwbytickets;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.framed.Header;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient httpClient;
    private static final String BASE_URL = "https://poezd.rw.by";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        httpClient = new OkHttpClient();

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                //Response response = null;
                try {
                    // Get proper sign in form url
                    Request request = new Request.Builder()
                            .url("https://poezd.rw.by/wps/portal/home/login_main")
                            .build();
                    Response response = httpClient.newCall(request).execute();

                    Document document = Jsoup.parse(response.body().string());
                    Element element = document.getElementById("login");

                    String actionUrl = element.attr("action");

                    ///// Sign in
                    String signInUrl = BASE_URL + actionUrl; // compose login url for auth

                    // Create body with POST parameters
                    RequestBody formBody = new FormBody.Builder()
                            .add("login", "")
                            .add("password", "")
                            .build();

                    Request signInRequest = new Request.Builder()
                            .url(signInUrl)
                            .post(formBody)
                            .build();

                    Response signInResponse = httpClient.newCall(signInRequest).execute();

                    Integer test = 3450345;
                    /////
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }
        }.execute();
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
