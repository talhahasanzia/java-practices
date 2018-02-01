package com.playground.java8.objectoriented;



// Inheritance
public class MiniCooper extends Vehicle {

    public String numberPlate;

    public MiniCooper()
    {
        name="Mini Cooper";  // name is inherited
        numberPlate="MCR 95"+this.manufacturerID; // "this" is redundant here
        vehicleType="Hatchback"; // vehicleType
        engine="668cc"; // Engine

    }

}
