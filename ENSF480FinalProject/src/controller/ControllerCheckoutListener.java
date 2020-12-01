package controller;

import java.util.ArrayList;
import model.movie.Ticket;
import model.user.Card;

public interface ControllerCheckoutListener {
    void checkout(Card card, ArrayList<Ticket> tickets);
}
