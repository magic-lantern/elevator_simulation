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

        List<Floor> floors = new ArrayList<Floor>();
        for (int i = 1; i <= this.numFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    public void setNumElevators(int n) {
        this.numElevators = n;

        List<Elevator> elevators = new ArrayList<Elevator>();
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator());
        }
    }
    
    /*
     if invalid elevator number is passed, assume first elevator
        probabably would be better to throw exception
    */
    public int getElevatorCurrentFloor(int elevatornumber) {
        if ((elevatornumber <= 0) || (elevatornumber > this.numElevators))
            elevatornumber = 1;
    
        Iterator<Elevator> i = elevators.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
	}
        //elevators.get(elevatornumber).label;
        return 0;        
    }
    
    public String getElevatorCurrentStatus(int elevatornumber) {
        if ((elevatornumber <= 0) || (elevatornumber > this.numElevators))
            elevatornumber = 1;
    
        System.out.println(elevators.get(elevatornumber).toString());
        return "";//elevators.get(elevatornumber).toString();
    }

}
