package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ExpanseCard {
    @FXML
    private ImageView catagoryImg;

    @FXML
    private Label catagoryName;

    @FXML
    private Label date;

    @FXML
    private Label expanseBalace;

    public void setData(String catagory,String datee , String Balance){
        catagoryName.setText(catagory);
        date.setText(datee);
        expanseBalace.setText(Balance);
        if(catagory.trim().equals("Food")){
            catagoryImg.setImage(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/icons8-food-80.png"))));
        } else if (catagory.trim().equals("Transport")) {
            catagoryImg.setImage(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/ricshaaa.png"))));
        } else if (catagory.trim().equals("Movie")) {
            catagoryImg.setImage(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/movie-ticket-icon.png"))));
        }else if (catagory.trim().equals("Shopping")){
            catagoryImg.setImage(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/icons8-fast-cart-64.png"))));
        }
    }
}
