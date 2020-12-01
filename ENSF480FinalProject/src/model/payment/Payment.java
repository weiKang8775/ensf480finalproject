package model.payment;

import java.util.ArrayList;

import model.movie.Ticket;
import model.strategies.paymentStrategy.PaymentStrategy;
import model.user.Card;

public class Payment {
    private PaymentStrategy paymentStrategy;

    public Receipt pay(ArrayList<Ticket> tickets, Card card) {
        return this.paymentStrategy.pay(tickets, card);
    }

    public double cancel(Ticket ticket) {
        return this.paymentStrategy.cancel(ticket);
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
}
