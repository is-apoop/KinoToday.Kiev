package kino.today.model;

import java.util.List;

/**
 * Created by hlib on 25.03.15.
 */
public class Cinema {
    private String name;
    private double rating;
    private List<Session> sessions;

    public Cinema() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
