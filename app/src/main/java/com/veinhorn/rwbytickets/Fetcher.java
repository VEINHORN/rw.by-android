package com.veinhorn.rwbytickets;

/**
 * Created by veinhorn on 21.4.16.
 */
public interface Fetcher<T> {
    /** Fetches data from html page */
    T fetch(String html) throws NullPointerException;
}
