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

    public APIController(){
        this.URL = "https://baza-gai.com.ua/";
    }
    public void searchByNumber(String number){
        String _url = this.URL;
        _url += "nomer/"+number;
        try{
            //Create URL
            URL url = new URL(_url);

            //crete connection
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("X-Api-Key","91c95e92e39fe354d161f556ba2b9e39");
//            connection.setRequestProperty("Accept","application/json");
//            connection.connect();
//
//            int responseCod = connection.getResponseCode();
//            if(responseCod != 200){
//                throw new RuntimeException("HttpResponseCode: " + responseCod);
//            }else {
//                StringBuilder stringBuilder = new StringBuilder();
//                Scanner scanner = new Scanner(url.openStream());
//
//                while (scanner.hasNext()){
//                    stringBuilder.append(scanner.nextLine());
//                }
//                scanner.close();
//                JSONParser parser = new JSONParser();
//                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(stringBuilder));
//                System.out.println(dataObject);
//
//                JSONObject countryData = (JSONObject) dataObject.get(0);
//            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(_url))
                    .headers("X-Api-Key","91c95e92e39fe354d161f556ba2b9e39","Accept","application/json")
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Object obj = new JSONParser().parse(response.body());
            JSONObject jo = (JSONObject) obj;
            System.out.println(jo.get("photo_url"));
            System.out.println(jo.get("vendor"));
            System.out.println(jo.get("model"));
            System.out.println(jo.get("model_year"));
            System.out.println(jo.get("vin"));
            //
            //  oj1 -> obj2 ["registered_at"] = 13.06.2023
            //
            JSONArray array = (JSONArray) jo.get("operations");
            JSONObject ob2 = (JSONObject) array.get(0);
            System.out.println(ob2.get("registered_at"));
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

}
