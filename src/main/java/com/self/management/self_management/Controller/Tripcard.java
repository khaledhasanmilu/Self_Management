package com.self.management.self_management.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Tripcard {
    @FXML
    public Button editbtn;
    @FXML
    private Label trip_name;
    @FXML
    private Label tripDate;
    public Button checkoutBtn;
    public int id;
    public void setTripData(String tripname , String Date ,int ida){
        trip_name.setText(tripname);
        tripDate.setText(Date);
        id=ida;
    }
}
