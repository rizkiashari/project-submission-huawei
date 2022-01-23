package com.rizkiashari.restoapp.model;

public class RvModel {
    private int image;
    private String location;
    private String makanan;

    public RvModel(int image, String location, String makanan) {
        this.image = image;
        this.location = location;
        this.makanan = makanan;
    }

    public int getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public String getMakanan() {
        return makanan;
    }
}
