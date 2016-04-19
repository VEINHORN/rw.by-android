package com.veinhorn.rwbytickets.action;

import com.veinhorn.rwbytickets.purchase.dialog.Dialog;
import com.veinhorn.rwbytickets.tickets.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 19.4.16.
 */
public class FetchOrdersAction implements Action<List<Order>, Dialog> {

    public List<Order> doAction(Dialog dialog) {
        return new ArrayList<>();
    }

}
