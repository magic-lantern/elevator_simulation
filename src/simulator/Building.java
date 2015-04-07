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
        if ((elevatornumber < 0) || (elevatornumber >= this.numElevators))
            elevatornumber = 0;
        
        return elevators.get(elevatornumber).getFloor();
    
        /*
        Iterator<Elevator> i = elevators.iterator();
        while (i.hasNext()) {
            System.out.println("getElevatorCurrentFloor: " + i.next());
	}
        */
    }
    
    public String getElevatorCurrentStatus(int elevatornumber) {
        if ((elevatornumber < 0) || (elevatornumber >= this.numElevators))
            elevatornumber = 0;
    
        System.out.println(elevators.get(elevatornumber).toString());
        return elevators.get(elevatornumber).toString();
    }
    
    // return false if invalid request
    // return true if valid request
    public boolean requestFloor(int floor) {
        Elevator e = elevators.get(0);
        
        if (floor < 1 || floor > this.numFloors) {
            System.out.println("Invalid floor request");
            return false;
        }
        
        if (floor == e.getFloor()) {
            return false;
        }
        
        if (e.getState() == Elevator.State.stopped) {
            if (e.getFloor() < floor) {
                e.moveUp();
            }
            else
                e.moveDown();
        }
        return true;
    }

}
