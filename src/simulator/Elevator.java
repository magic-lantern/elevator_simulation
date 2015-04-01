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
    
    public Elevator() {
        
    } 
 
    public void moveUp() {
        state = State.up;
    }
    
    public void moveDown() {
        state = State.down;
    }
    
    public void requestFloor(int f) {
        
    }
    
    public String toString() {
        return "Current elevator status: " + this.state.toString();
    }
            
}
