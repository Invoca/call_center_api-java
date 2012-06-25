package com.ringrevenue.CallCenter;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class RingRevenue_Call_CenterTest {
    
    public RingRevenue_Call_CenterTest() {
    }


    @Test
    public void testConfig() {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("CALL_CENTER_ID", "1");
        expected.put("API_VERSION", "2012-01-10");
        expected.put("API_USERNAME", "test@ringrevenue.com");
        expected.put("API_PASSWORD", "ringrevenue");
        
        RingRevenue_Call_Center.config(expected);
        
        assertEquals(expected, RingRevenue_Call_Center.get_config());
    }

}
