package com.self.management.self_management.Controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ReciveMassegeCard {
    @FXML
    private Text reciveMassegeText;
    public void setReciveMassegeText(String msg){
        reciveMassegeText.setText(msg);
    }
}
