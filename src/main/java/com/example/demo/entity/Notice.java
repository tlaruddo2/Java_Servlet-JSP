package com.example.demo.entity;

public class Notice {
    private int id;
    private String title;
    private String writerID;
    private String date;
    private String hit;
    private String files;
    private String content;

    public Notice(){
    }

    public Notice(int id, String title, String writerID, String date, String hit, String files, String content) {
        this.id = id;
        this.title = title;
        this.writerID = writerID;
        this.date = date;
        this.hit = hit;
        this.files = files;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterID() {
        return writerID;
    }

    public void setWriterID(String writerID) {
        this.writerID = writerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writerID='" + writerID + '\'' +
                ", date='" + date + '\'' +
                ", hit='" + hit + '\'' +
                ", files='" + files + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
