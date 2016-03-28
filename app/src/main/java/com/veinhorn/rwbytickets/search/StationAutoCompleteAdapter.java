package com.veinhorn.rwbytickets.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.veinhorn.rwbytickets.search.rest.RetrofitCreator;
import com.veinhorn.rwbytickets.search.rest.model.Station;
import com.veinhorn.rwbytickets.search.rest.model.Stations;
import com.veinhorn.rwbytickets.search.rest.service.RwStationsService;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by veinhorn on 28.3.16.
 */
public class StationAutoCompleteAdapter extends BaseAdapter implements Filterable {
    private static  final String TAG = StationAutoCompleteAdapter.class.getName();
    private LayoutInflater inflater;

    private Stations stations;
    private Retrofit retrofit;
    private RwStationsService stationsService;

    public StationAutoCompleteAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        retrofit = RetrofitCreator.create();
        stationsService = retrofit.create(RwStationsService.class);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.select_dialog_item,
                    parent, false);
            ((TextView)convertView).setText(getItem(position).getName());
        }
        return convertView;
    }

    @Override public int getCount() {
        return stations.size();
    }

    @Override public Station getItem(int index) {
        return stations.getStation(index);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint != null) {
                    try {
                        Stations stations = findStations("ru", constraint.toString());
                        results.values = stations;
                        results.count = stations.size();
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    stations = (Stations) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    private Stations findStations(String lang, String name) throws IOException {
        return stationsService.searchStations(lang, name).execute().body();
    }
}
