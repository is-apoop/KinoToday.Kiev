package kino.today.parsers;

import junit.framework.TestCase;

public class KinoafishaParserTest extends TestCase {

    public void testUpdateCinemaIds() throws Exception {
        new KinoafishaParser().updateCinemaIds();
    }
}