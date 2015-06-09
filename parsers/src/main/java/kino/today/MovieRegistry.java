package kino.today;

import kino.today.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hlib on 4/25/15.
 */
public enum MovieRegistry {
    INSTANCE; /* singleton */

    private List<Movie> registeredMovies = new ArrayList<>();

    public Movie getMovieByTitle(String title) {
        for (Movie movie : registeredMovies) {
            if (title.equals(movie.getTitle())) {
                return movie;
            }
        }
        return null;
    }

    public void addIfAbsent(Movie movie) {
        Movie sameMovieFromRegistry = getMovieByTitle(movie.getTitle());
        if (sameMovieFromRegistry == null) {
            registeredMovies.add(movie);
        }
    }
}
