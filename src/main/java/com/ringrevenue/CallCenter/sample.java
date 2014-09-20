package com.invoca.CallCenter;

import java.util.ArrayList;
import java.util.HashMap;


public class sample {
    
    public static void main(String[] args){
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("CALL_CENTER_ID", "1");
        hm.put("API_VERSION", "2010-04-22");
        hm.put("API_USERNAME", "test@invoca.com");
        hm.put("API_PASSWORD", "testcalls");
        
        Invoca_Call_Center.config(hm);
        
        ArrayList<HashMap<String,String>> call_data = new ArrayList<HashMap<String,String>>();
        call_data.add(new HashMap<String,String>());
        call_data.get(0).put("start_time_t", "1339289018" );
        call_data.get(0).put("call_center_call_id", "91234567" );
        call_data.get(0).put("duration_in_seconds", "200" );
        call_data.get(0).put("reason_code", "S" );
        call_data.get(0).put("sale_currency", "USD" );
        call_data.get(0).put("sale_amount", "1.01" );
        
        call_data.add(new HashMap<String,String>());
        call_data.get(1).put("start_time_t", "1339721018" );
        call_data.get(1).put("call_center_call_id", "91234568" );
        call_data.get(1).put("duration_in_seconds", "200" );
        call_data.get(1).put("reason_code", "S" );
        call_data.get(1).put("sale_currency", "USD" );
        call_data.get(1).put("sale_amount", "1.12" );
        call_data.get(1).put("email_address", "john@doe.com" );
        call_data.get(1).put("sku_list[]", "DVD,cleaner" );
        call_data.get(1).put("quantity_list[]", "2,1" );
        
        call_data.add(new HashMap<String,String>());
        call_data.get(2).put("start_time_t", "1340153017" );
        call_data.get(2).put("call_center_call_id", "91234569" );
        call_data.get(2).put("duration_in_seconds", "200" );
        call_data.get(2).put("reason_code", "S" );
        call_data.get(2).put("sale_currency", "USD" );
        call_data.get(2).put("sale_amount", "2.02" );
        call_data.get(2).put("called_phone_number", "+1 8888665440" );
        call_data.get(2).put("calling_phone_number", "+1 8056801218" );

        for(HashMap<String,String> call_info : call_data){
        Invoca_Call_Center_Call call = new Invoca_Call_Center_Call(call_info);
        hm = call.save();
        
        HashMap<String,String> params = call.get_attributes();
        if(Integer.parseInt( hm.get("status_code") ) >= 200 && Integer.parseInt( hm.get("status_code") ) < 300)
            System.out.println("Success on call " + params.get("call_center_call_id") + "!\n\n");
        else
            System.out.println(hm.get("status_code") + " Error on call " + params.get("call_center_call_id") + ":\n" + hm.get("response_body") + "\n");
        }      
        

    }
    
}
