package com.veinhorn.rwbytickets;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by veinhorn on 21.3.16.
 */
public class TicketsLoader extends AsyncTask<String, Void, String> {
    private Context context;
    private OkHttpClient httpClient;

    private static final String TAG = TicketsLoader.class.getName();

    private static final String BASE_URL = "https://poezd.rw.by";
    private static final String SIGN_IN_PAGE_URL = "https://poezd.rw.by/wps/portal/home/login_main";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    public TicketsLoader(Context context) {
        this.context = context;
        httpClient = new OkHttpClient();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // Get credentials
            Map<String, String> creds = fetchCredentials();
            // Get proper sign in form url
            String signInUrl = createSignInUrl(fetchSignInAction());
            // Sign In
            Request signInRequest = createSignInRequest(signInUrl, creds);
            // Response signInResponse = httpClient.newCall(signInRequest).execute(); // don't work
            Response signInResponse = httpClient.newCall(signInRequest).execute();

        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Cannot open resource file", e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (Exception e) {
            Log.e(TAG, "Ugly crappy exception " + e.getMessage(), e);
        }
        return "";
    }

    private Request createSignInRequest(String signInUrl, Map<String, String> creds) {
        return new Request.Builder()
                .url(signInUrl)
                .header("Content-Type", "application/x-www-form-urlencoded") // be on the safe side
                .post(createSignInFormBody(creds))
                .build();
    }

    /**
     * Creates body with POST parameters (form parameters)
     */
    private RequestBody createSignInFormBody(Map<String, String> creds) {
        return new FormBody.Builder()
                .add(LOGIN, creds.get(LOGIN))
                .add(PASSWORD, creds.get(PASSWORD))
                .build();
    }

    private String createSignInUrl(String signInAction) {
        return BASE_URL + signInAction;
    }

    private String fetchSignInAction() throws IOException {
        Request request = new Request.Builder().url(SIGN_IN_PAGE_URL).build();
        Response response = httpClient.newCall(request).execute();
        Document document = Jsoup.parse(response.body().string());
        Element loginForm = document.getElementById("login");
        return loginForm.attr("action");
    }

    private Map<String, String> fetchCredentials() throws Resources.NotFoundException, IOException {
        // Read credentials from property file (just for test now)
        // TODO: Make some form for input when start application
        Properties properties = new Properties();
        properties.load(context.getResources().openRawResource(R.raw.creds));
        return composeCredentials(properties.getProperty(LOGIN), properties.getProperty(PASSWORD));
    }

    private Map<String, String> composeCredentials(String login, String password) {
        Map<String, String> creds = new HashMap<>();
        creds.put(LOGIN, login);
        creds.put(PASSWORD, password);
        return creds;
    }
}
