package model.movie;

import java.util.ArrayList;

import model.strategies.searchStrategy.SearchByMovie;
import model.strategies.searchStrategy.SearchStrategy;

public class MovieCatalogue {
    private ArrayList<Movie> movies;
    private SearchStrategy searchStrategy;

    public MovieCatalogue() {
        this.movies = new ArrayList<>();
        this.searchStrategy = new SearchByMovie();
    }

    public MovieCatalogue(ArrayList<Movie> movies) {
        this.movies = new ArrayList<>();
        for (Movie m : movies) {
            this.movies.add(m);
        }
        this.searchStrategy = new SearchByMovie();
    }

    public void SetStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }
    
    public ArrayList<Movie> search(String input) {
        return this.searchStrategy.search(this.movies, input);
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }
}
