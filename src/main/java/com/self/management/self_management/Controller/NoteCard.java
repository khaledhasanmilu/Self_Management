package com.self.management.self_management.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class NoteCard {
    @FXML
    private Text noteBody;

    @FXML
    private Label noteTitle;

    @FXML
    private Label date;
    private int noteId;

    public int getNoteId() {
        return noteId;
    }

    public Text getNoteBody() {
        return noteBody;
    }

    public Label getNoteTitle() {
        return noteTitle;
    }

    public Label getDate() {
        return date;
    }

    public void setData(String title, String body, String datefd,int noteId){
        noteBody.setText(body);
        noteTitle.setText(title);
        date.setText(date.getText()+" "+datefd);
        this.noteId=noteId;
    }
}
