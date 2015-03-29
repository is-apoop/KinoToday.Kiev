package kino.today.model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private Double rating;
    private String name;
    private java.util.Date updated;
    private String ownerId;
    private java.util.Date created;
    private String objectId;
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

    public java.util.Date getUpdated() {
        return updated;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public String getObjectId() {
        return objectId;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public Cinema save() {
        return Backendless.Data.of(Cinema.class).save(this);
    }

    public Future<Cinema> saveAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Cinema> future = new Future<Cinema>();
            Backendless.Data.of(Cinema.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<Cinema> callback) {
        Backendless.Data.of(Cinema.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(Cinema.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(Cinema.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(Cinema.class).remove(this, callback);
    }

    public static Cinema findById(String id) {
        return Backendless.Data.of(Cinema.class).findById(id);
    }

    public static Future<Cinema> findByIdAsync(String id) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Cinema> future = new Future<Cinema>();
            Backendless.Data.of(Cinema.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<Cinema> callback) {
        Backendless.Data.of(Cinema.class).findById(id, callback);
    }

    public static Cinema findFirst() {
        return Backendless.Data.of(Cinema.class).findFirst();
    }

    public static Future<Cinema> findFirstAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Cinema> future = new Future<Cinema>();
            Backendless.Data.of(Cinema.class).findFirst(future);

            return future;
        }
    }


    public void addSession(Session session) {
        sessions.add(session);
    }

    public static void findFirstAsync(AsyncCallback<Cinema> callback) {
        Backendless.Data.of(Cinema.class).findFirst(callback);
    }

    public static Cinema findLast() {
        return Backendless.Data.of(Cinema.class).findLast();
    }

    public static Future<Cinema> findLastAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Cinema> future = new Future<Cinema>();
            Backendless.Data.of(Cinema.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<Cinema> callback) {
        Backendless.Data.of(Cinema.class).findLast(callback);
    }

    public static BackendlessCollection<Cinema> find(BackendlessDataQuery query) {
        return Backendless.Data.of(Cinema.class).find(query);
    }

    public static Future<BackendlessCollection<Cinema>> findAsync(BackendlessDataQuery query) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<BackendlessCollection<Cinema>> future = new Future<BackendlessCollection<Cinema>>();
            Backendless.Data.of(Cinema.class).find(query, future);

            return future;
        }
    }

    public static void findAsync(BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Cinema>> callback) {
        Backendless.Data.of(Cinema.class).find(query, callback);
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