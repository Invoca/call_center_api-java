package com.ringrevenue.CallCenter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.String;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;



public class HttpURLConnectionWrapper implements HttpRequest{
    private HttpURLConnection conn;
    
    @Override
    public void openConnection(String url){
        URL u;
        try{
            u = new URL(url);
            conn = (HttpURLConnection)u.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void setRequestMethod(String method){
        try{
            conn.setRequestMethod(method);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void setRequestProperty(String option, String value){
        conn.setRequestProperty(option, value);
    }
    
    @Override
    public void setUseCaches(boolean val){
        conn.setUseCaches(val);
    }
    
    @Override
    public void setDoInput(boolean val){
        conn.setDoInput(val);
    }
    
    @Override
    public void setDoOutput(boolean val){
        conn.setDoOutput(val);
    }
    
    @Override
    public void request(HashMap<String,String> attributes){
        try{
            DataOutputStream wr = new DataOutputStream (
                                conn.getOutputStream ());

            byte[] bytes = generate_url_encode(attributes).getBytes("UTF-8");
            wr.write(bytes);

            wr.flush();
            wr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public String generate_url_encode(HashMap<String,String> attributes){
        String attr = "";
        String[] keys = (String []) attributes.keySet().toArray(new String[0]);
        Arrays.sort(keys);
                   
        for(String key : keys){
            
            //sku_list and quantity_list is comma separated
            String[] vals = attributes.get(key).split(",");
            for(int i = 0; i < vals.length; i++){
                try{
                    attr += "&" + key + "=" + URLEncoder.encode(vals[i], "UTF-8");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        
        } 
           
       return attr.substring(1);
    }
    
    @Override
    public int getResponseCode(){
        try{
            return conn.getResponseCode();
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public String getErrorResponseBody(){
        try{
            InputStream is = conn.getErrorStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public String getNoErrorResponseBody(){
        try{
            InputStream is = conn.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void disconnect(){
        conn.disconnect();
    }
}