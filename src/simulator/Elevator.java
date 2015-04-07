/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

/**
 *
 * @author seth
 */
public class Elevator {

    public enum State {
        up,
        down,
        stopped
    }
    State state = State.stopped;
    String label;
    int currFloor = 1;
    
    public Elevator() {
        
    }
    
    public Elevator(String lbl) {
        this.label = lbl;
    }
 
    public void moveUp() {
        state = State.up;
    }
    
    public void moveDown() {
        state = State.down;
    }
    
    public void requestFloor(int f) {
        
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
    public void setLabel(String lbl)
    {
        this.label = lbl;
    }
    
    public int getFloor()
    {
        return currFloor;
    }
    
    public void setFloor(int floor)
    {
        currFloor = floor;
    }
    
    public State getState() {
        return this.state;
    }
    
    public String toString() {
        if (this.state != State.stopped)
            return "Elevator '" + this.label + "' currently moving " + this.state.toString();
        else
            return "Elevator '" + this.label + "' currently stopped";
    }
    
}
