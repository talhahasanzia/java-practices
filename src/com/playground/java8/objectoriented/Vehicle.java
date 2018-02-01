package com.playground.java8.objectoriented;



// Inheritance
public class Vehicle {

    protected String engine; // can be inherited, access is private
    protected String vehicleType; // can be inherited, access is private
    public String name; // can be inherited, accessed publicly

    protected static int manufacturerID; // cannot be inherited, but can be accessed


    private boolean hasWheels; // cannot be inherited

    public Vehicle() {
        manufacturerID = 2348;
    }

    public void showData() {
        System.out.println(toString());
    }


    /**
     * static getter for static member
     *
     * @return
     */
    public static int getManufacturerID() // cannot be inherit but can be accessed
    {
        return manufacturerID;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "Engine: " + engine +
                "Type: " + vehicleType +
                "Manufacturer's License: " + manufacturerID;
    }
}