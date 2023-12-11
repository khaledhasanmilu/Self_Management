package com.self.management.self_management.Controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SendMassegeCard {
    @FXML
    public Text sendMassegeText;
    public void setText(String text){
        sendMassegeText.setText(text);
        System.out.println(sendMassegeText);
    }
}
