package com.playground.java8.objectoriented;


// abstraction: this class can now be referenced as Movable
public class PrototypeCar extends AutonomousVehicle implements Movable {

    @Override
    public void selfParking() {
        System.out.println("Self Parking... Please Wait.");
    }

    @Override
    public void valletCall(int coordinates) {
        System.out.println("Dispatching vehicle to point: " + coordinates);
    }

    @Override
    public void setAutoPilot(boolean autoPilot) {
        System.out.println("AutoPilot Mode: " + autoPilot);
    }


    // Movable methods

    @Override
    public void switchOff() {
        System.out.println("Switched off");
    }

    @Override
    public void switchOn() {
        System.out.println("Switched On");
    }

    @Override
    public void steerLeft() {
        System.out.println("Steer left");
    }

    @Override
    public void steerRight() {
        System.out.println("Steer right");
    }

    @Override
    public void reverse() {
        System.out.println("Reverse");
    }

    @Override
    public void forward() {
        System.out.println("Forward");
    }
}
