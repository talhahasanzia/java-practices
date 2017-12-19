package com.playground.streamapidemo;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by Talha on 19-Dec-17.
 */

public class DataModel implements Serializable {

    public long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    private long id;
    private String data;

    public DataModel(long id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return id + ":" + data;
    }
}
