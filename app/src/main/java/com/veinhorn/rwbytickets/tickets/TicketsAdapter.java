package com.veinhorn.rwbytickets.tickets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.tickets.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 21.4.16.
 */
public class TicketsAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Order> orders;

    private class ViewHolder {
        TextView fromToStationTextView;
        TextView dispatchTextView;

        public ViewHolder(View item) {
            fromToStationTextView = (TextView) item.findViewById(R.id.fromToStationTextView);
            dispatchTextView = (TextView) item.findViewById(R.id.dispatchTextView);
        }
    }

    public TicketsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        orders = new ArrayList<>();
    }

    /** Clears current orders and adds new */
    public void updateOrders(List<Order> orders) {
        this.orders.clear();
        this.orders.addAll(orders);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.order_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String fromToStation = orders.get(position).getFromStation() + " - " +
                orders.get(position).getToStation();

        viewHolder.fromToStationTextView.setText(fromToStation);
        viewHolder.dispatchTextView.setText(orders.get(position).getDispatch());
        return convertView;
    }

    @Override public int getCount() {
        return orders.size();
    }

    @Override public Order getItem(int index) {
        return orders.get(index);
    }

    @Override public long getItemId(int position) {
        return position;
    }
}
