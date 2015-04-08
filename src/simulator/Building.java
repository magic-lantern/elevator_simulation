/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import simulator.Floor;
import simulator.Elevator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author seth
 */
public class Building {

    private List<Floor> floors;
    private List<Elevator> elevators;
    private int numFloors;
    private int numElevators;
    private boolean log = true;

    public Building() {
        this.setNumElevators(1);
        this.setNumFloors(5);
    }

    public Building(int numberOfFloors, int numberOfElevators) {
        this.setNumElevators(numberOfElevators);
        this.setNumFloors(numberOfFloors);
    }

    public int getNumFloors() {
        return this.numFloors;
    }

    public int getNumElevators() {
        return this.numElevators;
    }

    public void setNumFloors(int n) {
        this.numFloors = n;

        floors = new ArrayList<Floor>();
        for (int i = 1; i <= this.numFloors; i++) {
            floors.add(new Floor(i));
            System.out.println("Adding floor: " + i);
        }
    }

    public void setNumElevators(int n) {
        this.numElevators = n;

        elevators = new ArrayList<Elevator>();
        for (int i = 1; i <= numElevators; i++) {
            // new elevator should have a name or label the same as it's number
            Elevator e = new Elevator("" + i);
            elevators.add(e);
            System.out.println("Adding elevator: " + i);
        }
    }

    /*
     if invalid elevator number is passed, assume first elevator
     probabably would be better to throw exception
     */
    public int getElevatorCurrentFloor(int elevatornumber) {
        if ((elevatornumber < 0) || (elevatornumber >= this.numElevators)) {
            elevatornumber = 0;
        }

        return elevators.get(elevatornumber).getFloor();

        /*
         Iterator<Elevator> i = elevators.iterator();
         while (i.hasNext()) {
         System.out.println("getElevatorCurrentFloor: " + i.next());
         }
         */
    }

    public String getElevatorCurrentStatus(int elevatornumber) {
        if ((elevatornumber < 0) || (elevatornumber >= this.numElevators)) {
            elevatornumber = 0;
        }

        System.out.println(elevators.get(elevatornumber).toString());
        return elevators.get(elevatornumber).toString();
    }

    // return false if invalid request
    // return true if valid request
    public boolean requestFloor(int floor) {
        Elevator e = elevators.get(0);
        boolean returnStatus = true;

        if (floor < 1 || floor > this.numFloors) {
            if (log) {
                System.out.println("Invalid floor request");
            }
            returnStatus = false;
        } else if (floor == e.getFloor()) {
            System.out.println("Requesting current floor, invalid");
            returnStatus = false;
        } else {
            if (e.getState() == Elevator.State.stopped) {
                if (log) {
                    System.out.println("Valid request logged.");
                }

                if (e.getFloor() < floor) {
                    // elevator and building stores floor as human expected value, but
                    // arraylist is 0 based
                    floors.get(floor - 1).requestStop();
                    e.moveUp();
                } else {
                    floors.get(floor - 1).requestStop();
                    e.moveDown();
                }
            } else {
                // elevator already moving, can't set direction
                floors.get(floor - 1).requestStop();
            }
        }

        if (log) {
            showStatus();
        }

        return returnStatus;

    }

    // return false if invalid request
    // return true if valid request
    public boolean floorRequest(int floor, Elevator.State s) {
        Elevator e = elevators.get(0);
        boolean returnStatus = true;

        floors.get(floor - 1).request(s);
        if (e.getState() == Elevator.State.stopped) {
            if (log) {
                System.out.println("Valid request logged.");
            }

            if (e.getFloor() < floor) {
                // elevator and building stores floor as human expected value, but
                // arraylist is 0 based
                floors.get(floor - 1).requestStop();
                e.moveUp();
            } else {
                floors.get(floor - 1).requestStop();
                e.moveDown();
            }
        } else {
            // elevator already moving, can't set direction
            floors.get(floor - 1).requestStop();
        }

        if (log) {
            showStatus();
        }

        return returnStatus;
    }

    public void testStartFloor(int floor) {
        System.out.println("Manually setting elevator floor to: " + floor);
        Elevator e = elevators.get(0);
        e.setFloor(floor);
    }

    public void showStatus() {
        Iterator<Floor> i = floors.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

}
