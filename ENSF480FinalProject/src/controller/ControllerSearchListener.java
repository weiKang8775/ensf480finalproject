package controller;

import java.util.ArrayList;

import model.movie.Movie;

public interface ControllerSearchListener {
    ArrayList<Movie> search(String input, int mode);
}
