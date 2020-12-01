package model.strategies.paymentStrategy;

import java.util.ArrayList;

import model.payment.Receipt;
import model.payment.ReceiptItem;
import model.user.Card;
import model.movie.Ticket;

public class RegularPaymentStrategy implements PaymentStrategy {

    @Override
    public Receipt pay(ArrayList<Ticket> tickets, Card card) {
        ArrayList<ReceiptItem> items = new ArrayList<>();
        for (Ticket t : tickets) {
            items.add(new ReceiptItem(t.getMovieName(), t.getPrice()));
        }
        return new Receipt(items, card);
    }

    @Override
    public double cancel(Ticket ticket) {
        ticket.cancel();
        return ticket.getPrice() * 0.85;
    }
    
}
