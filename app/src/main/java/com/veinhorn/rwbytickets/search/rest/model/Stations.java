package com.veinhorn.rwbytickets.search.rest.model;

import java.util.List;

/**
 * Created by veinhorn on 24.3.16.
 */
public class Stations {
    protected String query;

    protected List<String> code;
    protected List<String> data;
    protected List<String> full;

    /** Finds and return station in @Station representetion by its name from @data */
    public Station getStation(String name) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equalsIgnoreCase(name)) return getStation(i);
        }
        return null;
    }

    /** Creates Station object by @index position in rw.by stations JSON representation */
    public Station getStation(int index) {
        return new Station(code.get(index), data.get(index), full.get(index));
    }

    public String getQuery() {
        return query;
    }

    public List<String> getCodes() {
        return code;
    }

    public List<String> getNames() {
        return data;
    }

    public List<String> getFullNames() {
        return full;
    }

    public int size() {
        return data.size();
    }
}
