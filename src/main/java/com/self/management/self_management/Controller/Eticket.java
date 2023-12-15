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

public class Eticket {
    @FXML
    private WebView webView;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane ticketPane;
    @FXML
    private ImageView loadingimg;
    @FXML
    private AnchorPane hotelPane;
    @FXML
    void onClose(ActionEvent event) {

        Platform.exit();
    }


    @FXML
    void onMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    public void onBus(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.shohoz.com/bus-tickets");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onTrain(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://eticket.railway.gov.bd/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onAir(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://flightexpert.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onShip(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.bdstall.com/ship-ticket/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onUber(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.uber.com/us/en/ride/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    public void onObhai(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.obhai.com/");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    @FXML
    void onTicket(ActionEvent event) {
    mainPane.setVisible(false);
    ticketPane.setVisible(true);
    }
    @FXML
    void onHotel(ActionEvent event) {
    mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        loadingimg.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.booking.com/country/bd.en-gb.html");
        wb.getLoadWorker().progressProperty().addListener((a,b,progress) -> {
                    if(progress.doubleValue()==1){
                        loadingimg.setVisible(false);
                    }
                }

        );
    }
    @FXML
    public void onBacktoTicket(ActionEvent event) {
    ticketPane.setVisible(false);
    mainPane.setVisible(true);
    }
    @FXML
    public void onBackToETicket(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource("FXML/ToolBox.fxml")));
        BorderPane borderPane = (BorderPane) mainPane.getParent().getParent();
        borderPane.setCenter(parent);
    }
}
