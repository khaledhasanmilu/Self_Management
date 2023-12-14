package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.web.*;

import java.io.IOException;
import java.util.Objects;

public class News {
    @FXML
    private WebView webView;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane loadingscreen;
    @FXML
    void onClose(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    void onMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    public void onProthomalo(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingscreen.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.prothomalo.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingscreen.setVisible(false);
                    }
                }

        );

    }

    @FXML
   public void onBackToNews(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource("FXML/ToolBox.fxml")));
        BorderPane borderPane = (BorderPane) mainPane.getParent().getParent();
        borderPane.setCenter(parent);
    }

    public void onDailyStar(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.thedailystar.net/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingscreen.setVisible(false);
                    }
                }

        );
    }
    public void onJugantor(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingscreen.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.jugantor.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingscreen.setVisible(false);
                    }
                }

        );
    }
    public void onBonikBatra(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingscreen.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://bonikbarta.net/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingscreen.setVisible(false);
                    }
                }

        );
    }
    public void onBdProtidin(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingscreen.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.ebdpratidin.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingscreen.setVisible(false);
                    }
                }

        );
    }
    public void onIttefaq(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingscreen.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.ittefaq.com.bd/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingscreen.setVisible(false);
                    }
                }

        );
    }
    public void onSomokal(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingscreen.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://samakal.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingscreen.setVisible(false);
                    }
                }

        );
    }
    public void onNoyaDiganta(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingscreen.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.dailynayadiganta.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingscreen.setVisible(false);
                    }
                }

        );
    }
    public void onJanakanta(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingscreen.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.dailyjanakantha.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingscreen.setVisible(false);
                    }
                }

        );
    }

}
