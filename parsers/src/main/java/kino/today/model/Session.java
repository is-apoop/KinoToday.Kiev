package kino.today.model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class Session {
    private String ticketsLink;
    private java.util.Date created;
    private String ownerId;
    private java.util.Date tineStart;
    private java.util.Date updated;
    private Integer price;
    private String objectId;
    private java.util.Date timeEnd;
    private Movie movie;
    private Cinema cinema;

    public String getTicketsLink() {
        return ticketsLink;
    }

    public void setTicketsLink(String ticketsLink) {
        this.ticketsLink = ticketsLink;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public java.util.Date getTineStart() {
        return tineStart;
    }

    public void setTineStart(java.util.Date tineStart) {
        this.tineStart = tineStart;
    }

    public java.util.Date getUpdated() {
        return updated;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getObjectId() {
        return objectId;
    }

    public java.util.Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(java.util.Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }


    public Session save() {
        return Backendless.Data.of(Session.class).save(this);
    }

    public Future<Session> saveAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Session> future = new Future<Session>();
            Backendless.Data.of(Session.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<Session> callback) {
        Backendless.Data.of(Session.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(Session.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(Session.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(Session.class).remove(this, callback);
    }

    public static Session findById(String id) {
        return Backendless.Data.of(Session.class).findById(id);
    }

    public static Future<Session> findByIdAsync(String id) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Session> future = new Future<Session>();
            Backendless.Data.of(Session.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<Session> callback) {
        Backendless.Data.of(Session.class).findById(id, callback);
    }

    public static Session findFirst() {
        return Backendless.Data.of(Session.class).findFirst();
    }

    public static Future<Session> findFirstAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Session> future = new Future<Session>();
            Backendless.Data.of(Session.class).findFirst(future);

            return future;
        }
    }

    public static void findFirstAsync(AsyncCallback<Session> callback) {
        Backendless.Data.of(Session.class).findFirst(callback);
    }

    public static Session findLast() {
        return Backendless.Data.of(Session.class).findLast();
    }

    public static Future<Session> findLastAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Session> future = new Future<Session>();
            Backendless.Data.of(Session.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<Session> callback) {
        Backendless.Data.of(Session.class).findLast(callback);
    }

    public static BackendlessCollection<Session> find(BackendlessDataQuery query) {
        return Backendless.Data.of(Session.class).find(query);
    }

    public static Future<BackendlessCollection<Session>> findAsync(BackendlessDataQuery query) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else

        {
            Future<BackendlessCollection<Session>> future = new Future<BackendlessCollection<Session>>();
            Backendless.Data.of(Session.class).find(query, future);

            return future;
        }

    }

    @Override
    public String toString() {
        return "Session{" +
                "movie=" + (movie != null ? movie.getTitle() : "no movie") +
                ", price=" + price +
                ", timeStart=" + tineStart +
                ", ticketsLink='" + ticketsLink + '\'' +
                '}';
    }

    public static void findAsync(BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Session>> callback) {
        Backendless.Data.of(Session.class).find(query, callback);
    }
}