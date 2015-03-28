package kino.today.parsers;

import kino.today.model.Cinema;
import org.junit.Test;

public class KievCinemaParserTest {

    @Test
    public void testParse() throws Exception {
        KievCinemaParser parser = new KievCinemaParser();
        Cinema cinema = parser.parse();
        System.out.println(cinema);
    }
}