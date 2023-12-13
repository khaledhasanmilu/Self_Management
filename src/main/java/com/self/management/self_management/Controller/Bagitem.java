package com.self.management.self_management.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Bagitem {
    @FXML
    private Label item;
    public void setItem(String item){
        this.item.setText(item);
    }
}
