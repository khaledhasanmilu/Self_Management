package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import com.self.management.self_management.Model.TaskModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Date;
import java.util.Objects;

public class TaskViewcard {
    @FXML
    public Label taskDate;

    @FXML
    public ImageView taskIcon;
    @FXML
    public Button taskViewCardEditbtn;
    @FXML
    public Label taskTitle;

    public int taskId;
    public String title;
    public String Details;
    public String Progrss;
    public Date task_date;
   public void setData(TaskModel model){
       taskDate.setText(model.getTaskDate().toString());
       taskTitle.setText(model.getTaskTitle());
       taskId= model.getId();
       title=model.getTaskTitle();
       Details=model.getTaskDetails();
       Progrss=model.getTaskProgrss();
       task_date=model.getTaskDate();

       if(model.getTaskProgrss().equals("Up comming")){
           taskIcon.setImage(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/Upcomingggg......png"))));
       } else if (model.getTaskProgrss().equals("In progress")){
           taskIcon.setImage(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/inprogress.png"))));
       }else{
           taskIcon.setImage(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/workDone222.png"))));
       }
   }
}
