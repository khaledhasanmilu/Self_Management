package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ToDo implements Initializable {
    @FXML
    private AnchorPane CreateTaskPane;

    @FXML
    private AnchorPane allTaskPane;

    @FXML
    private VBox allTaskVbox;

    @FXML
    private DatePicker createTaskDate;

    @FXML
    private TextArea createTaskDetails;

    @FXML
    private TextArea createTaskDetails1;

    @FXML
    private TextField createTaskTitle;

    @FXML
    private RadioButton doneRadiobtn;

    @FXML
    private DatePicker editTaskDate;

    @FXML
    private AnchorPane editTaskPane;

    @FXML
    private TextField editTaskTitle;

    @FXML
    private RadioButton inProgressradionbtn;

    @FXML
    private AnchorPane todayTaskPane;

    @FXML
    private VBox todayTaskVbox;

    @FXML
    private AnchorPane upcommingTaskPane;

    @FXML
    private VBox upcommingTaskVbox;


    @FXML
    void onCreateTaskBtn(ActionEvent event) {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(true);
        allTaskPane.setVisible(false);

    }

    @FXML
    void onCreateTaskCancel(ActionEvent event) {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);

    }

    @FXML
    void onCreateTaskSave(ActionEvent event) {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);
        //implemest further database save

    }

    @FXML
    void onEditBack(ActionEvent event) {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);

    }

    @FXML
    void onEditDeleteTask(ActionEvent event) {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);
        //implement further delete from database

    }

    @FXML
    void onEditSave(ActionEvent event) {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);
        //implement further database save
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 5; i++) {
            FXMLLoader all = new FXMLLoader(MainApp.class.getResource("FXML/taskViewcard.fxml"));
            FXMLLoader today = new FXMLLoader(MainApp.class.getResource("FXML/todayTaskcard.fxml"));
            FXMLLoader upcomming = new FXMLLoader(MainApp.class.getResource("FXML/upCommingtaskcard.fxml"));
            try {
                Parent alltasknode = all.load();
                Parent todaytasknode = today.load();
                Parent upcommingtasknode = upcomming.load();
                TaskViewcard taskViewcard = all.getController();
                taskViewcard.taskViewCardEditbtn.setOnAction(event -> {
                    editTaskPane.setVisible(true);
                    CreateTaskPane.setVisible(false);
                    allTaskPane.setVisible(false);
                });
                alltasknode.setOnMouseClicked(event -> {
                    editTaskTitle.setText(taskViewcard.taskTitle.getText());
                    editTaskDate.getEditor().setText(taskViewcard.taskDate.getText());
                    editTaskPane.setVisible(true);
                    CreateTaskPane.setVisible(false);
                    allTaskPane.setVisible(false);
                });
                todaytasknode.setOnMouseClicked(event -> {
                    editTaskPane.setVisible(true);
                    CreateTaskPane.setVisible(false);
                    allTaskPane.setVisible(false);
                });
                allTaskVbox.getChildren().add(alltasknode);
                upcommingTaskVbox.getChildren().add(upcommingtasknode);
                todayTaskVbox.getChildren().add(todaytasknode);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
