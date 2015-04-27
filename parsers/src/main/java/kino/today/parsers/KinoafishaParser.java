package kino.today.parsers;

import kino.today.model.Cinema;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hlib on 23.04.15.
 */
public class KinoafishaParser {

    private static final String ALL_CINEMAS_URL = "http://kinoafisha.ua/ajax/kinoteatrs_load";
    private static final String KIEV = "kiev";
    //private static final String ALL_CINEMAS_URL = "http://kinoafisha.ua/ajax/kinoteatrs_load";

    private Map<String, String> cinemaNaneToKinoafishaNameMap = new HashMap<String, String>() {{
            put("Kiev", "Киев");
    }};

    private Map<String, String> cinemaNaneToKinoafishaIdMap = new HashMap<>();

    public void updateCinemaIds() { //throws CinemaUpdateFailedException {
        String json = sendCinemaIdsRequest();
    }

    private String sendCinemaIdsRequest() { //throws CinemaUpdateFailedException {
        CloseableHttpResponse response2 = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String theString = null;
        try {
            HttpPost httpPost = new HttpPost(ALL_CINEMAS_URL);
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("city", KIEV));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
            response2 = httpclient.execute(httpPost);

            try {
                HttpEntity entity2 = response2.getEntity();
                StringWriter stringWriter = new StringWriter();
                IOUtils.copy(entity2.getContent(), stringWriter, "UTF-8");
                theString = stringWriter.toString();
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } catch (IOException e) {
            //throw new CinemaUpdateFailedException(e);
        }
        return theString;
    }
}
