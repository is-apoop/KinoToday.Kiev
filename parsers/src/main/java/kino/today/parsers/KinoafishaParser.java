package kino.today.parsers;

import kino.today.CinemaRegistry;
import kino.today.MovieRegistry;
import kino.today.model.Cinema;
import kino.today.model.Session;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by hlib on 23.04.15.
 */
public class KinoafishaParser {
    private final static String TIME_FORMAT = "HH:mm";

    private static final String ALL_CINEMAS_URL = "http://kinoafisha.ua/ajax/kinoteatrs_load";

    private static final String KIEV = "kiev";
    private static final String ALL_SESSIONS_URL = "http://kinoafisha.ua/ajax/kinoafisha_load";

    private Map<String, String> cinemaNameToKinoafishaNameMap = new HashMap<String, String>() {{
            put("Kiev", "Киев");
    }};

    private Map<String, String> cinemaNameToKinoafishaIdMap = new HashMap<>();

    public void updateCinemaIds() throws CinemaUpdateFailedException {
        try {
            String json = sendPostRequest(
                    ALL_CINEMAS_URL,
                    new HashMap<String, String>() {{
                        put("city", KIEV);
                    }
                    });
        } catch (IOException e) {
            throw new CinemaUpdateFailedException(e);
        }
    }

    private String sendPostRequest(String url, Map<String, String> params) throws IOException {
        CloseableHttpResponse response2;
        CloseableHttpClient httpСlient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
        response2 = httpСlient.execute(httpPost);

        try {
            HttpEntity entity2 = response2.getEntity();
            StringWriter stringWriter = new StringWriter();
            IOUtils.copy(entity2.getContent(), stringWriter, "UTF-8");
            String theString = stringWriter.toString();
            EntityUtils.consume(entity2);
            return theString;
        } finally {
            response2.close();
        }
    }

    //TODO refactor
    String getDateInKinoafishaFormat(Date date) {
        Date may_01_2015_date;
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
            String inputString1 = "01-05-2015";
            may_01_2015_date = myFormat.parse(inputString1);
        } catch (ParseException e) {
            throw new AssertionError(e);
        }
        long diff = date.getTime() - may_01_2015_date.getTime();
        long dayDifference = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        long may_01_2015 = 2952720;
        long difBetweenDays = 246060;

        return Long.toString(may_01_2015 + difBetweenDays * dayDifference);
    }

    public List<Session> parseCinema(final String cinemaName, final Date date) throws IOException, InvalidFormatException {
        String json = sendPostRequest(ALL_SESSIONS_URL, new HashMap<String, String>(){{
            put("city", KIEV);
            put("date", getDateInKinoafishaFormat(date));
            put("kinoteatr", cinemaNameToKinoafishaIdMap.get(cinemaName));
        }});

        return parseAllSessionsJson(json, CinemaRegistry.INSTANCE.getCinemaByName(cinemaName));
    }

    private List<Session> parseAllSessionsJson(String response, Cinema cinema) throws InvalidFormatException {
        final String RESULT_KEY = "result";
        JSONObject jsonResponse = new JSONObject(response);
        List<Session> sessions = new ArrayList<>();
        try {
            JSONArray jsonResults = jsonResponse.getJSONArray(RESULT_KEY);
            for (int i = 0; i < jsonResults.length(); i++) {
                JSONObject jsonResult = (JSONObject) jsonResults.get(i);
                String titleInUnicodeDigits = jsonResult.getString("name");
                String title = StringEscapeUtils.unescapeJava(titleInUnicodeDigits);
                JSONArray jsonSessions = jsonResult.getJSONArray("sessions");
                for (int j = 0; j < jsonSessions.length(); j++) {
                    List<Date> sessionTimes = parseSessionTimes((JSONObject) jsonSessions.get(j));
                    for (Date sessionTime : sessionTimes) {
                        Session session = new Session();
                        session.setMovie(MovieRegistry.INSTANCE.getMovieByTitle(title));
                        session.setCinema(cinema);
                        session.setTimeStart(sessionTime);
                        sessions.add(session);
                    }
                }
            }
        } catch (JSONException e) {
            throw new InvalidFormatException("Check if format of response have changed.", e);
        }
        return sessions;
    }

    private List<Date> parseSessionTimes(JSONObject jsonSession) throws InvalidFormatException {
        String sessionTimesInHtml = jsonSession.getString("sessions");
        return parseSessionTimeFromHtml(sessionTimesInHtml);
    }

    private List<Date> parseSessionTimeFromHtml(String sessionTimesInHtml) throws InvalidFormatException {
        List<Date> sessionTimes = new ArrayList<>();
        Document html = Jsoup.parse(sessionTimesInHtml);
        Elements aElement = html.getElementsByTag("span");
        for (Element element : aElement) {
            try {
                Date date = new SimpleDateFormat(TIME_FORMAT).parse(element.text());
                sessionTimes.add(date);
            } catch (ParseException e) {
                throw new InvalidFormatException("TODO write good message here", e);
            }
        }
        return sessionTimes;
    }

    /* Setters */

    public void setCinemaNameToKinoafishaIdMap(Map<String, String> cinemaNameToKinoafishaIdMap) {
        this.cinemaNameToKinoafishaIdMap = cinemaNameToKinoafishaIdMap;
    }
}
