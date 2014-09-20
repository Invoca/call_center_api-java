package com.invoca.CallCenter;

import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;


public class Invoca_Call_Center_CallTest {
    
    
    public Invoca_Call_Center_CallTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("CALL_CENTER_ID", "1");
        hm.put("API_VERSION", "2012-01-10");
        hm.put("API_USERNAME", "test@invoca.com");
        hm.put("API_PASSWORD", "invoca");
        
        Invoca_Call_Center.config(hm);
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testAttributes() {
        Invoca_Call_Center_Call call = new Invoca_Call_Center_Call( new HashMap<String,String>() );
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("start_time_t", "13401824" );
        expected.put("duration_in_seconds", "200" );
        expected.put("reason_code", "S" );
        expected.put("opt_in_SMS", "1" );
        
        call.attributes(expected);
        assertEquals(expected, call.get_attributes());
    }

    
    @Test
    public void testSaveShouldRunWithNoErrorOn200s() {
        Invoca_Call_Center_Call call = new Invoca_Call_Center_Call( new HashMap<String,String>() );
        try{
            call.connection =  new HttpURLConnectionMock();
        } catch (Exception e){
            e.printStackTrace();
        }
        ((HttpURLConnectionMock)call.connection).setResponseCode(200);
        call.save();
        
        assertEquals("PUT", ((HttpURLConnectionMock)call.connection).request_method);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).set_use_caches);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).connection_open);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).set_do_input);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).set_do_output);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).sent_request);
        assertEquals(false, ((HttpURLConnectionMock)call.connection).get_error_body);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).get_noerror_body);
        
        HashMap<String,String> expected_options = new HashMap<String,String>();
        expected_options.put("Content-Type", "application/x-www-form-urlencoded");
        expected_options.put("Content-Language", "en-US");
        
        assertEquals(expected_options, ((HttpURLConnectionMock)call.connection).options);
    }
    
    @Test
    public void testSaveShouldRunWithErrorOnNon200s() {
        Invoca_Call_Center_Call call = new Invoca_Call_Center_Call( new HashMap<String,String>() );
        try{
            call.connection =  new HttpURLConnectionMock();
        } catch (Exception e){
            e.printStackTrace();
        }
        ((HttpURLConnectionMock)call.connection).setResponseCode(404);
        call.save();
        
        assertEquals("PUT", ((HttpURLConnectionMock)call.connection).request_method);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).set_use_caches);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).connection_open);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).set_do_input);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).set_do_output);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).sent_request);
        assertEquals(true, ((HttpURLConnectionMock)call.connection).get_error_body);
        assertEquals(false, ((HttpURLConnectionMock)call.connection).get_noerror_body);
        
        HashMap<String,String> expected_options = new HashMap<String,String>();
        expected_options.put("Content-Type", "application/x-www-form-urlencoded");
        expected_options.put("Content-Language", "en-US");
        
        assertEquals(expected_options, ((HttpURLConnectionMock)call.connection).options);
    }

    @Test
    public void testGenerateApiUrl(){
        Invoca_Call_Center_Call call = new Invoca_Call_Center_Call( new HashMap<String,String>() );
        String expected_url = "https://api" + call.get_api_num() + ".invoca.com/api/" + Invoca_Call_Center.API_VERSION + "/calls/" + Invoca_Call_Center.CALL_CENTER_ID + ".xml";
        assertEquals(expected_url, call.generate_api_url());
    }
    
    @Test
    public void testGenerateUrlEncode(){
        Invoca_Call_Center_Call call = new Invoca_Call_Center_Call( new HashMap<String,String>() );
        String expected_encode = "duration_in_seconds=200&opt_in_SMS=1&reason_code=S&sku_list[]=one&sku_list[]=two&start_time_t=13401824";
        
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("start_time_t", "13401824" );
        expected.put("duration_in_seconds", "200" );
        expected.put("reason_code", "S" );
        expected.put("opt_in_SMS", "1" );
        expected.put("sku_list[]", "one,two" );
        
        assertEquals(expected_encode, ((HttpURLConnectionWrapper)call.connection).generate_url_encode(expected));
    }
}
