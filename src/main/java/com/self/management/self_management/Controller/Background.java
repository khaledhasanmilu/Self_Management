package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class Background implements Initializable {
    private static double xOffset = 0;
    private static double yOffset = 0;
    Stage stage;
    public  Image img;

    @FXML
    private Label email;
    @FXML
    public Circle circlePhoto;

    @FXML
    private BorderPane root;

    @FXML
    private Label user;



    @FXML
    void onDashboard() {
        Node node;
        try {
            node = FXMLLoader.load(MainApp.class.getResource("FXML/Dashboard.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setCenter(node);
    }

    @FXML
    void onDrage(MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setX(event.getScreenX()-xOffset);
        stage.setY(event.getScreenY()-yOffset);
    }

    @FXML
    void onEticket() {
        Node node;
        try {
            node = FXMLLoader.load(MainApp.class.getResource("FXML/Eticket.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setCenter(node);
    }

    @FXML
    void onLogout() throws IOException {

    stage = MainApp.sStage;
    Parent parent = FXMLLoader.load(MainApp.class.getResource("FXML/login.fxml"));
    Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    void onMessage() {
        Node node;
        try {
            node = FXMLLoader.load(MainApp.class.getResource("FXML/Message.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setCenter(node);
    }

    @FXML
    void onNews() {
        Node node;
        try {
            node = FXMLLoader.load(MainApp.class.getResource("FXML/News.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setCenter(node);
    }

    @FXML
    void onNote() {
        Node node;
        try {
            node = FXMLLoader.load(MainApp.class.getResource("FXML/Note.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setCenter(node);
    }

    @FXML
    void onPrassed(MouseEvent event) {
    xOffset=event.getScreenX();
    yOffset=event.getSceneY();
    }

    @FXML
    void onShooping() {
        Node node;
        try {
            node = FXMLLoader.load(MainApp.class.getResource("FXML/Shooping.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setCenter(node);
    }

    @FXML
    void onToDo() {
        Node node;
        try {
            node = FXMLLoader.load(MainApp.class.getResource("FXML/ToDo.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setCenter(node);
    }

    @FXML
    void onToolBox() {
        Node node;
        try {
            node = FXMLLoader.load(MainApp.class.getResource("FXML/ToolBox.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setCenter(node);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        Connection con = DB.getConnection();
        PreparedStatement pst;
        ResultSet rst;
        String name = null;
        String ema = null;
        InputStream is;
        FileOutputStream fis = null;
        Blob blob;
        try {
            assert con != null;
            pst = con.prepareStatement("SELECT * FROM `userinfo` WHERE uname = ?");
            pst.setString(1, loginController.username);
            rst = pst.executeQuery();
            rst.next();
            name=rst.getString(1);
            ema=rst.getString(2);
            blob = rst.getBlob(6);
            is = blob.getBinaryStream();
            img = new Image(is);
            try {
                fis = new FileOutputStream("output.png");
                fis.write(is.readNBytes(is.available()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }catch (Exception r){
            System.out.println("sql error");
        }


        user.setText(user.getText()+name);
        email.setText(email.getText()+ema);
        Node node;
        try {
            node = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource("FXML/Dashboard.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setCenter(node);
        System.out.println(loginController.username);
        if(img!=null){
            circlePhoto.setFill(new ImagePattern(img));
        }else {
            img = new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/milu.jpg")));
            circlePhoto.setFill(new ImagePattern(img));
        }

    }


}
