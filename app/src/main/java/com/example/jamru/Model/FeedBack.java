package com.example.jamru.Model;

public class FeedBack {

    private String nama;
    private String email;
    private String tipefeedback;
    private String details;

    public FeedBack(String nama, String email, String tipefeedback, String details) {
        this.nama = nama;
        this.email = email;
        this.tipefeedback = tipefeedback;
        this.details = details;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipefeedback() {
        return tipefeedback;
    }

    public void setTipefeedback(String tipefeedback) {
        this.tipefeedback = tipefeedback;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
