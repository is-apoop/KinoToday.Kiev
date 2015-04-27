package kino.today.parsers;

import com.backendless.Backendless;
import kino.today.model.Cinema;
import org.junit.Test;

public class KievCinemaParserTest {

    @Test
    public void testParse() throws Exception {
        KievCinemaParser parser = new KievCinemaParser();
        Cinema cinema = parser.parse();
        Backendless.initApp( "C0CAF713-73D0-475A-FFED-43E5641FDD00", "007C7D0F-28CB-EE69-FFBC-120E8EB75E00", "v1" );
        Backendless.Data.of( Cinema.class ).save( cinema );
        System.out.println(cinema);
    }
}