package com.veinhorn.rwbytickets.search.rest.callback;

import com.veinhorn.rwbytickets.search.rest.model.Stations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by veinhorn on 28.3.16.
 */
public class RestCallback implements Callback<Stations> {
    @Override
    public void onResponse(Call<Stations> call, Response<Stations> response) {
        Stations stations = response.body();
        Integer test = 0;
    }

    // TODO: Add here some handling
    @Override
    public void onFailure(Call<Stations> call, Throwable t) {
        t.printStackTrace();
    }
}
