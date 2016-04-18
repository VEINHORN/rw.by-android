package com.veinhorn.rwbytickets.purchase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.purchase.model.AvailableTrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 18.4.16.
 */
public class AvailibleTrainsAdapter extends BaseAdapter {
    private static final String TAG = AvailibleTrainsAdapter.class.getName();
    private LayoutInflater inflater;

    private List<AvailableTrain> availableTrains;

    private class ViewHolder {
        TextView trainNameView;

        public ViewHolder(View item) {
            trainNameView = (TextView) item.findViewById(R.id.trainNameTextView);
        }
    }

    public AvailibleTrainsAdapter(Context context) {
        inflater = LayoutInflater.from(context);

        availableTrains = new ArrayList<>();
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.availible_train_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.trainNameView.setText(availableTrains.get(position).getName());
        return convertView;
    }

    @Override public int getCount() {
        return availableTrains.size();
    }

    @Override public AvailableTrain getItem(int index) {
        return availableTrains.get(index);
    }

    @Override public long getItemId(int position) {
        return position;
    }
}
