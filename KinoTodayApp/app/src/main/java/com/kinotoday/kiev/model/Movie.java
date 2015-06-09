package com.kinotoday.kiev.model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Movie {
    private Integer duration;
    private String objectId;
    private String title;
    private String description;
    private java.util.Date updated;
    private String trailer;
    private String imbdLink;
    private java.util.Date created;
    private Double rating;
    private String ownerId;
    private String genre;
    private String kinopoiskLink;
    private java.util.List<Screenshot> screenshots;
    private java.util.List<Session> sessions;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getUpdated() {
        return updated;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImbdLink() {
        return imbdLink;
    }

    public void setImbdLink(String imbdLink) {
        this.imbdLink = imbdLink;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getKinopoiskLink() {
        return kinopoiskLink;
    }

    public void setKinopoiskLink(String kinopoiskLink) {
        this.kinopoiskLink = kinopoiskLink;
    }

    public java.util.List<Screenshot> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(java.util.List<Screenshot> screenshots) {
        this.screenshots = screenshots;
    }


    public Movie save() {
        return Backendless.Data.of(Movie.class).save(this);
    }

    public Future<Movie> saveAsync() {
        if ( Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Movie> future = new Future<Movie>();
            Backendless.Data.of(Movie.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<Movie> callback) {
        Backendless.Data.of(Movie.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(Movie.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if ( Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(Movie.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(Movie.class).remove(this, callback);
    }

    public static Movie findById(String id) {
        return Backendless.Data.of(Movie.class).findById(id);
    }

    public static Future<Movie> findByIdAsync(String id) {
        if ( Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Movie> future = new Future<Movie>();
            Backendless.Data.of(Movie.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<Movie> callback) {
        Backendless.Data.of(Movie.class).findById(id, callback);
    }

    public static Movie findFirst() {
        return Backendless.Data.of(Movie.class).findFirst();
    }

    public static Future<Movie> findFirstAsync() {
        if ( Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Movie> future = new Future<Movie>();
            Backendless.Data.of(Movie.class).findFirst(future);

            return future;
        }
    }

    public static void findFirstAsync(AsyncCallback<Movie> callback) {
        Backendless.Data.of(Movie.class).findFirst(callback);
    }

    public static Movie findLast() {
        return Backendless.Data.of(Movie.class).findLast();
    }

    public static Future<Movie> findLastAsync() {
        if ( Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Movie> future = new Future<Movie>();
            Backendless.Data.of(Movie.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<Movie> callback) {
        Backendless.Data.of(Movie.class).findLast(callback);
    }

    public static BackendlessCollection<Movie> find(BackendlessDataQuery query) {
        return Backendless.Data.of(Movie.class).find(query);
    }

    public static Future<BackendlessCollection<Movie>> findAsync(BackendlessDataQuery query) {
        if ( Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<BackendlessCollection<Movie>> future = new Future<BackendlessCollection<Movie>>();
            Backendless.Data.of(Movie.class).find(query, future);

            return future;
        }
    }

    public static void findAsync(BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Movie>> callback) {
        Backendless.Data.of(Movie.class).find(query, callback);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                ", imdbLink='" + imbdLink + '\'' +
                ", kinopoiskLink='" + kinopoiskLink + '\'' +
                ", rating=" + rating +
                ", sessions=" + sessions +
                '}';
    }
}