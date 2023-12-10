package com.self.management.self_management.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Eticket {
    @FXML
    private WebView webView;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane ticketPane;

    @FXML
    private AnchorPane hotelPane;
    @FXML
    void onClose(ActionEvent event) {
        System.exit(0);
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
        WebEngine wb = webView.getEngine();
        wb.load("https://www.shohoz.com/bus-tickets");
    }
    public void onTrain(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://eticket.railway.gov.bd/");
    }
    public void onAir(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://flightexpert.com/");
    }
    public void onShip(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.bdstall.com/ship-ticket/");
    }
    public void onUber(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.uber.com/us/en/ride/");
    }
    public void onObhai(){
        mainPane.setVisible(false);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.obhai.com/");
    }
    @FXML
    void onTicket(ActionEvent event) {
    mainPane.setVisible(false);
    ticketPane.setVisible(true);
    }
    @FXML
    void onHotel(ActionEvent event) {
    mainPane.setVisible(false);
    hotelPane.setVisible(true);
        ticketPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.booking.com/country/bd.en-gb.html");
    }
    @FXML
    void onBacktoTicket(ActionEvent event) {
    ticketPane.setVisible(false);
    mainPane.setVisible(true);
    }
}
