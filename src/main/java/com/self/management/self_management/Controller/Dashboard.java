package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    private Circle circle;
    @FXML
    private Label dob;
    @FXML
    private Label email;
    @FXML
    private PieChart pichart;
    @FXML
    private Label name;
    @FXML
    private Label balance_lb;
    @FXML
    private Label expanse_lb;
    @FXML
    private Label income_lb;

    public  Image image;

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

        setInfo();
        //PiChart data set
       setPichart();


    }

    private void setInfo(){
        Connection con = DB.getConnection();
        PreparedStatement pst;
        ResultSet rst;
        String nam = null;
        String ema = null;
        String date = null;

        InputStream is;
        FileOutputStream fis = null;
        Blob blob;

        try {
            assert con != null;
            pst = con.prepareStatement("SELECT * FROM `userinfo` WHERE uname = ?");
            pst.setString(1, loginController.username);
            rst = pst.executeQuery();
            rst.next();
            nam=rst.getString(1);
            ema=rst.getString(2);
            date=rst.getString(3);
            blob = rst.getBlob(6);
            is = blob.getBinaryStream();
            image = new Image(is);
            try {
                fis = new FileOutputStream("output.png");
                fis.write(is.readNBytes(is.available()));
            } catch (FileNotFoundException e) {
                System.out.println("File input problem");
            }

        }catch (Exception r){
            System.out.println("sql error");
        }
        name.setText(name.getText()+nam);
        email.setText(email.getText()+ema);
        dob.setText(dob.getText()+date);
        if(image!=null){
            circle.setFill(new ImagePattern(image));
        }else {
            image = new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/defaultuser.png")));
            circle.setFill(new ImagePattern(image));
        }
    }
    private void setPichart(){
        int total_income = 0;
        int total_expanse=0;
        int movie_total = 0;
        int foodTotal = 0;
        int transportTotal =0;
        int shoppingTotal = 0;
        int others = 0;
        Connection con = DB.getConnection();
        try {
            assert con != null;
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `incomedata` WHERE `user`= ?");
            pst.setString(1,loginController.username);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                Date date = rst.getDate(4);
                LocalDate localDate = LocalDate.now();
                if(date.toLocalDate().getMonth().compareTo(localDate.getMonth())==0){
                    total_income +=rst.getInt(5);
                }

            }
            pst = con.prepareStatement("SELECT * FROM `expensedata` WHERE `user` = ?");
            pst.setString(1,loginController.username);
            rst = pst.executeQuery();
            while (rst.next()){
                Date date = rst.getDate(4);
                LocalDate localDate = LocalDate.now();
                if(date.toLocalDate().getMonth().compareTo(localDate.getMonth())==0){
                    if (rst.getString(3).equals("Movie")){
                        movie_total+=rst.getInt(5);
                    }else if (rst.getString(3).equals("Transport")){
                        transportTotal+=rst.getInt(5);
                    }else if (rst.getString(3).equals("Food")){
                        foodTotal+=rst.getInt(5);
                    }else if (rst.getString(3).equals("Shopping")){
                        shoppingTotal+=rst.getInt(5);
                    }else{
                        others+=rst.getInt(5);
                    }
                    total_expanse+=rst.getInt(5);
                }
            }
        } catch (SQLException e) {
            System.out.println("income connection error");
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Income", total_income),
                new PieChart.Data("Movie", movie_total),
                new PieChart.Data("Transport", transportTotal),
                new PieChart.Data("Shopping", shoppingTotal),
                new PieChart.Data("Others", others),
                new PieChart.Data("Food", foodTotal));
        pichart.setData(pieChartData);
        income_lb.setText("="+total_income+"/=");
        expanse_lb.setText("="+total_expanse+"/=");
        balance_lb.setText("="+(total_income-total_expanse)+"/=");
    }

}
