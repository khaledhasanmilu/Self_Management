package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class Setting implements Initializable {

    @FXML
    private Circle circle;

    @FXML
    private Label dob;

    @FXML
    private Button editbtn;

    @FXML
    private Label email;
    @FXML
    private Label genderlbl;
    @FXML
    private Label name;
    @FXML
    private Button savebtn;
    @FXML
    private Button cancelbtn;

    private File file;
    public Image image;
    @FXML
    void onClose(ActionEvent event) {

        Platform.exit();
    }


    @FXML
    void onMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
}
    @FXML
    void onEditPhoto() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) email.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        image = new Image(new FileInputStream(file));
        circle.setFill(new ImagePattern(image));
        editbtn.setVisible(false);
        savebtn.setVisible(true);
        cancelbtn.setVisible(true);
        System.out.println(file);
    }

    @FXML
    void onCancelImgbtn() {
        savebtn.setVisible(false);
        cancelbtn.setVisible(false);
        editbtn.setVisible(true);
        circle.setFill(Color.WHITE);
    }

    @FXML
    void onSaveImgbtn() throws IOException, SQLException {
        circle.setFill(new ImagePattern(image));
        InputStream in = new FileInputStream(file);
        Connection connection = DB.getConnection();
        String query = "UPDATE userinfo SET image = ? WHERE uname = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setBlob(1,in,in.available());
        statement.setString(2,loginController.username);

        statement.execute();
        Background background = loginController.getBG();
        background.circlePhoto.setFill(new ImagePattern(image));
        savebtn.setVisible(false);
        cancelbtn.setVisible(false);
        editbtn.setVisible(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    Connection con = DB.getConnection();
    PreparedStatement pst;
    ResultSet rst;
    String nam = null;
    String ema = null;
    String date = null;
    String gender = null;
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
        gender=rst.getString(4);
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
        genderlbl.setText(genderlbl.getText()+gender);
        if(image!=null){
            circle.setFill(new ImagePattern(image));
        }else {
            image = new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/defaultuser.png")));
            circle.setFill(new ImagePattern(image));
        }
   }


}
