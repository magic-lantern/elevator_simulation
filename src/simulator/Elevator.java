/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

/**
* Elevator Model Object.
* 
* <P>Various attributes of elevators, and related behavior.
*  
* <P>Note that {@link BigDecimal} is used to model the price - not double or float. 
* See {@link #Guitar(String, BigDecimal, Integer)} for more information.
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

    /**
     * Set the elevator to move up
     */
    public void moveUp() {
        state = State.up;
    }

    /**
     * Set the elevator to move down
     */
    public void moveDown() {
        state = State.down;
    }

    /**
     * Set the elevator to stop
     */
    public void stop() {
        state = State.stopped;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String lbl) {
        this.label = lbl;
    }

    public int getFloor() {
        return currFloor;
    }

    public void setFloor(int floor) {
        currFloor = floor;
    }

    /**
     * Return the current state of the elevator
     *
     * @return Elevator.State enum with current direction
     */
    public State getState() {
        return this.state;
    }

    public String toString() {
        if (this.state != State.stopped) {
            return "Elevator '" + this.label + "' on floor " + this.currFloor + " and is currently moving " + this.state.toString();
        } else {
            return "Elevator '" + this.label + "' on floor " + this.currFloor + " and is currently stopped";
        }
    }

}
