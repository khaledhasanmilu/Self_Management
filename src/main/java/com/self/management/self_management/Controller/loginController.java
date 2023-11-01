package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class loginController {
    @FXML
    public Button button;
    @FXML
    protected void onClose() {System.exit(0);
    }
    @FXML
    protected void onLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(MainApp.class.getResource("FXML/dashbord.fxml"));
        Stage stage = (Stage)( (Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}