package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.util.Objects;

public class IncomeCard {
    @FXML
    private Label inComeDate;

    @FXML
    private Label incomeCatagory;

    @FXML
    private Label incomeCredit;
    public int id;

    public void setData(String catagory,String datee , String Balance , int ida){
        incomeCatagory.setText(catagory);
        inComeDate.setText(datee);
        incomeCredit.setText(Balance);
        id = ida;
    }

}
