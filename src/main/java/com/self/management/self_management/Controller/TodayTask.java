package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import com.self.management.self_management.Model.TaskModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Date;
import java.util.Objects;

public class TodayTask {

    @FXML
    private ImageView taskIcon;

    @FXML
    private Label taskTitle;
    public int taskId;
   public String title;
   public String Details;
   public String Progrss;
   public Date taskDate;
    public void setData(TaskModel model){
        taskTitle.setText(model.getTaskTitle());
        taskId= model.getId();
        title=model.getTaskTitle();
        Details=model.getTaskDetails();
        Progrss=model.getTaskProgrss();
        taskDate=model.getTaskDate();

        if(model.getTaskProgrss().equals("Done")){
            taskIcon.setImage(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/workDone222.png"))));
        }else {
            taskIcon.setImage(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("IMG/inprogress.png"))));

        }
    }

}
