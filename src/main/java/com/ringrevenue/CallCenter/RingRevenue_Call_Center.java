package com.invoca.CallCenter;

import java.util.*;

abstract class Invoca_Call_Center {
    public static String CALL_CENTER_ID;
    public static String API_VERSION;
    public static String API_USERNAME;
    public static String API_PASSWORD;
    
    
    public static void config( HashMap<String,String> hm ){
	  ArrayList<String> keys = new ArrayList<String>();
          keys.add("CALL_CENTER_ID");
          keys.add("API_VERSION");
          keys.add("API_USERNAME");
          keys.add("API_PASSWORD");

          CALL_CENTER_ID = hm.keySet().contains( "CALL_CENTER_ID" ) ? hm.get("CALL_CENTER_ID") : null;
          API_VERSION    = hm.keySet().contains( "API_VERSION" ) ? hm.get("API_VERSION") : null;
          API_USERNAME   = hm.keySet().contains( "API_USERNAME" ) ? hm.get("API_USERNAME") : null;
          API_PASSWORD   = hm.keySet().contains( "API_PASSWORD" ) ? hm.get("API_PASSWORD") : null;
          
        }
    
    public static HashMap<String,String> get_config(){
    	HashMap hm = new HashMap();
        hm.put("CALL_CENTER_ID", CALL_CENTER_ID);
        hm.put("API_VERSION", API_VERSION);
        hm.put("API_USERNAME", API_USERNAME);
        hm.put("API_PASSWORD", API_PASSWORD);
        return hm;
    }
    
    
}
