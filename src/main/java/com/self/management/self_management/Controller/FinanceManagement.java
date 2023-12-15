package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import com.self.management.self_management.Model.NoteModel;
import javafx.application.Platform;
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
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private Label total_expanselb;

    @FXML
    private Label total_incomelb;
    @FXML
    private ComboBox<String> quickSelectCombo;
    private int Total_income;
    private int Total_Expanse;
    @FXML
    void onAddIncome(ActionEvent event) throws IOException {
        LocalDate date = LocalDate.now();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("FXML/IncomeCard.fxml"));
        incomeVbox.getChildren().add(loader.load());
        IncomeCard incomeCard = loader.getController();
        incomeCard.setData(addBalanceCatagoryField.getText(),date.toString(),addBalanceField.getText(),0);
        Connection con = DB.getConnection();
        try {
            assert con != null;
            PreparedStatement pst = con.prepareStatement("INSERT INTO `incomedata`(`user`, `income_catagory`, `income_date`, `income_credit`) VALUES (?,?,?,?)");
            pst.setString(1,loginController.username);
            pst.setString(2,addBalanceCatagoryField.getText());
            pst.setDate(3, Date.valueOf(date));
            pst.setInt(4, Integer.parseInt(addBalanceField.getText()));
            pst.execute();
        } catch (SQLException e) {
            System.out.println("income connection erro");
        }
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("FXML/expanseCard.fxml"));
        expanseVbox.getChildren().add(loader.load());
        ExpanseCard expanseCard = loader.getController();
        expanseCard.setData(expanceCatagoryField.getText(),date.toString(),expanceMonyField.getText());
        Connection con = DB.getConnection();
        try {
            assert con != null;
            PreparedStatement pst = con.prepareStatement("INSERT INTO `expensedata`(`user`,`expanse_catagory`, `expanse_date`, `expanse_balance`) VALUES (?,?,?,?)");
            pst.setString(1,loginController.username);
            pst.setString(2,expanceCatagoryField.getText());
            pst.setDate(3, Date.valueOf(date));
            pst.setInt(4, Integer.parseInt(expanceMonyField.getText()));
            pst.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        initialize(null,null);
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

        Platform.exit();
    }


    @FXML
    void onMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> list = new ArrayList<>();
        Total_income = 0;
        Total_Expanse=0;
        list.add("Job");
        list.add("Bank");
        list.add("Business");
        quickSelectCombo.getItems().addAll(list);
        quickSelectCombo.setOnAction(event -> addBalanceCatagoryField.setText(quickSelectCombo.getValue()));
        incomeVbox.getChildren().clear();
        expanseVbox.getChildren().clear();
        Connection con = DB.getConnection();
        try {
            assert con != null;
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `incomedata` WHERE `user`= ?");
            pst.setString(1,loginController.username);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("FXML/IncomeCard.fxml"));
                incomeVbox.getChildren().add(loader.load());
                IncomeCard incomeCard = loader.getController();
                incomeCard.setData(rst.getString(3),rst.getDate(4).toString(), String.valueOf(rst.getInt(5)), rst.getInt(2));
                Total_income+=rst.getInt(5);
            }
            pst = con.prepareStatement("SELECT * FROM `expensedata` WHERE `user` = ?");
            pst.setString(1,loginController.username);
            rst = pst.executeQuery();
            while (rst.next()){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("FXML/expanseCard.fxml"));
                expanseVbox.getChildren().add(loader.load());
                ExpanseCard expanseCard = loader.getController();
                expanseCard.setData(rst.getString(3),rst.getDate(4).toString(), String.valueOf(rst.getInt(5)));
                Total_Expanse+=rst.getInt(5);
            }
        } catch (SQLException e) {
            System.out.println("income connection erro");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        currentBalance.setText(Total_income - Total_Expanse +"/=");
        total_expanselb.setText(Total_Expanse +"/=");
        total_incomelb.setText(Total_income +"/=");

    }
}
