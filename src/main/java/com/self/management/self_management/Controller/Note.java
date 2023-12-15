package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import com.self.management.self_management.Model.NoteModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class Note implements Initializable {
    @FXML
    private TextArea NoteBodyFS;
    @FXML
    private ScrollPane scroolPane;
    @FXML
    private TextField NoteTitleFS;
    @FXML
    private AnchorPane noteView;

    @FXML
    private TextArea noteViewBody;
    @FXML
    private Button onViewEdit;

    @FXML
    private Button onViewSave;

    @FXML
    private TextField noteViewTitle;
    @FXML
    private Label noteViewDate;

    @FXML
    private AnchorPane createNotepane;

    @FXML
    private AnchorPane shownotepane;
    private String ViewTitle;
    private String ViewBody;
    private String ViewDate;
    private int noteID;
    private ArrayList<NoteModel> notelist;

    @FXML
    void onCanclebtn(ActionEvent event) {
        createNotepane.setVisible(false);
        shownotepane.setVisible(true);
    }

    @FXML
    void onClose(ActionEvent event) {

        Platform.exit();
    }


    @FXML
    void onMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onAddNotebtn(ActionEvent event) {
        shownotepane.setVisible(false);
        createNotepane.setVisible(true);
        NoteTitleFS.clear();
        NoteBodyFS.clear();
        System.out.println(createNotepane.getParent().getLayoutX());
        System.out.println(createNotepane.getParent().getLayoutY());
    }


    @FXML
    void onSavebtn(ActionEvent event) throws SQLException {


        Connection con = DB.getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `noten`(`user`, `note_title`, `note_body`) VALUES (?,?,?)");
        pst.setString(1, loginController.username);
        pst.setString(2, NoteTitleFS.getText());
        pst.setString(3, NoteBodyFS.getText());

        if (pst.executeUpdate() > 0) {
            System.out.println("Successfully added");
            createNotepane.setVisible(false);
            shownotepane.setVisible(true);
            noteView.setVisible(false);
            UpdateGUI();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GridPane noteContainer = new GridPane();
        scroolPane.setContent(noteContainer);
        int row = 0, col = 0;
        noteContainer.setAlignment(Pos.TOP_CENTER);
        try {
            notelist = getNoteList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < notelist.size(); i++) {
            NoteModel noteFd = notelist.get(i);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(MainApp.class.getResource("FXML/notecard.fxml")));
                AnchorPane note = fxmlLoader.load();
                NoteCard controller = fxmlLoader.getController();
                controller.setData(noteFd.noteTitle, noteFd.nodaBody, noteFd.date, noteFd.noteId);


                note.setOnMouseClicked(event -> {
                    shownotepane.setVisible(false);
                    createNotepane.setVisible(false);
                    noteView.setVisible(true);

                    ViewTitle = controller.getNoteTitle().getText();
                    ViewBody = controller.getNoteBody().getText();
                    ViewDate = controller.getDate().getText();
                    noteID = controller.getNoteId();
                    noteViewTitle.setText(ViewTitle);
                    noteViewBody.setText(ViewBody);
                    noteViewDate.setText(ViewDate);
                    System.out.println(noteID);
                });

//                Testing.....
//                System.out.println(noteFd.noteTitle);
//                System.out.println(noteFd.nodaBody);
//                System.out.println(noteFd.noteId);

                noteContainer.add(note, col++, row);
                if (col == 3) {
                    col = 0;
                    row++;
                }
                GridPane.setMargin(note, new Insets(10, 5, 5, 10));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }
    void UpdateGUI(){
        initialize(null,null);
    }
    @FXML
    void onBackFronView(ActionEvent event) {
        noteView.setVisible(false);
        shownotepane.setVisible(true);
        createNotepane.setVisible(false);
    }

    @FXML
    void onViewEdit(ActionEvent event) {
        noteViewTitle.setEditable(true);
        noteViewBody.setEditable(true);
        onViewEdit.setVisible(false);
        onViewSave.setVisible(true);
    }

    @FXML
    void onViewSave(ActionEvent event) throws SQLException, MalformedURLException {
        onViewEdit.setVisible(true);
        onViewSave.setVisible(false);

        /// database update
        Connection con = DB.getConnection();
        PreparedStatement pst = con.prepareStatement("UPDATE `noten` SET `note_title` = ?, `note_body` = ? ,`note_date`= NOW() WHERE `noteId` = ?");

        pst.setString(1, noteViewTitle.getText());
        pst.setString(2, noteViewBody.getText());
        pst.setInt(3, noteID);

        if (!pst.execute()) {
            System.out.println("Successfully added");
            noteViewTitle.setEditable(false);
            noteViewBody.setEditable(false);
            createNotepane.setVisible(false);
            noteView.setVisible(false);
            UpdateGUI();
            shownotepane.setVisible(true);


        }


    }

    @FXML
    void onViewDelete(ActionEvent event) throws SQLException {
        Connection con = DB.getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM `noten` WHERE `noteId`=?");
        pst.setInt(1, noteID);

        //test
        System.out.println(noteID);
        if (!pst.execute()) {
            System.out.println("Successfully deleted");
            noteViewTitle.setEditable(false);
            noteViewBody.setEditable(false);

            shownotepane.setVisible(true);
            createNotepane.setVisible(false);
            noteView.setVisible(false);

        }

        UpdateGUI();
    }

    private ArrayList<NoteModel> getNoteList() throws SQLException {
        ArrayList<NoteModel> list = new ArrayList<>();
        Connection con = DB.getConnection();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM noten WHERE user = ?");
        pst.setString(1, loginController.username);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            String title = rst.getString(4);
            String date = rst.getString(3);
            String body = rst.getString(5);
            int noteID = rst.getInt(2);
            list.add(new NoteModel(title, body, date, noteID));
        }
        return list;
    }
}
