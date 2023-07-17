package com.example.carcheck.Models;

public class Car {
    private int id;
    private int id_user;
    private String Mark;
    private  String Model;
    private String Year;
    private String VIN;
    private String URL_Photo;
    private String LastRegistration;
    public Car(int id,int id_user, String mark,String model, String year,String lastReg,String vin,String url){
        this.id = id;
        this.id_user = id_user;
        this.Mark = mark;
        this.Year = year;
        this.LastRegistration = lastReg;
        this.VIN = vin;
        this.URL_Photo = url;
        this.Model = model;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "ID User: " + this.id_user + "Mark: " + this.Mark + "Model: " + this.Model;
    }
}
