package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class FinanceManagement implements Initializable {



    @FXML
    private TextField addBalanceCatagoryField;

    @FXML
    private TextField addBalanceField;

    @FXML
    private Label currentBalance;

    @FXML
    private TextField expanceCatagoryField;

    @FXML
    private TextField expanceMonyField;

    @FXML
    private VBox expanseVbox;

    @FXML
    private AnchorPane financemain;

    @FXML
    private VBox incomeVbox;

    @FXML
    private ComboBox<String> quickSelectCombo;

    @FXML
    void onAddIncome(ActionEvent event) {

    }


    @FXML
    void onFood(ActionEvent event) {
        expanceCatagoryField.setText("Food");
    }



    @FXML
    void onMovie(ActionEvent event) {
        expanceCatagoryField.setText("Movie");
    }

    @FXML
    void onSaveExpanse(ActionEvent event) throws IOException {
        LocalDate date = LocalDate.now();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXML/expanseCard.fxml"));
        ExpanseCard expanseCard = loader.getController();
        expanseCard.setData(expanceCatagoryField.getText(),date.toString(),expanceMonyField.getText());
        expanseVbox.getChildren().add(loader.load());
    }

    @FXML
    void onShoping(ActionEvent event) {
        expanceCatagoryField.setText("Shopping");
    }

    @FXML
    void onTransport(ActionEvent event) {
        expanceCatagoryField.setText("Transport");
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
        ArrayList<String> list = new ArrayList<>();
        list.add("Job");
        list.add("Bank");
        list.add("Business");
        quickSelectCombo.getItems().addAll(list);
        quickSelectCombo.setOnAction(event -> addBalanceCatagoryField.setText(quickSelectCombo.getValue()));
    }
}
