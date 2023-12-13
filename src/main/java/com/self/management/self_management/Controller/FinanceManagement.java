package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FinanceManagement {

    @FXML
    private AnchorPane financemain;


    @FXML
    void onClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onBackToFinance(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource("FXML/ToolBox.fxml")));
        BorderPane borderPane = (BorderPane) financemain.getParent();
        borderPane.setCenter(parent);
    }

    @FXML
    void onMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
