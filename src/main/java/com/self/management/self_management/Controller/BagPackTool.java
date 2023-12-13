
package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import com.self.management.self_management.Model.CustomAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class BagPackTool implements Initializable {
    private ArrayList<String> itemlist;

    @FXML
    private AnchorPane editPane;

    @FXML
    private TextField newItemField;

    @FXML
    private VBox editVbox;

    @FXML
    private AnchorPane bagpack;

    @FXML
    private TextField items;

    @FXML
    private VBox itemvbox;

    @FXML
    private DatePicker tripdate;

    @FXML
    private TextField tripname;

    @FXML
    private VBox tripvbox;

    @FXML
    private AnchorPane checkingPane;

    @FXML
    private VBox checkingVbox;

    @FXML
    private AnchorPane createPane;
    private ArrayList<PackingCheck> currentChecker;
    private ArrayList<Tripeditcard> editlist;
    private int CheckoutD;
    @FXML
    void onAddItembtn(ActionEvent event) throws IOException {
        if(items.getText().trim().isEmpty()){
            new CustomAlert(Alert.AlertType.WARNING,"","Please add an item","warning!!");
        }else {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXML/bagitem.fxml"));
            itemvbox.getChildren().add(loader.load());
            Bagitem bagitem = loader.getController();
            bagitem.setItem(items.getText());
            itemlist.add(items.getText());
            items.setText("");
        }
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

    @FXML
    void oncreate(ActionEvent event) throws IOException {

        if(tripname.getText().trim().isEmpty() || tripdate.getEditor().getText().trim().isEmpty()){
            new CustomAlert(Alert.AlertType.WARNING,"","Please add Trip name and trip date","warning!!");
        }else {
            try {
                Connection con = DB.getConnection();
                PreparedStatement pst = con.prepareStatement("INSERT INTO `bagPack`( `tripname`, `date`,`user`) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, tripname.getText());
                pst.setDate(2, Date.valueOf(tripdate.getValue()));
                pst.setString(3, loginController.username);
                pst.execute();
                int id = 0;
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                for (String item : itemlist) {
                    pst = con.prepareStatement("INSERT INTO `packitem`(`id`, `item`) VALUES (?,?)");
                    pst.setInt(1, id);
                    pst.setString(2, item);
                    pst.executeUpdate();
                }
                itemvbox.getChildren().clear();

                // clear old list
                itemlist.clear();
                //add trip vbox
                FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXML/tripcard.fxml"));
                tripvbox.getChildren().add(loader.load());
                Tripcard tripcard = loader.getController();
                tripcard.setTripData(tripname.getText(), tripdate.getEditor().getText(), id);
                int finalId = id;

                //clear  old name and date

                tripname.clear();
                tripdate.getEditor().clear();


                tripcard.checkoutBtn.setOnAction(actionEvent -> {
                    createPane.setVisible(false);
                    checkingPane.setVisible(true);
                    editPane.setVisible(false);
                    Connection con1 = DB.getConnection();
                    PreparedStatement pst1 = null;
                    try {
                        pst1 = con1.prepareStatement("SELECT * FROM `packitem` WHERE `id`= ?");
                        pst1.setInt(1, finalId);
                        ResultSet rst = pst1.executeQuery();
                        checkingVbox.getChildren().clear();
                        currentChecker = new ArrayList<>();
                        while (rst.next()) {
                            FXMLLoader loader1 = new FXMLLoader(MainApp.class.getResource("FXML/PackingCheck.fxml"));
                            AnchorPane item = loader1.load();

                            checkingVbox.getChildren().add(item);
                            PackingCheck packingCheck = loader1.getController();
                            currentChecker.add(packingCheck);
                            packingCheck.setTripData(rst.getString(2));
                            CheckoutD = rst.getInt(1);
                        }
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                tripcard.editbtn.setOnAction(event1 -> {
                    editVbox.getChildren().clear();
                    editlist.clear();
                    createPane.setVisible(false);
                    checkingPane.setVisible(false);
                    editPane.setVisible(true);
                    Connection con1 = DB.getConnection();
                    PreparedStatement pst1 = null;
                    try {
                        pst1 = con1.prepareStatement("SELECT * FROM `packitem` WHERE `id`= ?");
                        pst1.setInt(1, finalId);
                        ResultSet rst2 = pst1.executeQuery();
                        editVbox.getChildren().clear();
                        currentChecker = new ArrayList<>();
                        while (rst2.next()) {
                            FXMLLoader loader1 = new FXMLLoader(MainApp.class.getResource("FXML/tripeditcard.fxml"));
                            AnchorPane item = loader1.load();
                            editVbox.getChildren().add(item);
                            Tripeditcard editcard = loader1.getController();
                            editlist.add(editcard);
                            editcard.setTripData(rst2.getString(2));
                            CheckoutD = rst2.getInt(1);

                            //set on action for delete item
                            editcard.delete.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    editlist.remove(editcard);
                                    editVbox.getChildren().remove(item);
                                    System.out.println(editlist);
                                }
                            });


                        }
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });


            } catch (SQLException e) {
                System.out.println();
            }


        }
    }
    @FXML
    void onBack(ActionEvent event) {
        createPane.setVisible(true);
        checkingPane.setVisible(false);
        editPane.setVisible(false);
    }

    @FXML
    void onBackTobackpack(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource("FXML/ToolBox.fxml")));
        BorderPane borderPane = (BorderPane) bagpack.getParent();
        borderPane.setCenter(parent);
    }
    @FXML
    void onDone(ActionEvent event) throws SQLException {
        int it = checkingVbox.getChildren().size();
        System.out.println(it);
        boolean isCheckall= true;
        for (int i = 0; i < it; i++) {
            if(!currentChecker.get(i).checkox.isSelected()){
                System.out.println("plz select all");
                new CustomAlert(Alert.AlertType.WARNING,"","Please check all item","Checkout prolem");
                isCheckall = false;
                break;
            }

        }
        if(isCheckall){
            System.out.println("Successfully Checkout");
            new CustomAlert(Alert.AlertType.INFORMATION,"","Successfully checkout","Done");
            Connection con = DB.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM `bagPack` WHERE `id`=?");
            pst.setInt(1,CheckoutD);
            pst.execute();
            pst=con.prepareStatement("DELETE FROM `packitem` WHERE `id`=?");
            pst.setInt(1,CheckoutD);
            pst.execute();
            checkingPane.setVisible(false);
            createPane.setVisible(true);
            initialize(null,null);

        }

    }


    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        Connection con = DB.getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM `bagPack` WHERE `id`=?");
        pst.setInt(1,CheckoutD);
        pst.execute();
        pst=con.prepareStatement("DELETE FROM `packitem` WHERE `id`=?");
        pst.setInt(1,CheckoutD);
        pst.execute();
        checkingPane.setVisible(false);
        createPane.setVisible(true);
        editPane.setVisible(false);
        new CustomAlert(Alert.AlertType.CONFIRMATION,"","Successfully Deleted","Delete!!");
        initialize(null,null);
    }


    @FXML   //new add on editpane
    void onAddNew(ActionEvent event) throws IOException {
        if(newItemField.getText().trim().isEmpty()){
            new CustomAlert(Alert.AlertType.WARNING,"","Please add item before add to Trip","warning!!");

        }else{
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXML/trimeditcard.fxml"));
        editVbox.getChildren().add(loader.load());
        Tripeditcard editcard =loader.getController();
        editcard.setTripData(newItemField.getText());
        newItemField.setText("");
        editlist.add(editcard);
        }
    }

    @FXML
    void onUpdate(ActionEvent event) throws SQLException {
        //at first delete old data
        Connection con = DB.getConnection();
        PreparedStatement pst;
        pst=con.prepareStatement("DELETE FROM `packitem` WHERE `id`=?");
        pst.setInt(1,CheckoutD);
        pst.execute();
        //now update new data

        for (Tripeditcard edititem:editlist) {
            pst=con.prepareStatement("INSERT INTO `packitem`(`id`, `item`) VALUES (?,?)");
            pst.setInt(1,CheckoutD);
            pst.setString(2,edititem.itemName.getText());
            pst.execute();
        }
        new CustomAlert(Alert.AlertType.CONFIRMATION,"","Successfully Updated your Trip Item","Updated");
        editPane.setVisible(false);
        createPane.setVisible(true);
        checkingPane.setVisible(false);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    itemlist=new ArrayList<>();
    editlist = new ArrayList<>();
    //clear all vbox

    tripvbox.getChildren().clear();
    itemvbox.getChildren().clear();
    editVbox.getChildren().clear();

        Connection con = DB.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("SELECT * FROM `bagPack` WHERE `user`= ?");
            pst.setString(1,loginController.username);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXML/tripcard.fxml"));
                tripvbox.getChildren().add(loader.load());
                Tripcard tripcard =loader.getController();
                int finalId = rst.getInt(1);
                tripcard.setTripData(rst.getString(2),rst.getString(3),finalId);

                tripcard.checkoutBtn.setOnAction(actionEvent -> {
                    createPane.setVisible(false);
                    checkingPane.setVisible(true);
                    editPane.setVisible(false);


                    Connection con1 = DB.getConnection();
                    PreparedStatement pst1 = null;
                    try {
                        pst1 = con1.prepareStatement("SELECT * FROM `packitem` WHERE `id`= ?");
                        pst1.setInt(1, finalId);
                        ResultSet rst1 = pst1.executeQuery();
                        checkingVbox.getChildren().clear();
                        currentChecker=new ArrayList<>();
                        while (rst1.next()){
                            FXMLLoader loader1 = new FXMLLoader(MainApp.class.getResource("FXML/PackingCheck.fxml"));
                            AnchorPane item = loader1.load();

                            checkingVbox.getChildren().add(item);
                            PackingCheck packingCheck = loader1.getController();
                            currentChecker.add(packingCheck);
                            packingCheck.setTripData(rst1.getString(2));
                            CheckoutD=rst1.getInt(1);
                        }
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                tripcard.editbtn.setOnAction(event1 -> {
                    //clear previous data
                    editlist.clear();
                    editVbox.getChildren().clear();

                    //visibility check up
                    createPane.setVisible(false);
                    checkingPane.setVisible(false);
                    editPane.setVisible(true);

                    //database connection
                    Connection con1 = DB.getConnection();
                    PreparedStatement pst1 = null;
                    try {
                        pst1 = con1.prepareStatement("SELECT * FROM `packitem` WHERE `id`= ?");
                        pst1.setInt(1, finalId);
                        ResultSet rst1 = pst1.executeQuery();
                        while (rst1.next()){
                            FXMLLoader loader1 = new FXMLLoader(MainApp.class.getResource("FXML/tripeditcard.fxml"));
                            AnchorPane item = loader1.load();

                            editVbox.getChildren().add(item);
                            Tripeditcard editcard = loader1.getController();
                            editlist.add(editcard);                         //add controller in arraylist
                            editcard.setTripData(rst1.getString(2));
                            CheckoutD=rst1.getInt(1);

                            //delete item button onAction
                            editcard.delete.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    editlist.remove(editcard);
                                    editVbox.getChildren().remove(item);
                                    System.out.println(editlist);
                                }
                            });

                        }
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}