package com.invoca.CallCenter;


import java.util.ArrayList;
import java.util.HashMap;



public class HttpURLConnectionMock implements HttpRequest{
    
    public String request_method;
    public ArrayList<String> properties;
    public HashMap<String,String> options;
    public boolean set_use_caches       = false;
    public boolean connection_open      = false;
    public boolean set_do_input         = false;
    public boolean set_do_output        = false;
    public boolean sent_request         = false;
    public boolean get_error_body       = false;
    public boolean get_noerror_body     = false;
    
    public int response                 = 200; // Changes the status code and response body
    
    
    public HttpURLConnectionMock(){
        options = new HashMap<String,String>();
        properties = new ArrayList<String>();
    }
    
    @Override    
    public void openConnection(String url){
        connection_open = true;
    }
    
    @Override
    public void disconnect(){}
    
    
    public void connect(){}
    
    @Override
    public void setRequestMethod(String method){
        request_method = method;
    }
    
    @Override
    public void setRequestProperty(String option, String value){
        options.put(option, value);
    }
    
    @Override
    public void setUseCaches(boolean val){
        set_use_caches = true;
    }
    
    @Override
    public void setDoInput(boolean val){
        set_do_input = true;
    }
    
    @Override
    public void setDoOutput(boolean val){
        set_do_output = true;
    }
    
    @Override
    public int getResponseCode(){
        return response;
    }

    @Override
    public void request(HashMap<String,String> attributes){
        sent_request = true;
    }
    
    @Override
    public String getErrorResponseBody(){
        get_error_body = true;
        return "Error: " + response;
    }
    
    @Override
    public String getNoErrorResponseBody(){
        get_noerror_body = true;
        return "Success! " + response;
    }
    
    public void setResponseCode(int code){
        response = code;
    }
    
}
