package com.invoca.CallCenter;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class Invoca_Call_CenterTest {
    
    public Invoca_Call_CenterTest() {
    }


    @Test
    public void testConfig() {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("CALL_CENTER_ID", "1");
        expected.put("API_VERSION", "2012-01-10");
        expected.put("API_USERNAME", "test@invoca.com");
        expected.put("API_PASSWORD", "invoca");
        
        Invoca_Call_Center.config(expected);
        
        assertEquals(expected, Invoca_Call_Center.get_config());
    }

}
