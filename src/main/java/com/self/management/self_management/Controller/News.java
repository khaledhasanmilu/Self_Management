package com.self.management.self_management.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.web.*;

public class News {
    @FXML
    private WebView webView;
    @FXML
    private AnchorPane mainPane;
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
        WebEngine wb = webView.getEngine();
        wb.load("https://www.prothomalo.com/");
    }
    public void onDailyStar(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.thedailystar.net/");
    }
    public void onJugantor(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.jugantor.com/");
    }
    public void onBonikBatra(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://bonikbarta.net/");
    }
    public void onBdProtidin(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.ebdpratidin.com/");
    }
    public void onIttefaq(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.ittefaq.com.bd/");
    }
    public void onSomokal(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://samakal.com/");
    }
    public void onNoyaDiganta(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.dailynayadiganta.com/");
    }
    public void onJanakanta(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.dailyjanakantha.com/");
    }

}
