package com.veinhorn.rwbytickets.purchase.model;

/**
 * Created by veinhorn on 5.4.16.
 */
public class AvailableTrain {

    private String selectedRow; // this String is used when we will be sent data to server

    private String name;

    private String type; // tipa meghdunarodnie linii

    private String dispatch;

    private String arrival;

    private String travelTime;

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getArrival() {
        return arrival;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

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
