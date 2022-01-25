package com.rizkiashari.restoapp.model;

public class DataModel  {

    private int id;
    private String namaResto, openDate, picture, locationResto, addressResto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaResto() {
        return namaResto;
    }

    public void setNamaResto(String namaResto) {
        this.namaResto = namaResto;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLocationResto() {
        return locationResto;
    }

    public void setLocationResto(String locationResto) {
        this.locationResto = locationResto;
    }

    public String getAddressResto() {
        return addressResto;
    }

    public void setAddressResto(String addressResto) {
        this.addressResto = addressResto;
    }
}
