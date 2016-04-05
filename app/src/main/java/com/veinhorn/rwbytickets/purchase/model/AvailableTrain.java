package com.veinhorn.rwbytickets.purchase.model;

/**
 * Created by veinhorn on 5.4.16.
 */
public class AvailableTrain {

    private String selectedRow; // this String is used when we will be sent data to server

    private String name;

    public void setSelectedRow(String selectedRow) {
        this.selectedRow = selectedRow;
    }

    public String getSelectedRow() {
        return selectedRow;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
