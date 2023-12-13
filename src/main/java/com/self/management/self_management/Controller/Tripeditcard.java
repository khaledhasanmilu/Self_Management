package com.self.management.self_management.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Tripeditcard {
    @FXML
    public Button delete;

    @FXML
    public Label itemName;

    public void setTripData(String item){
        itemName.setText(item);
    }
}
