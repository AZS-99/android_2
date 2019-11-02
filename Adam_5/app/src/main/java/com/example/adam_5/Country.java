package com.example.adam_5;

public class Country {
    String country;
    String capital;
    String imgURL;

    public Country(String name, String capital, String imgURL) {
        this.country = name;
        this.capital = capital;
        this.imgURL = imgURL;
    }

    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public String getImgURL() {
        return imgURL;
    }
}
