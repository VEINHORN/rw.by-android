package com.veinhorn.rwbytickets.tickets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veinhorn.rwbytickets.R;
import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.purchase.dialog.factory.DialogFactory;

import butterknife.ButterKnife;

/**
 * Created by veinhorn on 13.4.16.
 */
public class TicketsFragment extends Fragment {
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tickets, container, false);
        ButterKnife.bind(this, rootView);

        // Create new dialog
        dialog = DialogFactory.newInstance();

        new TicketsLoader(getActivity(), dialog).execute();

        return rootView;
    }

}
