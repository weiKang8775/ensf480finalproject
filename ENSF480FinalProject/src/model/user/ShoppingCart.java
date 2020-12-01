package model.user;

import java.util.ArrayList;

import model.movie.Ticket;
import model.payment.Payment;
import model.strategies.paymentStrategy.PaymentStrategy;

public class ShoppingCart {
    private ArrayList<Ticket> tickets;
    private double total;
    private Payment payment;

    public ShoppingCart() {
        this.tickets = new ArrayList<>();
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.payment.setPaymentStrategy(paymentStrategy);
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        this.total += ticket.getPrice();
    }

    public void checkout(Card card) {
        for (Ticket t : this.tickets) {
            t.reserve();
        }
        this.payment.pay(tickets, card);
    }

    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }
}
