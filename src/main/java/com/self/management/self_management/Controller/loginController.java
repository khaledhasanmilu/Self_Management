package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class loginController {
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
                Parent root = FXMLLoader.load(MainApp.class.getResource("FXML/dashbord.fxml"));
                Stage stage = (Stage)( (Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                System.out.println("Invalid user password");
            }
        }catch (SQLException ev){
            System.out.println("Connection Error!!");
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
//        System.out.println("User :"+uname);
//        System.out.println("Email :"+Email);
//        System.out.println("Date of Birth :"+Dob);
//        System.out.println("Gender :"+Gender);
//        System.out.println("Password :"+password);
        if(uname==null || Email==null || Dob==null || rpass.getText()==null || rcpass.getText()==null){
            System.out.println("Must Fill up all field");
        }else{
            con = DB.getConnection();
            pst = con.prepareStatement("SELECT * FROM `userinfo` WHERE uname = ?");
            pst.setString(1,uname);
            rst = pst.executeQuery();
            if(rst.next()){
                System.out.println("user name alredy Exist!!");
            }else {
                if(rpass.getText().equals(rcpass.getText())){
                    String query = "INSERT INTO `userinfo`(`uname`, `email`, `dob`, `gender`, `password`) VALUES ('"+uname+"','"+Email+"','"+Dob+"','"+Gender+"','"+password+"')";
                    pst = con.prepareStatement(query);
                    if(pst.executeUpdate()>0){
                        System.out.println("SuccessFully Registered");
                    }else{
                        System.out.println("An Error Accourd");
                    }
                }else {
                    System.out.println("password and confirm password didn't match!!");
                }
            }
        }

    }
}