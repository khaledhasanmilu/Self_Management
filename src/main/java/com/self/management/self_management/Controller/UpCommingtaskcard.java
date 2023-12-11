package com.self.management.self_management.Controller;

import com.self.management.self_management.Model.TaskModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UpCommingtaskcard {
    @FXML
    private Label taskDate;
    @FXML
    private Label taskTitle;

    public int taskId;
    public void setData(TaskModel model){
        taskDate.setText(model.getTaskDate().toString());
        taskTitle.setText(model.getTaskTitle());
        taskId = model.getId();
    }

}
