package model.payment;

public class ReceiptItem {
    private String item;
    private double price;

    public ReceiptItem(String item, double price) {
        this.item = item;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}
