package com.websystique.springsecurity.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


public class VKApiService {
        
    private static final String ACCESS_TOKEN = 
    //"b16864fec478b4587708a4bcc169e95e359155efb07fc70ec13c118a8b306f295c08ce6f8773a0a56126a";
    
    "7945a54608c3f8cac74c186ba4cae39f2178f94138020ace95e186f89d99847c7ada0d1483e679d5feb0b";
           
    public static boolean sendMessage(String userId, String message) throws IOException
    { 
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
        String response = sb.toString();//ответ от сервера
        //состояние отправки
        return (!response.contains("error"));
    }    
}
