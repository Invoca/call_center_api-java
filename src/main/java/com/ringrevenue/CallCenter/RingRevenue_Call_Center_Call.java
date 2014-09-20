package com.invoca.CallCenter;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.*;

public class Invoca_Call_Center_Call {
    private HashMap<String,String> call_attributes;
    private int api_num;
    public HttpRequest connection;

    public Invoca_Call_Center_Call( HashMap<String,String> hm ) {
        call_attributes = new HashMap<String,String>();
        attributes(hm);
        api_num = (int) (Math.random() + 0.5);
        connection = new HttpURLConnectionWrapper();
    }    
    
    
    public void attributes( HashMap<String,String> hm ){
        for(String key : hm.keySet()){
            call_attributes.put(key, hm.get(key));
        }
    }
    
    public HashMap<String,String> get_attributes(){
        return call_attributes;
    }
    
    public HashMap<String,String> save(){
        return httpRequest("PUT");
    }
    
    private HashMap<String,String> httpRequest(String method){

        try{
            connection.openConnection(generate_api_url());
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", 
                "application/x-www-form-urlencoded");
            
            connection.setRequestProperty("Content-Language", "en-US"); 
            
            Authenticator.setDefault (new Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication (Invoca_Call_Center.API_USERNAME, Invoca_Call_Center.API_PASSWORD.toCharArray());
    }
});
            
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
                  
            connection.request(get_attributes());
         
            int status = connection.getResponseCode();
            
            if(status >= 300){
                String response_body = connection.getErrorResponseBody();
                                
                HashMap<String,String> hm = new HashMap<String,String>();
                hm.put("response_body", response_body);
                hm.put("status_code", "" + status);
                return hm;
            }
            else {
                
                //Get Response	
                String response_body = connection.getNoErrorResponseBody();

                HashMap<String,String> hm = new HashMap<String,String>();
                hm.put("response_body", response_body);
                hm.put("status_code", "" + status);
                return hm;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        
    }
    
    public int get_api_num(){
        return api_num;
    }

    public String generate_api_url(){
        return "https://api" + api_num + ".invoca.com/api/" + Invoca_Call_Center.API_VERSION + "/calls/" + Invoca_Call_Center.CALL_CENTER_ID + ".xml";

    } 
    
}
