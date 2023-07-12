package com.example.carcheck.Models;

public class Car {
    // id_user ? 
    private String Mark;
    private  String Model;
    private String Year;
    private String VIN;
    private String URL_Photo;
    private String LastRegistration;
    public Car(String mark,String model, String year,String vin,String url,String lastReg){
        this.Mark = mark;
        this.Year = year;
        this.LastRegistration = lastReg;
        this.VIN = vin;
        this.URL_Photo = url;
        this.Model = model;
    }

    public String getYear() {
        return Year;
    }

    public String getVIN() {
        return VIN;
    }

    public String getLastRegistration() {
        return LastRegistration;
    }

    public String getMark() {
        return Mark;
    }

    public String getModel() {
        return Model;
    }

    public String getURL_Photo() {
        return URL_Photo;
    }
}
