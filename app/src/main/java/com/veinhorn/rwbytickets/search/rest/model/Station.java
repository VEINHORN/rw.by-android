package com.veinhorn.rwbytickets.search.rest.model;

/**
 * Created by veinhorn on 24.3.16.
 */
public class Station {
    private String code;
    private String name;
    private String fullName;

    public Station(String code, String name, String fullName) {
        this.code = code;
        this.name = name;
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
