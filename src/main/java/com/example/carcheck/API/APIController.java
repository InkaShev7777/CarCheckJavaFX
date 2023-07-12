package com.example.carcheck.API;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.http.HttpRequest;
import com.google.gson.Gson;


public class APIController {
    private String URL;
    private String Marck;
    private String URLImage;
    private String VIN;
    private String Year;
    private String LastRegistrate;
    private Boolean Error;

    public APIController(){
        this.URL = "https://baza-gai.com.ua/";
        this.Marck = "";
        this.URLImage = "";
        this.LastRegistrate = "";
        this.VIN = "";
        this.Year = "";
        this.Error = false;
    }
    public void searchByNumber(String number){
        String _url = this.URL;
        _url += "nomer/"+number;
        try{
            URL url = new URL(_url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(_url))
                    .headers("X-Api-Key","91c95e92e39fe354d161f556ba2b9e39","Accept","application/json")
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Object obj = new JSONParser().parse(response.body());
            JSONObject jo = (JSONObject) obj;
            if(jo.containsKey("error"))
            {
                this.Error = true;
            }else {
                this.URLImage = jo.get("photo_url").toString();
                this.Marck = jo.get("vendor").toString() + " " + jo.get("model").toString();
                this.Year = jo.get("model_year").toString();
                this.VIN = jo.get("vin").toString();
                JSONArray array = (JSONArray) jo.get("operations");
                JSONObject ob2 = (JSONObject) array.get(0);
                this.LastRegistrate = ob2.get("registered_at").toString();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
    public void searchByVIN(String VIN){
        String _url = this.URL;
        _url += "vin/"+VIN;

        try{
            URL url = new URL(_url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(_url))
                    .headers("X-Api-Key","91c95e92e39fe354d161f556ba2b9e39","Accept","application/json")
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Object obj = new JSONParser().parse(response.body());
            JSONObject jo = (JSONObject) obj;
            if(jo.containsKey("error"))
            {
                this.Error = true;
            }else {
                this.URLImage = jo.get("photo_url").toString();
                this.Marck = jo.get("vendor").toString() + " " + jo.get("model").toString();
                this.Year = jo.get("model_year").toString();
                this.VIN = jo.get("vin").toString();

                JSONArray array = (JSONArray) jo.get("operations");
                JSONObject ob2 = (JSONObject) array.get(0);
                this.LastRegistrate = ob2.get("registered_at").toString();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String getMarck(){
        return  this.Marck;
    }
    public String getURLImage() {
        return URLImage;
    }
    public String getLastRegistrate() {
        return LastRegistrate;
    }
    public String getVIN() {
        return VIN;
    }
    public String getYear() {
        return Year;
    }
    public Boolean getError() {
        return Error;
    }
}
