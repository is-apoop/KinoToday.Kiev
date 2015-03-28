package kino.today.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hlib on 24.03.15.
 */
//TODO extract interface from here
public class KievCinemaParser
{

  //TODO think where to put xPathes below
  private final static String xPathToSessionLink = ".session.actual";

  private final static String xPathToTitle = "div.right-column.center>h3";
  private final static String xPathToTime = "div.right-column.center>p";
  private final static String xPathToPrices = "ul#scheme-legend>li";

  Map<String, List<String>> movieNameToSessions = new HashMap<>();

  public void parse() throws IOException
  {
    //TODO put the url in properties or something
    //String url = new Crawler().getSessionsPageLink();
    String url = "http://www.kievkino.com.ua/ru/sessions/2/index.html";

    Document document = Jsoup.connect( url ).get();
    Elements elements = document.select( xPathToSessionLink );
    for( Element element : elements )
    {
      String linkToSession = element.attr( "abs:href" );
      parseSession( linkToSession );
    }
  }

  private void parseSession( String linkToSession ) throws IOException
  {
    Document document = Jsoup.connect( linkToSession ).get();

    String title = document.select( xPathToTitle ).first().text();
    String time = document.select( xPathToTime ).first().text();
    List<Element> elements = document.select( xPathToPrices );
    List<String> prices = new ArrayList<>(  );
    for( Element element : elements )
    {
      prices.add( element.text() );
    }

    put( title, time + "--- " + prices + " ---" );
  }

  private void put( String name, String time )
  {
    List<String> list = movieNameToSessions.get( name );
    if( list == null )
    {
      list = new ArrayList<>();
      movieNameToSessions.put( name, list );
    }
    list.add( time );
  }
}
