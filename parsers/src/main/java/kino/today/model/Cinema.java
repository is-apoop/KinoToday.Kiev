package kino.today.model;

import com.backendless.geo.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private Double rating;
    private String name;
    private GeoPoint location;
    private List<Session> sessions = new ArrayList<>();

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public void addSession(Session session) {
        sessions.add( session );
    }

    public List<Session> getSessions()
    {
        return sessions;
    }

    public void setSessions( List<Session> sessions )
    {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", sessions=" + sessions +
                '}';
    }
}