package com.playground.java8;

public interface InstanceChecker<T> {

    default String instanceOf(T t) {

        if (t instanceof Main)
            return "MainActivity";
        else if (t instanceof Sample)
            return "Sample Activity";
        else
            return "Wrong Type Implementaion";
    }
}
