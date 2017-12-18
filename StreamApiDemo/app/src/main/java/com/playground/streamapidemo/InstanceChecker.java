package com.playground.streamapidemo;

/**
 * Created by VD-Test on 18-Dec-17.
 */

public interface InstanceChecker<T> {

    default String instanceOf(T t) {

        if (t instanceof MainActivity)
            return "MainActivity";
        else if (t instanceof SampleActivity)
            return "Sample Activity";
        else
            return "Wrong Type Implementaion";
    }
}
