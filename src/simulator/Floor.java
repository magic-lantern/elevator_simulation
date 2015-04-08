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
    private boolean upRequested = false;
    private boolean downRequested = false;
    private boolean stopRequested = false;

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
        System.out.println("Floor: " + num + " up requested");
        upRequested = true;
    }

    public boolean getUpRequested() {
        return upRequested;
    }

    public void requestDown() {
        System.out.println("Floor: " + num + " down requested");
        downRequested = true;
    }

    public boolean getDownRequested() {
        return downRequested;
    }

    public void fullfillUp() {
        upRequested = false;
    }

    public void fullfillDown() {
        downRequested = false;
    }

    public void requestStop() {
        stopRequested = true;
        System.out.println("Floor: " + num + " stop requested");
    }

    public void fulfillStop() {
        stopRequested = false;
    }

    public boolean getStopRequested() {
        return stopRequested;
    }

    public void request(Elevator.State s) {
        if (s == Elevator.State.down) {
            this.requestDown();
        }
        else if (s == Elevator.State.up) {
            this.requestUp();
        }
        else {
            System.out.println("Unknown request recieved" + s);
        }
    }

    public String toString() {
        return "Floor: " + num + " with state uprequested: " + upRequested + " downrequested: " + downRequested + " stoprequested: " + stopRequested;
    }
}
