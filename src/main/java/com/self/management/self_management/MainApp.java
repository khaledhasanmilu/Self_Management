package com.self.management.self_management;

import com.self.management.self_management.Preloader.Launch;
import com.self.management.self_management.Preloader.initPreloader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void init() throws Exception {
        initPreloader init = new initPreloader();
        init.update();

    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("FXML/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.setProperty("javafx.preloader", Launch.class.getCanonicalName());
        launch();
    }
}