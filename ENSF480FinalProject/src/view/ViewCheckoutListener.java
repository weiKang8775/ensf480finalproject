package view;

import java.util.ArrayList;
import model.movie.Ticket;

public interface ViewCheckoutListener {
    void checkout(ArrayList<Ticket> tickets);
}
