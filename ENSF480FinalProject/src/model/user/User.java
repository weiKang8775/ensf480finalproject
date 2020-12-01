package model.user;

import java.util.ArrayList;

import model.movie.Ticket;
import model.payment.Payment;
import model.strategies.paymentStrategy.RegularPaymentStrategy;

public class User implements Comparable {
    protected int id;
    protected String email;
    protected double credits;
    protected boolean registered;
    protected ArrayList<Ticket> shoppingCart;
    protected ArrayList<Ticket> purchaseHistory;
    protected Payment payment;

    public User(int id, String email, double credits, boolean registered, ArrayList<Ticket> sc, ArrayList<Ticket> ph) {
        this.id = id;
        this.email = email;
        this.credits = credits;
        this.registered = registered;
        this.shoppingCart = sc;
        this.purchaseHistory = new ArrayList<>();
        for (Ticket t : ph) {
            this.purchaseHistory.add(t);
        }
        this.payment = new Payment();
        this.payment.setPaymentStrategy(new RegularPaymentStrategy());
    }
    
    public User(int id, String email, double credits, boolean registered) {
    	this.id = id;
    	this.email = email;
    	this.credits = credits;
    	this.registered = registered;
    	this.shoppingCart = new ArrayList<>();
    	this.purchaseHistory = new ArrayList<>();
    	
        this.payment = new Payment();
        this.payment.setPaymentStrategy(new RegularPaymentStrategy());
    }
    
    public User(String email, double credits, boolean registered) {
    	this.email = email;
    	this.credits = credits;
    	this.registered = registered;
    	this.shoppingCart = new ArrayList<>();
    	this.purchaseHistory = new ArrayList<>();
    	
        this.payment = new Payment();
        this.payment.setPaymentStrategy(new RegularPaymentStrategy());
    }

    public User (String email, double credits, boolean registered, ArrayList<Ticket> sc, ArrayList<Ticket> ph) {
        this.email = email;
        this.credits = credits;
        this.registered = registered;
        this.shoppingCart = new ArrayList<>();
        for (Ticket t : sc) {
        	this.shoppingCart.add(t);
        }
        this.purchaseHistory = new ArrayList<>();
        for (Ticket t : ph) {
            this.purchaseHistory.add(t);
        }
        this.payment = new Payment();
        this.payment.setPaymentStrategy(new RegularPaymentStrategy());
    }
    
    public void setPurchaseHistory(ArrayList<Ticket> purchaseHistory) {
    	this.purchaseHistory.clear();
    	for (Ticket t : purchaseHistory) {
    		this.purchaseHistory.add(t);
    	}
    }

    public String getEmail() {
        return this.email;
    }

    public void setShoppingCart(ArrayList<Ticket> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addCredit(double credit) {
        this.credits += credit;
    }

    public void addTicket(ArrayList<Ticket> newTickets) {
        for (Ticket t : newTickets) {
            this.purchaseHistory.add(t);
        }
    }

    public void pay(Card card) {
        this.payment.pay(shoppingCart, card);

        for (Ticket t : shoppingCart) {
            t.reserve();
            this.purchaseHistory.add(t);
        }
        
        this.shoppingCart.clear();
    }

    public void cancel(Ticket ticket) {
        this.purchaseHistory.remove(ticket);
        ticket.cancel();
        this.credits += this.payment.cancel(ticket);
    }
    
    public int getId() {
    	return this.id;
    }
    
    public boolean isRegistered() {
    	return this.registered;
    }
    
    public void setId(int id) {
    	this.id = id;
    }


    @Override
    public int compareTo(Object o) {
        User u = (User) o;
        return this.email.compareTo(u.getEmail());
    }

	public ArrayList<Ticket> getPurchaseHistory() {
		return purchaseHistory;
	}
}
