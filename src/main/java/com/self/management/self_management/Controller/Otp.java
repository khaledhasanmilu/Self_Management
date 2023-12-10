package com.self.management.self_management.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Otp implements Initializable {
    @FXML
    private TextField otp;
    private String randomOtp;


    @FXML
    void onSubmit() {
        String Otp = otp.getText();
        if(randomOtp.equals(Otp)){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Random random = new Random();
        randomOtp  = String.format("%04d",random.nextInt(10000));
    }
}
