/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

/**
 *
 * @author seth
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label elevatorStatus;

    @FXML
    private Button elevatorbtn1;

    @FXML
    private Button elevatorbtn2;

    @FXML
    private Button elevatorbtn3;

    @FXML
    private Button elevatorbtn4;

    @FXML
    private Button elevatorbtn5;

    @FXML
    private Button floor1Up;

    @FXML
    private Button floor2Up;

    @FXML
    private Button floor3Up;

    @FXML
    private Button floor4Up;

    @FXML
    private Button floor2Down;

    @FXML
    private Button floor3Down;

    @FXML
    private Button floor4Down;

    @FXML
    private Button floor5Down;

    @FXML
    private Label currentFloor;

    private Building building;

    @FXML
    private void handleElevatorBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        // pass in floor requested
        boolean result = building.requestFloor(Integer.valueOf(b.getText()));
        if (result) {
            // if result is true, elevator moving, update floor
            System.out.println("Elevator - #" + b.getText() + " floor requested");
        } else {
            // if result is false, cancel call
            System.out.println("Elevator - canceling floor request to " + b.getText());
        }

        updateState();
    }

    @FXML
    private void handleFloorBtn(ActionEvent event) {
        Button b = (Button) event.getSource();
        Elevator.State s = Elevator.State.stopped;

        if (b.getText().toLowerCase().equals("up")) {
            s = Elevator.State.up;
        } else if (b.getText().toLowerCase().equals("down")) {
            s = Elevator.State.down;
        } else {
            System.out.println("invalid value ***********");
        }

        boolean result = building.floorRequest(Integer.valueOf(b.getId().substring(5, 6)), s);

        if (result) {
            // if result is true, elevator requested
            System.out.println(b.getId() + " - " + b.getText() + " call requested");
        } else {
            // if result is false, cancel call
            System.out.println(b.getId() + " - canceling " + b.getText() + " call request");
        }
        updateState();
    }

    @FXML
    private void runSimulation(ActionEvent event) {
        Button b = (Button) event.getSource();
        b.setCursor(Cursor.WAIT);
        b.getParent().setCursor(Cursor.WAIT);
        b.setText("Running");

        // separate non-UI thread
        new Thread() {
            // runnable for that thread
            public void run() {
                do {
                    //pause for a second to allow user to see changes
                    //and simulate time for elevator to move
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    threadUpdateUI();
                } while (building.run());

                Platform.runLater(new Runnable() {
                    public void run() {
                        b.setText("Run Simulation");
                        b.setCursor(Cursor.DEFAULT);
                        b.getParent().setCursor(Cursor.DEFAULT);
                    }
                });
            }
        }.start();
    }

    private void threadUpdateUI() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updateState();     
            }
        });
    }

    private void updateState() {
        elevatorStatus.setText(building.getElevatorCurrentStatus(1));
        currentFloor.setText("" + (building.getElevatorCurrentFloor(1)));

        if (building.floorUpRequested(1)) {
            floor1Up.setStyle("-fx-background-color: #FFFF00;");
        } else {
            floor1Up.setStyle("");
        }
        if (building.floorUpRequested(2)) {
            floor2Up.setStyle("-fx-background-color: #FFFF00;");
        } else {
            floor2Up.setStyle("");
        }
        if (building.floorDownRequested(2)) {
            floor2Down.setStyle("-fx-background-color: #FFFF00;");
        } else {
            floor2Down.setStyle("");
        }
        if (building.floorUpRequested(3)) {
            floor3Up.setStyle("-fx-background-color: #FFFF00;");
        } else {
            floor3Up.setStyle("");
        }
        if (building.floorDownRequested(3)) {
            floor3Down.setStyle("-fx-background-color: #FFFF00;");
        } else {
            floor3Down.setStyle("");
        }
        if (building.floorUpRequested(4)) {
            floor4Up.setStyle("-fx-background-color: #FFFF00;");
        } else {
            floor4Up.setStyle("");
        }
        if (building.floorDownRequested(4)) {
            floor4Down.setStyle("-fx-background-color: #FFFF00;");
        } else {
            floor4Down.setStyle("");
        }
        if (building.floorDownRequested(5)) {
            floor5Down.setStyle("-fx-background-color: #FFFF00;");
        } else {
            floor5Down.setStyle("");
        }
        if (building.floorStopRequested(1)) {
            elevatorbtn1.setStyle("-fx-background-color: #FFFF00;");
        } else {
            elevatorbtn1.setStyle("");
        }
        if (building.floorStopRequested(2)) {
            elevatorbtn2.setStyle("-fx-background-color: #FFFF00;");
        } else {
            elevatorbtn2.setStyle("");
        }
        if (building.floorStopRequested(3)) {
            elevatorbtn3.setStyle("-fx-background-color: #FFFF00;");
        } else {
            elevatorbtn3.setStyle("");
        }
        if (building.floorStopRequested(4)) {
            elevatorbtn4.setStyle("-fx-background-color: #FFFF00;");
        } else {
            elevatorbtn4.setStyle("");
        }
        if (building.floorStopRequested(5)) {
            elevatorbtn5.setStyle("-fx-background-color: #FFFF00;");
        } else {
            elevatorbtn5.setStyle("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        building = new Building();
        building.testStartFloor(2);
        updateState();
    }

}
