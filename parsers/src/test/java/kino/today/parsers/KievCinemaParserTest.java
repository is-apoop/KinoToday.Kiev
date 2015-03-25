package kino.today.parsers;

import org.junit.Test;

public class KievCinemaParserTest {

    @Test
    public void testParse() throws Exception {
        KievCinemaParser parser = new KievCinemaParser();
        parser.parse();
        System.out.println(parser.movieNameToSessions);
    }
}