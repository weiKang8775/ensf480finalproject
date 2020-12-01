package model.strategies.searchStrategy;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import model.movie.Movie;

public class SearchByMovie implements SearchStrategy {

    @Override
    public ArrayList<Movie> search(ArrayList<Movie> movie, String input) {
    	if (input == null) {
    		return movie;
    	}
    	if (input.length() == 0) {
    		return movie;
    	}
        ArrayList<Movie> temp = new ArrayList<>();
        Pattern p = Pattern.compile("^" + input, Pattern.CASE_INSENSITIVE);
        for (Movie m : movie) {
            Matcher matcher = p.matcher(m.getName());
            if (matcher.find()) {
                temp.add(m);
            }
        }
        return temp;
    }
}