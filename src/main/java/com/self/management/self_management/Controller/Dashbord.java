package com.self.management.self_management.Controller;


import com.self.management.self_management.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashbord{
    @FXML
    public static BorderPane root;
    private static double xOffset = 0;
    private static double yOffset = 0;


        @FXML
        private AnchorPane dashBord;

        @FXML
        private AnchorPane logOut;

        @FXML
        private AnchorPane messege;

        @FXML
        private AnchorPane toolBox;
        private static Stage stage;
        @FXML
        void onClose(ActionEvent event) {
        System.exit(0);
        }

        @FXML
        void onDashbord(ActionEvent event) {
            messege.setVisible(false);
            dashBord.setVisible(true);
            toolBox.setVisible(false);
            logOut.setVisible(false);
        }

        @FXML
        void onLogOut(ActionEvent event) {
            messege.setVisible(false);
            dashBord.setVisible(false);
            toolBox.setVisible(false);
            logOut.setVisible(true);
        }

        @FXML
        void onMessege(ActionEvent event) {
            messege.setVisible(true);
            dashBord.setVisible(false);
            toolBox.setVisible(false);
            logOut.setVisible(false);
        }

        @FXML
        void onMinimize(ActionEvent event) {
            Stage stage = (Stage) toolBox.getParent().getScene().getWindow();
            stage.setIconified(true);
        }
        @FXML
        void onToolBox(ActionEvent event) {
            messege.setVisible(false);
            dashBord.setVisible(false);
            toolBox.setVisible(true);
            logOut.setVisible(false);
        }


//    public void onPrassed(ActionEvent event) {
//        xOffset = ((Node)event.getSource()).getScene().getX();
//        yOffset =  ((Node)event.getSource()).getScene().getY();
//    }
//    public void onDrage(ActionEvent event) {
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage.setX(root.getLayoutX() - xOffset);
//        stage.setY(root.getLayoutY() - yOffset);
//    } Not working till now...
}


