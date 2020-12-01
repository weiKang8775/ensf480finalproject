package model.payment;

public class Totals {
    private double subTotal;
    private double salesTax;
    private double total;

    public void addTotal(double price) {
        this.subTotal += price;
        this.salesTax = this.subTotal * 0.05;
        this.total = subTotal + salesTax;
    }

    
}
