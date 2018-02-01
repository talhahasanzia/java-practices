package com.playground.java8;

import com.playground.java8.objectoriented.Movable;
import com.playground.java8.objectoriented.PrototypeCar;

public class Main {

    public static void main(String[] args) {
	    // write your code here

        Movable movable=new PrototypeCar(); // referencing class as interface

        movable.switchOn();

        movable.forward();

        PrototypeCar prototypeCar= (PrototypeCar) movable;

        prototypeCar.selfParking();

    }







}
