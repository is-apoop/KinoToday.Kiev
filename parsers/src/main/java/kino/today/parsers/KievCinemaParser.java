package kino.today.parsers;

import kino.today.model.Cinema;
import kino.today.model.Movie;
import kino.today.model.Session;
import org.apache.logging.log4j.Logger;
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

    private final static Logger LOG = org.apache.logging.log4j.LogManager.getLogger(KievCinemaParser.class);

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
        if (elements.isEmpty()) {
            LOG.error(this.getClass().getSimpleName() + " haven't found any links to sessions. Please check the following xPath: ["
                    + xPathToSessionLink + " ] or visit " + url);
        }
        for (Element element : elements) {
            String linkToSession = element.attr("abs:href");
            Session session = parseSession(linkToSession);
            if (session != null) {
                cinema.addSession(session);
            }
        }
        return cinema;
    }

    //TODO probably we should wrap this exceptions with ours here
    private Session parseSession(String linkToSession) throws IOException, ParseException {
        Session session = new Session();
        Movie movie = new Movie();
        session.setCinema(cinema);
        session.setTicketsLink(linkToSession);

        Document document = Jsoup.connect(linkToSession).timeout(JSOUP_TIMEOUT_MILLIS).get();

        Element titleElement = document.select(xPathToTitle).first();
        if (titleElement != null) {
            //TODO normalize the title of the movie and choose movie object from the list of known films
            movie.setTitle(titleElement.text());
        } else {
            LOG.error(this.getClass().getSimpleName() + " haven't found the title of the movie. Please check the following" +
                    "xPAth: [" + xPathToTitle + "] or visit " + linkToSession);
            return null; // no sense in returning session without title of the movie
        }
        Element timeElement = document.select(xPathToTime).first();
        if (timeElement != null) {
            try {
                session.setTineStart(parseTime(timeElement.text()));
            } catch (InvalidFormatException e) {
                LOG.error(e.getMessage() + "Visit the following link for more details: " + linkToSession);
                return null;
            }
        } else {
            LOG.error(this.getClass().getSimpleName() + " haven't found the time of the session. Please check the following " +
                    "xPAth: [" + xPathToTime + "] or visit " + linkToSession);
            return null; // no sense in returning session without time of the movie
        }

        List<Element> elements = document.select(xPathToPrices);
        if (elements.isEmpty()) {
            LOG.warn(this.getClass().getSimpleName() + "Could not find price for the following session: " + linkToSession +
            ". Please check xPath: " + xPathToPrices);
        }
        List<Integer> prices = new ArrayList<>();
        for (Element element : elements) {
            try {
                prices.add(parsePrice(element.text()));
            } catch (InvalidFormatException e) {
                LOG.warn(e.getMessage() + "Visit the following link for more details: " + linkToSession);
            }
        }
        session.setMovie(movie);

        session.setPrice(getMostRelevantPrice(prices));

        return session;
    }

    //TODO keep in mind that format can change, provide some docs
    // TODO so that the implementation can be easily adjusted
    private Date parseTime(String time) throws InvalidFormatException {
        String preparedTime = prepareTimeString(time);
        SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
        try {
            return formatter.parse(preparedTime);
        } catch (ParseException e) {
            throw new InvalidFormatException("The following string: " + time + " doesn't correspond the" +
                    "following format: " + TIME_FORMAT + ". Format may have been changed. ", e);
        }
    }

    private String prepareTimeString(String time) throws InvalidFormatException {
        Scanner scanner = new Scanner(time);
        String matchedText = scanner.findInLine(TIME_REGEXP);
        if (matchedText == null) {
            throw new InvalidFormatException("Can't find the following substring: " + TIME_REGEXP + " in " + time + "" +
                    ". Format may have been changed. ");
        }
        MatchResult matchResult = scanner.match();
        String stringNameOfMonth = matchResult.group(1);
        scanner.close();
        String monthInNumberFormat = monthRepresentation.get(stringNameOfMonth);
        if (monthInNumberFormat == null) {
            throw new InvalidFormatException("Can't find the number format for the following month: " + stringNameOfMonth
                    + ". Format may have been changed. ");
        }
        return time.replace(stringNameOfMonth, monthInNumberFormat);
    }

    private Integer parsePrice(String priceInString) throws InvalidFormatException {
        Scanner scanner = new Scanner(priceInString);
        String matchString = scanner.findInLine(PRICE_REGEXP);
        if (matchString == null) {
            throw new InvalidFormatException("The following string: " + priceInString + " doesn't correspond the" +
                    "following format: " + PRICE_REGEXP + ". Format may have been changed. ");
        }
        MatchResult matchResult = scanner.match();
        try {
            return Integer.parseInt(matchResult.group(1));
        } finally {
            scanner.close();
        }
    }

    private Integer getMostRelevantPrice(List<Integer> prices) {
        //TODO temporary solution
        return !prices.isEmpty() ? prices.get(0) : null;
    }
}
