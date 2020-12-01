package controller;

import model.movie.Seat;
import model.movie.Ticket;

public interface ControllerAddToCart {
    Ticket add(Seat s);
}
