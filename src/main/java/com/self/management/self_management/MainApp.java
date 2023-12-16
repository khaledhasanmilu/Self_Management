package com.self.management.self_management;

import com.self.management.self_management.Controller.loginController;
import com.self.management.self_management.Preloader.Launch;
import com.self.management.self_management.Preloader.initPreloader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class MainApp extends Application {
    private static double xOffset = 0;
    private static double yOffset = 0;
    public static Stage sStage;
    @Override
    public void init() throws Exception {
        initPreloader init = new initPreloader();
        init.update();

    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("FXML/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("IMG/firstlogo.png"))));
        stage.setScene(scene);
        sStage=stage;
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        try{
            Connection con = DB.getConnection();
            PreparedStatement pst;
            assert con != null;
            pst = con.prepareStatement("UPDATE `activestatus` SET `status`='Inactive' WHERE `user`= ?");
            pst.setString(1, loginController.username);
            pst.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        super.stop();
    }

    public static void main(String[] args) {
        System.setProperty("javafx.preloader", Launch.class.getCanonicalName());
        launch();
    }
}