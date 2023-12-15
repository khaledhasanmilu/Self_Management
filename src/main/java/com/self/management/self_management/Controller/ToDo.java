package com.self.management.self_management.Controller;

import com.self.management.self_management.DB;
import com.self.management.self_management.MainApp;
import com.self.management.self_management.Model.NoteModel;
import com.self.management.self_management.Model.TaskModel;
import javafx.application.Platform;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private TextArea editTaskDetails;

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
    private RadioButton inProgressRadiobtn;

    @FXML
    private RadioButton upCommingRadiobtn;
    @FXML
    private VBox todayTaskVbox;

    @FXML
    private VBox upcommingTaskVbox;

    private int currentTask_id ;
    @FXML
    void onCreateTaskBtn(ActionEvent event) {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(true);
        allTaskPane.setVisible(false);
        editTaskTitle.setText("");
        editTaskDate.getEditor().setText("");
        editTaskDetails.setText("");
    }

    @FXML
    void onCreateTaskCancel(ActionEvent event) {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);

    }

    @FXML
    void onCreateTaskSave(ActionEvent event) throws SQLException {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);
        //progress setting
        LocalDate todayDate = LocalDate.now();
        String progress;
        if(createTaskDate.getValue().equals(todayDate)){
            progress="In progress";
        } else if (createTaskDate.getValue().isAfter(todayDate)) {
            progress="Up comming";
        }else {
            progress="Done";
        }


        Connection con = DB.getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO `taskinfo`(`task_title`, `task_details`, `task_progress`, `task_date`,`user`) VALUES (?,?,?,?,?)");

        pst.setString(1, createTaskTitle.getText());
        pst.setString(2, createTaskDetails.getText());
        pst.setString(3,progress);
        pst.setDate(4, Date.valueOf(createTaskDate.getValue()));
        pst.setString(5, loginController.username);

        if (pst.executeUpdate() > 0) {
            System.out.println("Successfully added");

           UpdateGui();
        }



    }

    @FXML
    void onEditBack(ActionEvent event) {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);
        upCommingRadiobtn.setVisible(true);

    }

    @FXML
    void onEditDeleteTask(ActionEvent event) throws SQLException {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);
        //implement further delete from database
        Connection con = DB.getConnection();
        PreparedStatement pst;
        assert con != null;
        pst = con.prepareStatement("DELETE FROM `taskinfo` WHERE `taskid`= ?");
        pst.setInt(1, currentTask_id);
        if (pst.executeUpdate() > 0) {
            System.out.println("Successfully delete");
            UpdateGui();
        }
    }

    @FXML
    void onEditSave(ActionEvent event) throws SQLException {
        editTaskPane.setVisible(false);
        CreateTaskPane.setVisible(false);
        allTaskPane.setVisible(true);
        //implement further database save
        Connection con = DB.getConnection();
        PreparedStatement pst;


            assert con != null;
            pst = con.prepareStatement("UPDATE taskinfo SET task_title = ?, task_details = ?, task_progress = ?, task_date = ? WHERE user = ? AND  taskid = ?");
            pst.setString(1, editTaskTitle.getText());
            pst.setString(2, editTaskDetails.getText());
            if(doneRadiobtn.isSelected()){
                pst.setString(3,"Done");
            } else if (inProgressRadiobtn.isSelected()) {
                pst.setString(3,"In progress");
            } else {
                pst.setString(3,"Up comming");
            }
            pst.setDate(4, Date.valueOf(editTaskDate.getValue()));
            pst.setString(5, loginController.username);
            pst.setInt(6,currentTask_id);



        if (pst.executeUpdate() > 0) {
            System.out.println("Successfully Update");

            UpdateGui();
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
        LocalDate todayDate = LocalDate.now();
        try {
            ArrayList<TaskModel> list = getTask();
            for (int i = 0; i < list.size(); i++) {
                FXMLLoader alltaskloader  = new FXMLLoader();
                FXMLLoader todaytaskloader  = new FXMLLoader();
                FXMLLoader upcommingtaskloader  = new FXMLLoader();


                alltaskloader.setLocation(MainApp.class.getResource("FXML/taskViewcard.fxml"));
                Pane allTask = alltaskloader.load();
                allTaskVbox.getChildren().add(allTask);
                TaskViewcard allTaskController = alltaskloader.getController();
                allTaskController.setData(list.get(i));

                //add today task in vbox
                if(list.get(i).getTaskDate().toString().equals(todayDate.toString())){
                    System.out.println("Today task");
                    todaytaskloader.setLocation(MainApp.class.getResource("FXML/todayTaskcard.fxml"));
                    Pane todayTask = todaytaskloader.load();
                    todayTaskVbox.getChildren().add(todayTask);
                    TodayTask todayTaskController = todaytaskloader.getController();
                    todayTaskController.setData(list.get(i));

                    todayTask.setOnMouseClicked(event -> {
                        editTaskPane.setVisible(true);
                        CreateTaskPane.setVisible(false);
                        allTaskPane.setVisible(false);
                        currentTask_id = todayTaskController.taskId;
                        editTaskTitle.setText(todayTaskController.title);
                        editTaskDate.setValue(todayTaskController.taskDate.toLocalDate());
                        editTaskDetails.setText(todayTaskController.Details);
                        upCommingRadiobtn.setVisible(false);
                        if(todayTaskController.Progrss.equals("Done")){
                            doneRadiobtn.setSelected(true);
                        }else{
                            inProgressRadiobtn.setSelected(true);
                        }
                        System.out.println(currentTask_id);
                    });
                }
                //add upcomming task in Vbox
                if(todayDate.isBefore(list.get(i).getTaskDate().toLocalDate())){
                    System.out.println("Upcomming task");
                    upcommingtaskloader.setLocation(MainApp.class.getResource("FXML/upCommingtaskcard.fxml"));
                    Pane UpcommingTask = upcommingtaskloader.load();
                    upcommingTaskVbox.getChildren().add(UpcommingTask);
                    UpCommingtaskcard todayTaskController = upcommingtaskloader.getController();
                    todayTaskController.setData(list.get(i));
                }





                allTaskController.taskViewCardEditbtn.setOnAction(event -> {
                    editTaskPane.setVisible(true);
                    CreateTaskPane.setVisible(false);
                    allTaskPane.setVisible(false);
                    editTaskTitle.setText(allTaskController.taskTitle.getText());
                    editTaskDate.setValue(allTaskController.task_date.toLocalDate());
                    editTaskDetails.setText(allTaskController.Details);
                    if(allTaskController.Progrss.equals("Done")){
                        doneRadiobtn.setSelected(true);
                    } else if (allTaskController.Progrss.equals("Up comming")) {
                        upCommingRadiobtn.setSelected(true);
                    }else{
                        inProgressRadiobtn.setSelected(true);
                    }
                    currentTask_id=allTaskController.taskId;
                    System.out.println(currentTask_id);
                });
                allTask.setOnMouseClicked(event -> {
                    editTaskTitle.setText(allTaskController.taskTitle.getText());
                    editTaskDate.setValue(allTaskController.task_date.toLocalDate());
                    editTaskDetails.setText(allTaskController.Details);
                    editTaskPane.setVisible(true);
                    CreateTaskPane.setVisible(false);
                    allTaskPane.setVisible(false);
                    currentTask_id=allTaskController.taskId;
                    if(allTaskController.Progrss.equals("Done")){
                        doneRadiobtn.setSelected(true);
                    } else if (allTaskController.Progrss.equals("Up comming")) {
                        upCommingRadiobtn.setSelected(true);
                    }else{
                        inProgressRadiobtn.setSelected(true);
                    }
                    System.out.println(currentTask_id);
                });

            }


        } catch (SQLException e) {
            System.out.println("sql");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private ArrayList<TaskModel>getTask() throws SQLException {
        ArrayList<TaskModel> list = new ArrayList<>();
        Connection con = DB.getConnection();
        assert con != null;
        PreparedStatement pst = con.prepareStatement("SELECT * FROM taskinfo WHERE user = ?");
        pst.setString(1, loginController.username);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            String title = rst.getString(2);
            Date date = rst.getDate(5);
            String details = rst.getString(3);
            int taskID = rst.getInt(1);
            String taskProgress = rst.getString(4);
            list.add(new TaskModel(taskID,title,details,taskProgress,date));
        }
        return list;
    }
    private void UpdateGui(){
        upcommingTaskVbox.getChildren().clear();
        todayTaskVbox.getChildren().clear();
        allTaskVbox.getChildren().clear();
        initialize(null,null);
    }
}
