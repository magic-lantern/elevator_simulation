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
public class Floor {
    private int num;
    private boolean upRequested;
    private boolean downRequested;
    
    public Floor() {
        num = 1;
    }
    
    public Floor(int floorNum) {
        num = floorNum;
    }
    
    public int getFloorNumber() {
        return num;
    }
    
    public void requestUp() {
        upRequested = true;
    }
    
    public void requestDown() {
          downRequested = true;
    }
    
    public void fullfillUp() {
        upRequested = false;
    }
    
    public void fullfillDown() {
        downRequested = false;
    }
    
    public String toString() {
        return "Floor: " + num + " with state uprequested: " + upRequested + " downrequested: " + downRequested;
    }
}
