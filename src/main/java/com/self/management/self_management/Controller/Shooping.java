package com.self.management.self_management.Controller;

import com.self.management.self_management.MainApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Shooping {
    @FXML
    private WebView webView;
    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView loadingimg;
    @FXML
    void onClose(ActionEvent event) {

        Platform.exit();
    }


    @FXML
    void onMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    public void onDaraz(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.daraz.com.bd/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onAmazon(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.amazon.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }

    public void onBackToShoping() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource("FXML/ToolBox.fxml")));
        BorderPane borderPane = (BorderPane) mainPane.getParent().getParent();
        borderPane.setCenter(parent);
    }
    public void onRokomari(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.rokomari.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onBikroy(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://bikroy.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onChalDal(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://chaldal.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onAjkerDeal(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.ajkerdeal.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onPriyoShop(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://priyoshopretail.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onOthoba(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.othoba.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onAlibaba(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.alibaba.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
}
