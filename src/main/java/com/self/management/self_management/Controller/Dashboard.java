package com.self.management.self_management.Controller;


import com.self.management.self_management.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard {
         @FXML
         public BorderPane borderPane;
         private static double xOffset = 0;
         private static double yOffset = 0;
        @FXML
        private AnchorPane dashBord;
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;

        @FXML
        private AnchorPane logOut;

        @FXML
        private AnchorPane message;

        @FXML
        private AnchorPane toolBox;
        private static Stage stage;
        @FXML
        void onClose() {
        System.exit(0);
        }

        @FXML
        void onDashboard() {
            message.setVisible(false);
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
        void onMessage() {
            message.setVisible(true);
            dashBord.setVisible(false);
            toolBox.setVisible(false);
            logOut.setVisible(false);
        }

        @FXML
        void onMinimize() {
            Stage stage = (Stage) toolBox.getParent().getScene().getWindow();
            stage.setIconified(true);
        }
        @FXML
        void onToolBox() {
            message.setVisible(false);
            dashBord.setVisible(false);
            toolBox.setVisible(true);
            logOut.setVisible(false);
        }
        @FXML
    public void onPressed(MouseEvent event) {
        xOffset = event.getScreenX();
        yOffset =  event.getSceneY();
    }
    @FXML
    public void onDrag() {
            stage = (Stage) borderPane.getScene().getWindow();
            borderPane.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });
        }
    @FXML
    private void onMaxiMaze(){
            stage = (Stage)borderPane.getScene().getWindow();
            maximize.setVisible(false);
            minimize.setVisible(true);
            stage.setFullScreen(true);
    }
    @FXML
    private void onExitFull(){
        stage = (Stage)borderPane.getScene().getWindow();
        maximize.setVisible(true);
        minimize.setVisible(false);
        stage.setFullScreen(false);
    }

}


