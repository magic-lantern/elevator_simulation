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
            b.setStyle("-fx-background-color: #FFFF00;");
            System.out.println("Elevator - #" + b.getText() + " floor requested");
        } else {
            // if result is false, cancel call
            b.setStyle("");
            System.out.println("Elevator - canceling floor request to " + b.getText());
        }

        /*
        
        
         if (b.getStyle() == "-fx-background-color: #FFFF00;") {
         System.out.println("Elevator - canceling floor request to " + b.getText());
         b.setStyle("");
         //building.cancelRequestFloor();
         }
         else {
         b.setStyle("-fx-background-color: #FFFF00;");
         System.out.println("Elevator - " + b.getText() + " floor requested");
         }
                
         */
        updateState();
    }

    @FXML
    private void handleFloorBtn(ActionEvent event) {
        Button b = (Button) event.getSource();
        Elevator.State s = Elevator.State.stopped;

        if (b.getText().toLowerCase().equals("up")) {
            s = Elevator.State.up;
        }
        else if (b.getText().toLowerCase().equals("down")) {
            s = Elevator.State.down;
        }
        else {
            System.out.println("invalid value ***********");
        }

        boolean result = building.floorRequest(Integer.valueOf(b.getId().substring(5, 6)), s);

        if (result) {
            // if result is true, elevator requested
            b.setStyle("-fx-background-color: #FFFF00;");
            System.out.println(b.getId() + " - " + b.getText() + " call requested");
        } else {
            // if result is false, cancel call
            b.setStyle("");
            System.out.println(b.getId() + " - canceling " + b.getText() + " call request");
        }

        /*
         if (result) {
         // if result is true, elevator moving, update floor
         b.setStyle("-fx-background-color: #FFFF00;");
         System.out.println("Elevator - #" + b.getText() + " floor requested");
         }
         else {    
         // if result is false, cancel call
         b.setStyle("");
         System.out.println("Elevator - canceling floor request to " + b.getText());
         }
        
         if (b.getStyle() == "-fx-background-color: #FFFF00;") {
         System.out.println(b.getId() + " - canceling " + b.getText() + " call request");
         b.setStyle("");
         }
         else {
         b.setStyle("-fx-background-color: #FFFF00;");
         System.out.println(b.getId() + " - " + b.getText() + " call requested");
         }
         */
        updateState();
    }

    private void updateState() {
        elevatorStatus.setText(building.getElevatorCurrentStatus(1));
        currentFloor.setText("" + (building.getElevatorCurrentFloor(1)));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        building = new Building();
        building.testStartFloor(2);
        updateState();
    }

}
