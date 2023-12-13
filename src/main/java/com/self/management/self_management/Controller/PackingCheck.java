package com.self.management.self_management.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class PackingCheck {
    @FXML
    public CheckBox checkox;

    @FXML
    private Label itemName;
    public void setTripData(String item){
        itemName.setText(item);
    }

}
