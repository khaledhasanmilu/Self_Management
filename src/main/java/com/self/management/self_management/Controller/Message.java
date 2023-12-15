package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import com.self.management.self_management.Model.CustomAlert;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.*;
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
    private Label msg_userName;

    @FXML
    private Circle userImageCircle;

    @FXML
    private TextField userSearchInput;

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    boolean isCommunity=true;
    private String currentSendUser;
    @FXML
    void onActiveUser(ActionEvent event) {
    activeUserContainer.setVisible(true);
    massegeContainer.getChildren().clear();
    }

    @FXML
    void onCommunity(ActionEvent event) {
        massegeContainer.getChildren().clear();
    isCommunity=true;
    activeUserContainer.setVisible(false);
    msg_userName.setText("Community");
    setCommunityImg();
    }
    @FXML
    void onSearchUser(ActionEvent event) throws SQLException {
        if(userSearchInput.getText().trim().isEmpty()){
            new CustomAlert(Alert.AlertType.WARNING,"","Write a username to search","");
        }else {
            Connection con = DB.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT `user`, `status` FROM `activestatus` WHERE `user`=?");
            pst.setString(1,userSearchInput.getText());
            ResultSet resultSet = pst.executeQuery();
           if(resultSet.next()){
               if(resultSet.getString(2).equals("active")){
                   currentSendUser = userSearchInput.getText();
                   msg_userName.setText(userSearchInput.getText());
                   isCommunity=false;
                   pst = con.prepareStatement("UPDATE `activestatus` SET `invoke`= 1,`invoke_user`= ? WHERE `user`= ?");
                   pst.setString(1,loginController.username);
                   pst.setString(2,userSearchInput.getText());
                   pst.execute();
                   setUserImg(userSearchInput.getText());
               }else {
                   new CustomAlert(Alert.AlertType.WARNING,"","This User Inactive Now","");
               }
           }else {
               new CustomAlert(Alert.AlertType.WARNING,"","This is an Invalid Username","");
           }

        }
    }

    private void setUserImg(String user) {
        InputStream is;
        Blob blob;
        Image image=null;
        try {
            Connection con = DB.getConnection();
            assert con != null;
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `userinfo` WHERE `uname`= ?");
            pst.setString(1, user);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                blob = resultSet.getBlob(6);
                if(!(blob == null)){
                is = blob.getBinaryStream();
                image = new Image(is);
                }

            }
            if(image!=null){
                userImageCircle.setFill(new ImagePattern(image));
            }else {
                image = new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/defaultuser.png")));
                userImageCircle.setFill(new ImagePattern(image));
            }

        } catch (SQLException e) {
            System.out.println("sql exeption in user ims setup");
        }
    }
    private void setCommunityImg(){
        Image img = new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/community.png")));
        userImageCircle.setFill(new ImagePattern(img));
    }

    @FXML
    void onSendMassege() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXML/sendMassegeCard.fxml"));
            AnchorPane smsg = loader.load();
            SendMassegeCard contolr = loader.getController();
            contolr.sendMassegeText.setText(massegeInputField.getText());
            String msgForsend;
            if (isCommunity) {
                msgForsend = loginController.username + "<c>" + massegeInputField.getText();
            } else {
                msgForsend = currentSendUser + "<@>" + massegeInputField.getText();
            }
            dos.writeUTF(msgForsend);
            massegeInputField.clear();
            massegeContainer.getChildren().add(smsg);
            massegeScrollbar.needsLayoutProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) {
                    massegeScrollbar.setVvalue(1.0);
                }
            });
        }catch (IOException e){
            new CustomAlert(Alert.AlertType.ERROR,"","Server Connection Failed","");
        }
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
        msg_userName.setText("Community");
        Image image = new Image (Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/community.png")));
        userImageCircle.setFill(new ImagePattern(image));
        try{
            socket = new Socket("192.168.0.148",8080);
            dis= new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(loginController.username);
            Thread thread = new Thread(() -> {
                while (true){
                    try {
                        String reciveMsg = dis.readUTF();
                        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXML/reciveMassegeCard.fxml"));
                        Node node = loader.load();
                        ReciveMassegeCard reciveMassegeCard = loader.getController();

                        Platform.runLater(() -> {
                            if(isCommunity){
                                if(reciveMsg.contains("<c>")){
                                    reciveMassegeCard.setReciveMassegeText(reciveMsg);
                                    massegeContainer.getChildren().add(node);
                                }
                            }else {
                                if (!reciveMsg.contains("<c>")){
                                    reciveMassegeCard.setReciveMassegeText(reciveMsg);
                                    massegeContainer.getChildren().add(node);
                                }
                            }

                            massegeScrollbar.needsLayoutProperty().addListener((observable, oldValue, newValue) -> {
                                if (!newValue) {
                                    massegeScrollbar.setVvalue(1.0);
                                }
                            });
                        });
                        System.out.println(reciveMsg);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.setDaemon(true);
            Platform.runLater(thread::start);

        } catch (IOException e) {
            new CustomAlert(Alert.AlertType.INFORMATION,"Error","Contact admin to run server","Connection Error");
        }
    }
}
