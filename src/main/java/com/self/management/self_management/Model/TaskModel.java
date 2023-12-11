package com.self.management.self_management.Model;

import java.sql.Date;

public class TaskModel {
    int id;
    String taskTitle;
    String taskDetails;
    String taskProgrss;
    Date taskDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTaskProgrss() {
        return taskProgrss;
    }

    public void setTaskProgrss(String taskProgrss) {
        this.taskProgrss = taskProgrss;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public TaskModel(int id, String taskTitle, String taskDetails, String taskProgrss, Date taskDate) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskProgrss = taskProgrss;
        this.taskDate = taskDate;
    }
}
