package controller;

import java.util.ArrayList;
import model.movie.Ticket;
import model.user.Card;

public interface ControllerCheckoutListener {
    ArrayList<Ticket> checkout(Card card, String email);
}
