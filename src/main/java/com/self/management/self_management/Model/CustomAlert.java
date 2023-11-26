package com.self.management.self_management.Model;

import javafx.scene.control.Alert;

public class CustomAlert extends Alert {
    public CustomAlert(AlertType alertType,String header,String information,String title) {
        super(alertType);
        setContentText(header);
        setHeaderText(information);
        setTitle(title);
        show();
    }
}
