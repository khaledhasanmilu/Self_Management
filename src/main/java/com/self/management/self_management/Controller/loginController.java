package com.self.management.self_management.Controller;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import com.self.management.self_management.Model.CustomAlert;
import com.self.management.self_management.Model.OtpModel;
import com.self.management.self_management.PassWordHash;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import java.util.Random;


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
    private PasswordField cnewpass;
    @FXML
    private PasswordField newPass;
    @FXML
    private Pane restePass;
    @FXML
    private Pane mailSearch;
    @FXML
    private TextField searchMail;
    @FXML
    private TextField searchUser;

    @FXML
    private TextField otp;
    @FXML
    private TextField runame;
    @FXML
    private TextField email;
    @FXML
    private PasswordField lpass;
    @FXML
    private TextField luser;
    @FXML
    private Pane otpPane;
    private String randomOtp;
    int checker;

    ///for local use
    String uname ;
    String Email ;
    LocalDate Dob ;
    String Gender ;
    String password ;
    String userFromSearch;
    String emailFromSearch;
    @FXML
    protected void onClose() {System.exit(0);
    }
    @FXML
    protected void onLogin(ActionEvent e) throws IOException{
        username = luser.getText();
        String user = luser.getText();
        String pass = PassWordHash.hashPassword(lpass.getText());
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
                FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXML/Background.fxml"));
                BorderPane root = loader.load();
                setbg(loader.getController());
                Stage stage = (Stage)( (Node)e.getSource()).getScene().getWindow();
                stage.setX(80);
                stage.setY(30);
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
    protected void onRegibtn() throws SQLException, IOException {
        RadioButton rdbtn = (RadioButton) gender.getSelectedToggle();
        uname = runame.getText();
        Email = email.getText();
        Dob = dob.getValue();
        Gender = rdbtn.getText();
        password = rcpass.getText();
        String mailsub = "Confirm your otp for Self Management";
        System.out.println(uname);
        System.out.println(Email);
        System.out.println(Dob);
        System.out.println(Gender);
        if( uname.isEmpty() ||Email.isEmpty() ||Dob==null||Gender==null||rpass.getText().isEmpty()||rcpass.getText().isEmpty()){
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
//                    String query = "INSERT INTO `userinfo`(`uname`, `email`, `dob`, `gender`, `password`) VALUES ('"+uname+"','"+Email+"','"+Dob+"','"+Gender+"','"+password+"')";
//                    pst = con.prepareStatement(query);
//                    if(pst.executeUpdate()>0){
//                        System.out.println("SuccessFully Registered");
//                        new CustomAlert(Alert.AlertType.ERROR,"Now you can log in.!!","Congratulationd!Successfully Registered","Registered!");
//                        Login.setVisible(true);
//                        Registration.setVisible(false);
//                    }else{
//                        System.out.println("An Error Accourd");
//                    }
                    Registration.setVisible(false);
                    checker=1;
                    Random random = new Random();
                    randomOtp  = String.format("%04d",random.nextInt(10000));
                    OtpModel otpModel = new OtpModel();
                    try {
                        otpModel.SendOtp(randomOtp,Email,mailsub);
                    } catch (MailjetSocketTimeoutException | MailjetException e) {
                        throw new RuntimeException(e);
                    }

                    otpPane.setVisible(true);
                }else {
                    System.out.println("password and confirm password didn't match!!");
                    new CustomAlert(Alert.AlertType.ERROR,"Password and Confirm Password didn't match!!!","Please provide same password.","Failed!");
                }
            }
        }

    }
    @FXML
    void OnnewPassbtn() throws SQLException {
        String newp = newPass.getText();
        String cnpass = cnewpass.getText();
            if (newp.equals(cnpass)) {
                String hashPassword = PassWordHash.hashPassword(cnpass);
                String query = "UPDATE `userinfo` SET `password`='"+hashPassword+"' WHERE `uname`='"+userFromSearch+"'";
                pst = con.prepareStatement(query);
                if (pst.executeUpdate() > 0) {
                    System.out.println("SuccessFully Change Password");
                    new CustomAlert(Alert.AlertType.INFORMATION, "Successfully change!!", "Congratulationd!Successfully Changed your Password!", "Password Changed");
                    Login.setVisible(true);
                    Registration.setVisible(false);
                    otpPane.setVisible(false);
                    restePass.setVisible(false);
                }
            }else {
                System.out.println("pass and cpass didnt match");
                new CustomAlert(Alert.AlertType.WARNING, "Didn't match", "Password and Confirm Password should be same.", "Password and Confirm Password didn't match");
            }
    }

    @FXML
    public void onSubmit() throws SQLException, MailjetSocketTimeoutException, MailjetException {
        String Otp = otp.getText();
        System.out.println(randomOtp);
       if (checker==1){
           if(randomOtp.equals(Otp)){
               String hashpassword = PassWordHash.hashPassword(password);
               String query = "INSERT INTO `userinfo`(`uname`, `email`, `dob`, `gender`, `password`) VALUES ('"+uname+"','"+Email+"','"+Dob+"','"+Gender+"','"+hashpassword+"')";
               pst = con.prepareStatement(query);
               if(pst.executeUpdate()>0){
                   System.out.println("SuccessFully Registered");
                   new CustomAlert(Alert.AlertType.INFORMATION,"Now you can log in.!!","Congratulationd!Successfully Registered","Registered!");
                   pst = con.prepareStatement("INSERT INTO `activestatus`(`user`, `status`) VALUES (?,?)");
                   pst.setString(1,uname);
                   pst.setString(2,"Inactive");
                   pst.execute();
                   Login.setVisible(true);
                   Registration.setVisible(false);
                   otpPane.setVisible(false);
               }else{
                   System.out.println("An Error Accourd");

               }
           }else {
               new CustomAlert(Alert.AlertType.ERROR,"Invalid","Please Enter a Valid Otp","Invalid OTP");
           }
       }else {
           if(randomOtp.equals(Otp)){

              otpPane.setVisible(false);

              restePass.setVisible(true);
           }else {
               new CustomAlert(Alert.AlertType.ERROR,"Invalid","Please Enter a Valid Otp","Invalid OTP");
           }
       }
    }

    @FXML
    void onBackFromOtp(ActionEvent event) {
        otpPane.setVisible(false);
        restePass.setVisible(false);
        Registration.setVisible(true);
        Login.setVisible(false);

    }

    @FXML
    void onForgot() {
        Login.setVisible(false);
        otpPane.setVisible(false);
        Registration.setVisible(false);
        mailSearch.setVisible(true);
    }

    @FXML
    void onSearch() throws SQLException {
        userFromSearch = searchUser.getText();
        emailFromSearch = searchMail.getText();
        try {
            con = DB.getConnection();
            if (con == null) {
                throw new SQLException();
            }
            pst = con.prepareStatement("SELECT * FROM `userinfo` WHERE uname = ? and email =? ");
            pst.setString(1, userFromSearch);
            pst.setString(2, emailFromSearch);
            rst = pst.executeQuery();
            if(rst.next()){
               mailSearch.setVisible(false);
                Random random = new Random();
                randomOtp  = String.format("%04d",random.nextInt(10000));
                checker=-1;

                OtpModel otpModel = new OtpModel();
                String mailsub = "Reset Password OTP";

                otpModel.SendOtp(randomOtp,emailFromSearch,mailsub);

                System.out.println(randomOtp);
                System.out.println(emailFromSearch);

                otpPane.setVisible(true);
            }
            else{
                System.out.println("Invalid User or Email");
                new CustomAlert(Alert.AlertType.ERROR,"Invalid user","Please Enter a Valid Username and email","Invalid user or email");


            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void onBackFromnewpassword(ActionEvent event) {
        otpPane.setVisible(false);
        restePass.setVisible(false);
        Registration.setVisible(false);
        mailSearch.setVisible(false);
        Login.setVisible(true);
    }
    public static Background bg;
    private void setbg(Background bg1){
        bg = bg1;
    }
    public static Background getBG(){
        return bg;
    }
}