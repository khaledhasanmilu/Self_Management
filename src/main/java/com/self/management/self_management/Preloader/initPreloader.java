package com.self.management.self_management.Preloader;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class initPreloader implements Initializable {
    @FXML
    public Label lbloading;
    @FXML
    public ProgressBar progressBar;
    public static ProgressBar progressBar1;

    public static Label lbloadingg;
    public static double value;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbloadingg = lbloading;
        progressBar1 = progressBar;
    }
    public String CheckFunction(){
        final String[] massage = {""};
        Thread t1 = new Thread(() -> {
            massage[0] = "First Function";
            Platform.runLater(() -> lbloadingg.setText(massage[0]));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            massage[0] = "Second Function";
            Platform.runLater(() -> lbloadingg.setText(massage[0]));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return massage[0];
    }


    public void update() throws InterruptedException {
        value=0.1;
        Thread t1 = new Thread(() -> {

            while (progressBar1.getProgress()<1){
                Platform.runLater(() -> progressBar1.setProgress(value));
                value=value+0.01;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        t1.start();
        t1.join();

    }}
