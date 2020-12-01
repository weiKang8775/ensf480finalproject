package model.payment;

import java.sql.Date;
import java.util.ArrayList;

import model.user.Card;

public class Receipt {
    private int id;
    private Date purchaseDate;
    private ArrayList<ReceiptItem> items;
    private Totals totals;
    private Card card;

    public Receipt(ArrayList<ReceiptItem> items, Card card) {
        this.items = new ArrayList<>();
        this.totals = new Totals();
        for (ReceiptItem ri : items) {
            this.items.add(ri);
            this.totals.addTotal(ri.getPrice());
        }
        this.card = card;
        this.purchaseDate = new Date(System.currentTimeMillis());
    }
}
