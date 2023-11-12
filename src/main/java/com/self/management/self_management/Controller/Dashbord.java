package com.self.management.self_management.Controller;


import com.self.management.self_management.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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
        void onLogOut(ActionEvent event) throws IOException {
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("FXML/login.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
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


    public void onPrassed(ActionEvent event) {
        xOffset = ((Node)event.getSource()).getScene().getX();
        yOffset =  ((Node)event.getSource()).getScene().getY();
    }
    public void onDrage(ActionEvent event) {
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setX(root.getLayoutX() - xOffset);
        stage.setY(root.getLayoutY() - yOffset);
    }
}


