package model.strategies.searchStrategy;

import java.util.ArrayList;

import model.movie.Movie;

public class SearchByShowtime implements SearchStrategy {

    @Override
    public ArrayList<Movie> search(ArrayList<Movie> movie, String input) {
        ArrayList<Movie> temp = new ArrayList<>();
        for (Movie m : movie) {
            temp.add(m.searchByShowtime(input));
        }
        
        return temp;
    }
    
}
