package com.example.jamru.Model;

public class ModelBooking {

    private String room;
    private String name;
    private String keperluan;
    private String contact;
    private String date;
    private String startTime;
    private String endTime;

    private ModelBooking () {

    }

    public ModelBooking(String room, String name,String keperluan,String contact,String date,String startTime,String endTime) {
        this.room = room;
        this.name= name;
        this.keperluan = keperluan;
        this.contact = contact;
        this.date =date;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setBookerName(String name) {
        this.name = name;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public String getBookerContact() {
        return contact;
    }

    public void setBookerContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
