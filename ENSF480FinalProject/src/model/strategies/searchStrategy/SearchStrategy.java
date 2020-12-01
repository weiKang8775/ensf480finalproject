package model.strategies.searchStrategy;

import java.util.ArrayList;

import model.movie.Movie;

public interface SearchStrategy {
    ArrayList<Movie> search(ArrayList<Movie> movie, String input);
}
