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
    
    private Building building;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        System.out.println(building.getElevatorCurrentStatus(1));
    }
    
    @FXML
    private void handleElevatorBtn(ActionEvent event) {
        Button b = (Button)event.getSource();        
        b.setStyle("-fx-background-color: #FFFF00;");
        //elevatorStatus.setText(building.getElevatorCurrentStatus(1));
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        building = new Building();        
    }
        
}
