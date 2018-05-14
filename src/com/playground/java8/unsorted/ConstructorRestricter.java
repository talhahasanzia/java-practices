package com.playground.java8.unsorted;

import java.util.Random;

public class ConstructorRestricter {

    private Random random;
    private int min, max;

    public ConstructorRestricter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getRandom() {
        return random.nextInt(max) + min;
    }
}
