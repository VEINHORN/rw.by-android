package com.veinhorn.rwbytickets.search.rest.service;

import com.veinhorn.rwbytickets.search.rest.model.Stations;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by veinhorn on 24.3.16.
 */
public interface RwStationsService {
    @GET("AutoComplete?type=STATION")
    Call<Stations> searchStations(@Query("lang") String lang, @Query("term") String term);
}
