package kino.today.model;

import java.util.List;

/**
 * Created by hlib on 25.03.15.
 */
public class Movie {
    private String description;
    private int duration;
    private String genre;
    private String imdbLink;
    private String kinopoiskLink;
    private double rating;
    private List<Session> sessions;

    public Movie() {
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public String getKinopoiskLink() {
        return kinopoiskLink;
    }

    public void setKinopoiskLink(String kinopoiskLink) {
        this.kinopoiskLink = kinopoiskLink;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
