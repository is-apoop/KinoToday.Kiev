package kino.today.parsers;

import kino.today.model.Cinema;
import kino.today.model.Movie;
import kino.today.model.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.MatchResult;

/**
 * Created by hlib on 24.03.15.
 */
//TODO extract interface from here
public class KievCinemaParser {

    private final int JSOUP_TIMEOUT_MILLIS = 300 * 1000;

    private final static String TIME_FORMAT = "dd MM yyyy, HH:mm";
    private final static String TIME_REGEXP = "\\b\\d?\\d (.*) \\d{4},";
    private final static String PRICE_REGEXP = "(\\d+) грн";

    Map<String, String> monthRepresentation = new HashMap<String, String>() {{
        put("января", "01");
        put("февраля", "02");
        put("марта", "03");
        put("апреля", "04");
        put("мая", "05");
        put("июня", "06");
        put("июля", "07");
        put("августа", "08");
        put("сентября", "09");
        put("октября", "10");
        put("ноября", "11");
        put("декабря", "12");
    }};


    //TODO think where to put xPathes below
    private final static String xPathToSessionLink = ".session.actual";

    private final static String xPathToTitle = "div.right-column.center>h3";
    private final static String xPathToTime = "div.right-column.center>p";
    private final static String xPathToPrices = "ul#scheme-legend>li";

    private Cinema cinema;

    public KievCinemaParser() {
        this.cinema = new Cinema();
        cinema.setName("Kiev");
    }

    public Cinema parse() throws IOException, ParseException {
        //TODO put the url in properties or something
        //String url = new Crawler().getSessionsPageLink();
        String url = "http://www.kievkino.com.ua/ru/sessions/2/index.html";

        Document document = Jsoup.connect(url).timeout(JSOUP_TIMEOUT_MILLIS).get();

        Elements elements = document.select(xPathToSessionLink);
        int counter = 0;
        for (Element element : elements) {
            String linkToSession = element.attr("abs:href");
            Session session = parseSession(linkToSession);
            cinema.addSession(session);
        }
        return cinema;
    }

    //TODO probably we should wrap this exceptions with ours here
    private Session parseSession(String linkToSession) throws IOException, ParseException {
        Session session = new Session();
        session.setCinema(cinema);
        session.setTicketsLink(linkToSession);

        Document document = Jsoup.connect(linkToSession).timeout(JSOUP_TIMEOUT_MILLIS).get();

        String title = document.select(xPathToTitle).first().text();
        String time = document.select(xPathToTime).first().text();
        List<Element> elements = document.select(xPathToPrices);
        List<Integer> prices = new ArrayList<>();
        for (Element element : elements) {
            prices.add(parsePrice(element.text()));
        }

        Movie movie = new Movie();
        //TODO normalize the title of the movie and choose movie object from the list of known films
        movie.setTitle(title);
        session.setMovie(movie);
        session.setTineStart(parseTime(time));
        session.setPrice(getMostRelevantPrice(prices));

        return session;
    }

    //TODO keep in mind that format can change, provide some docs
    // TODO so that the implementation can be easily adjusted
    private Date parseTime(String time) throws ParseException {
        String preparedTime = prepareTimeString(time);
        SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
        return formatter.parse(preparedTime);
    }

    private String prepareTimeString(String time) {
        Scanner scanner = new Scanner(time);
        scanner.findInLine(TIME_REGEXP);
        MatchResult matchResult = scanner.match();
        String stringNameOfMonth = matchResult.group(1);
        scanner.close();
        return time.replace(stringNameOfMonth, monthRepresentation.get(stringNameOfMonth));
    }

    private Integer parsePrice(String text) {
        Scanner scanner = new Scanner(text);
        scanner.findInLine(PRICE_REGEXP);
        MatchResult matchResult = scanner.match();
        try {
            return Integer.parseInt(matchResult.group(1));
        } finally {
            scanner.close();
        }
    }

    private int getMostRelevantPrice(List<Integer> prices) {
        //TODO temporary solution
        return !prices.isEmpty() ? prices.get(0) : null;
    }
}
