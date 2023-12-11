package com.self.management.self_management.Model;

public class NoteModel {
    public String noteTitle;
    public String nodaBody;
    public String date;
    public int noteId;

    public NoteModel(String noteTitle, String nodaBody, String date,int noteId) {
        this.noteTitle = noteTitle;
        this.nodaBody = nodaBody;
        this.date = date;
        this.noteId=noteId;
    }
    public NoteModel(){

    }
}
