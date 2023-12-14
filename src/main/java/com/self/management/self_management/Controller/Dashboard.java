package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import java.io.*;
import java.net.URL;
import java.sql.*;
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
    public  Image image;


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
                throw new RuntimeException(e);
            }

        }catch (Exception r){
            System.out.println("sql error");
        }
        name.setText(name.getText()+nam);
        email.setText(email.getText()+ema);
        dob.setText(dob.getText()+date);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Income", 30000),
                new PieChart.Data("For Family", 20000),
                new PieChart.Data("Book", 15000),
                new PieChart.Data("Target", 3000));
              pichart.setData(pieChartData);
        if(image!=null){
            circle.setFill(new ImagePattern(image));
        }else {
            image = new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/milu.jpg")));
            circle.setFill(new ImagePattern(image));
        }

    }
}
