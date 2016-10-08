package photos.brooklyn.services;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import photos.brooklyn.images.model.Activity;
import photos.brooklyn.images.model.MerrillResponse;

@Ignore
public class TestActivityParser {
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testParse() throws Exception{
//        mapper.configure(Feature.UNWRAP_ROOT_VALUE, true);
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
        MerrillResponse resp = mapper.readValue(new File("/Users/gzhong/Documents/financial/merrill/arc-response-2014 Apr 12 12-10-53.json"), MerrillResponse.class);
        List<Activity> acts = resp.getActivities();
        assertEquals(639,acts.size());
        Activity act1 = acts.get(0);
        assertEquals(8,act1.getQuantity());
        assertEquals(true,act1.isQuantitySpecified());
        assertEquals("27Z18855",act1.getAccount());
        assertEquals("04/08/14",new SimpleDateFormat("MM/dd/yy").format(act1.getSettlementDate()));
        assertEquals("Stock Dividend\u003cBR\u003eGOOGLE INC SHS CL C HOLDING 8.0000 ...",act1.getDescription());
    }

}
