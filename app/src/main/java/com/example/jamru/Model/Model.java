package com.example.jamru.Model;

public class Model {

    private String name;
    private String alamat;
    private String deskripsi;
    private String pricehour;
    private String contact;

    private Model() {

    }

    public Model(String name, String alamat, String deskripsi, String pricehour, String contact) {
        this.name= name;
        this.alamat = alamat;
        this.deskripsi =deskripsi;
        this.pricehour =pricehour;
        this.contact = contact;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    public String getPricehour() {
        return pricehour;
    }
    public void setPricehour(String pricehour) {
        this.pricehour = pricehour;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

