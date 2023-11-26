package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import com.self.management.self_management.Model.CustomAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class loginController {
    public static String username;
    Connection con;
    PreparedStatement pst;
    ResultSet rst;
    @FXML
    private DatePicker dob;
    @FXML
    private Pane Login;
    @FXML
    private Pane Registration;
    @FXML
    private ToggleGroup gender;
    @FXML
    protected Button button;
    @FXML
    private PasswordField rcpass;
    @FXML
    private PasswordField rpass;

    @FXML
    private TextField runame;
    @FXML
    private TextField email;
    @FXML
    private PasswordField lpass;
    @FXML
    private TextField luser;
    @FXML
    protected void onClose() {System.exit(0);
    }
    @FXML
    protected void onLogin(ActionEvent e) throws IOException{
        username = luser.getText();
        String user = luser.getText();
        String pass = lpass.getText();
        try {
            con = DB.getConnection();
            if(con == null){
                throw new SQLException();
            }
            pst = con.prepareStatement("SELECT * FROM `userinfo` WHERE uname = ? and password =? ");
            pst.setString(1,user);
            pst.setString(2,pass);
            rst = pst.executeQuery();
            if(rst.next()){
                BorderPane root = FXMLLoader.load(MainApp.class.getResource("FXML/Background.fxml"));
                Stage stage = (Stage)( (Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                System.out.println("Invalid Password!!");
                new CustomAlert(Alert.AlertType.ERROR,"Please Enter Valid Username and password.","User Id or password Didn't Match.","Invalid Log In!");


            }
        }catch (SQLException ev){
            System.out.println("Connection Error!!");
            new CustomAlert(Alert.AlertType.INFORMATION,"Check your internet Connection.","Can not Connect to server.","Server Failed!");
        }

    }
    @FXML
    protected void onRegistrationLink(){
        Login.setVisible(false);
        Registration.setVisible(true);
    }
    @FXML
    protected void onLoginlink(){
        Login.setVisible(true);
        Registration.setVisible(false);
    }
    @FXML
    protected void onRegibtn() throws SQLException {
        RadioButton rdbtn = (RadioButton) gender.getSelectedToggle();
        String uname = runame.getText();
        String Email = email.getText();
        LocalDate Dob = dob.getValue();
        String Gender = rdbtn.getText();
        String password = rcpass.getText();

        if(uname==null||Email==null||Dob==null||Gender==null||rpass.getText()==null||rcpass.getText()==null){
            System.out.println("Must Fill up all field");
            new CustomAlert(Alert.AlertType.ERROR,"Please provide all Information","Registration Falid!","Faild!");

        }else{
            con = DB.getConnection();
            pst = con.prepareStatement("SELECT * FROM `userinfo` WHERE uname = ?");
            pst.setString(1,uname);
            rst = pst.executeQuery();
            if(rst.next()){
                System.out.println("user name alredy Exist!!");
                new CustomAlert(Alert.AlertType.ERROR,"Please use new Username.","User name alredy Exist!!","Failed!");

            }else {
                if(rpass.getText().equals(rcpass.getText())){
                    String query = "INSERT INTO `userinfo`(`uname`, `email`, `dob`, `gender`, `password`) VALUES ('"+uname+"','"+Email+"','"+Dob+"','"+Gender+"','"+password+"')";
                    pst = con.prepareStatement(query);
                    if(pst.executeUpdate()>0){
                        System.out.println("SuccessFully Registered");
                        new CustomAlert(Alert.AlertType.ERROR,"Now you can log in.!!","Congratulationd!Successfully Registered","Registered!");
                        Login.setVisible(true);
                        Registration.setVisible(false);
                    }else{
                        System.out.println("An Error Accourd");
                    }
                }else {
                    System.out.println("password and confirm password didn't match!!");
                    new CustomAlert(Alert.AlertType.ERROR,"Password and Confirm Password didn't match!!!","Please provide same password.","Failed!");
                }
            }
        }

    }
}