package model.strategies.paymentStrategy;

import java.util.ArrayList;

import model.payment.Receipt;
import model.user.Card;
import model.movie.Ticket;

public interface PaymentStrategy {
    Receipt pay(ArrayList<Ticket> tickets, Card card);
    double cancel(Ticket ticket);
}
