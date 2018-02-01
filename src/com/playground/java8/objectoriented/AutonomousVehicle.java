package com.playground.java8.objectoriented;

public abstract class AutonomousVehicle {

    private boolean autoPilot;

    public abstract void selfParking();
    public abstract void valletCall(int coordinates);
    public abstract void setAutoPilot(boolean autoPilot);

    static int trashVariable;

}
