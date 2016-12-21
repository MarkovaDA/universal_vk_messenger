package com.websystique.springsecurity.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.websystique.springsecurity.dto.VKObjectDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;
import java.net.ProtocolException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class VKApiService {
        
    private  final String ACCESS_TOKEN =     
    "837f56d7456970d26a5995ba89f14593a0b5de5576633dd64d09e473c12130ddc4a8f503afac1a9e35f58";
    //срок действия этого ключа истекает через сутки - подумать,как этого избежать

    private  Gson gson = new Gson();
    private  Type responseType = new TypeToken<ArrayList<VKObjectDTO>>(){}.getType();
           
    public  boolean sendMessage(String userId, String message) throws IOException, MalformedURLException, MalformedURLException, IOException, UnsupportedEncodingException{ 
        String query = "https://api.vk.com/method/messages.send";
        URL url = new URL(query);
        Map<String,String> params = new LinkedHashMap<>();
        params.put("user_ids", userId);
        params.put("message", message);
        params.put("access_token", ACCESS_TOKEN);
        StringBuilder postData = new StringBuilder();
        for(Map.Entry<String, String> param: params.entrySet())
        {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8")); 
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes); 
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
        sb.append((char)c);
        //состояние отправки
        return (!sb.toString().contains("error"));
    }    
    //параметр count настроить
    public  List<VKObjectDTO> getCities() throws MalformedURLException, IOException{
       return sendRequests(getQueryForCities());
    }
    public  String readResponseFromServer(HttpURLConnection connection) throws IOException{
        BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    
    public List<VKObjectDTO> getUniversities(int cityId) throws MalformedURLException, IOException{
        return sendRequests(getQueryForUniversities(cityId));
    }    
    public  List<VKObjectDTO> getFaculties(int universityId) throws MalformedURLException, ProtocolException, IOException{
       return sendRequests(getQueryForFaculties(universityId));
    }   
    private  String getQueryForFaculties(int universityId){
        String queryFormat = "https://api.vk.com/method/database.getFaculties?university_id=%d&v=5.60&access_token=%s";        
        return String.format(queryFormat, universityId, ACCESS_TOKEN);
    }   
    private  String getQueryForUniversities(int cityId){
        String queryFormat = "https://api.vk.com/method/database.getUniversities?city_id=%d&v=5.60&access_token=%s";        
        return String.format(queryFormat, cityId, ACCESS_TOKEN);
    }  
    private  String getQueryForCities(){
        String queryFormat = "https://api.vk.com/method/database.getCities?country_id=1&v=5.60&access_token=%s";
        return String.format(queryFormat, ACCESS_TOKEN);
    }  
    //общий вид запроса 
    private  List<VKObjectDTO> sendRequests(String query) throws MalformedURLException, ProtocolException, IOException{
        URL obj = new URL(query);
        HttpURLConnection connection = (HttpURLConnection)obj.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        String response = "";      
        if (responseCode == HttpURLConnection.HTTP_OK){
            response = readResponseFromServer(connection);          
        }
        int startIndex = response.lastIndexOf("items") + "items".length() + 1;
        response = response.substring(startIndex + 1, response.length() - 2);
        return gson.fromJson(response, responseType);
    }
        
}
