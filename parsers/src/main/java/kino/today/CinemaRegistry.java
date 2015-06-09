package kino.today;

import kino.today.model.Cinema;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hlib on 4/25/15.
 */
public enum CinemaRegistry {
    INSTANCE; /* singleton */

    private List<Cinema> registeredCinemas = new ArrayList<>();

    public Cinema getCinemaByName(String title) {
        for (Cinema cinema : registeredCinemas) {
            if (title.equals(cinema.getName())) {
                return cinema;
            }
        }
        return null;
    }

    public void addIfAbsent(Cinema cinema) {
        Cinema sameMovieFromRegistry = getCinemaByName(cinema.getName());
        if (sameMovieFromRegistry == null) {
            registeredCinemas.add(cinema);
        }
    }
}
