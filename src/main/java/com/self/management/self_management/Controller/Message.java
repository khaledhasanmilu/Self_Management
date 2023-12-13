package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Message implements Initializable {

    @FXML
    private VBox activeUserContainer;

    @FXML
    private VBox massegeContainer;

    @FXML
    private TextField massegeInputField;
    @FXML
    private ScrollPane massegeScrollbar;

    @FXML
    private Label msg_Name;

    @FXML
    private Label msg_userName;

    @FXML
    private Circle userImageCircle;

    @FXML
    void onActiveUser(ActionEvent event) {

    }



    @FXML
    void onCommunity(ActionEvent event) {

    }



    @FXML
    void onSendMassege() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXML/sendMassegeCard.fxml"));
        AnchorPane smsg = loader.load();
        SendMassegeCard contolr = loader.getController();
         contolr.sendMassegeText.setText(massegeInputField.getText());
        massegeContainer.getChildren().add(smsg);
        massegeScrollbar.needsLayoutProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                massegeScrollbar.setVvalue(1.0);
            }
        });
    }
    @FXML
    void onClose(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    void onMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image (Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/milu.jpg")));
        userImageCircle.setFill(new ImagePattern(image));
    }
}
