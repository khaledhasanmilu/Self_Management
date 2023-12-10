package com.self.management.self_management.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Shooping {
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
    public void onDaraz(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.daraz.com.bd/");
    }
    public void onAmazon(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.amazon.com/");
    }
    public void onRokomari(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.rokomari.com/");
    }
    public void onBikroy(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://bikroy.com/");
    }
    public void onChalDal(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://chaldal.com/");
    }
    public void onAjkerDeal(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.ajkerdeal.com/");
    }
    public void onPriyoShop(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://priyoshopretail.com/");
    }
    public void onOthoba(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.othoba.com/");
    }
    public void onAlibaba(){
        mainPane.setVisible(false);
        webView.setVisible(true);
        WebEngine wb = webView.getEngine();
        wb.load("https://www.alibaba.com/");
    }
}
