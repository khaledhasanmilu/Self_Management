package com.self.management.self_management;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;


public class Launch extends Preloader {

    private Stage preloaderStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.preloaderStage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXML/initPreloader.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if(info.getType()== StateChangeNotification.Type.BEFORE_START){
            preloaderStage.hide();
        }
    }

}
