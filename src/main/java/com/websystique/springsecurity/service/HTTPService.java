package com.websystique.springsecurity.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.websystique.springsecurity.dto.VKObjectDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HTTPService {
    protected  Gson gson = new Gson();
    private  Type responseType = new TypeToken<ArrayList<VKObjectDTO>>(){}.getType();
    
    protected String doPOSTQuery(String baseQuery, Map<String,String> params) throws MalformedURLException, UnsupportedEncodingException, ProtocolException, IOException{
        URL url = new URL(baseQuery);
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
        return readResponse(conn);
    }
    
    private String readResponse(HttpURLConnection connection) throws IOException{
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
    
     //общий вид GET-запроса к вк-апи
    protected  List<VKObjectDTO> doGETQuery(String query) throws MalformedURLException, ProtocolException, IOException{
        System.out.println("ЗАПРОС:" + query);
        URL obj = new URL(query);
        HttpURLConnection connection = (HttpURLConnection)obj.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        String response = "";      
        if (responseCode == HttpURLConnection.HTTP_OK){
            response = readResponse(connection);          
        }
        int startIndex = response.lastIndexOf("items") + "items".length() + 1;
        response = response.substring(startIndex + 1, response.length() - 2);
        return gson.fromJson(response, responseType);
    }
}
