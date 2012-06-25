package com.ringrevenue.CallCenter;

import java.util.HashMap;



public interface HttpRequest
{
    public void openConnection(String url);
    public void setRequestMethod(String method);
    public void setRequestProperty(String option, String value);
    public void setUseCaches(boolean val);
    public void setDoInput(boolean val);
    public void setDoOutput(boolean val);
    public void request(HashMap<String,String> attributes);
    public int getResponseCode();
    public String getErrorResponseBody();
    public String getNoErrorResponseBody();
    public void disconnect();
    
}