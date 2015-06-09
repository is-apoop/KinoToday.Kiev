package kino.today.parsers;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class KinoafishaParserTest extends TestCase {

    public void testUpdateCinemaIds() throws Exception {
        new KinoafishaParser().updateCinemaIds();
    }

    public void testParseCinema() throws Exception {
        KinoafishaParser kinoafishaParser = new KinoafishaParser();
        kinoafishaParser.setCinemaNameToKinoafishaIdMap(new HashMap<String, String>(){{
            put("Kiev", "21");
        }});
        System.out.println(kinoafishaParser.parseCinema("Kiev", new Date(new Date().getTime() + 1000 * 3600)));
    }

    public void testGetDateInKinoafishaFormat() {
        String actual = new KinoafishaParser().getDateInKinoafishaFormat(new Date());
        assertEquals(Long.toString(2952720 + 246060 * 38), actual);
    }
}