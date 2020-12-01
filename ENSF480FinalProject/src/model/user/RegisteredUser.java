package model.user;

import java.util.ArrayList;

import model.movie.Ticket;
import model.payment.Payment;
import model.shared.Address;
import model.strategies.paymentStrategy.RegisteredPaymentStrategy;

public class RegisteredUser extends User {
    private String password;
    private String name;
    private Address address;
    private ArrayList<Card> cards;

    public RegisteredUser(int id, String email, double credits, boolean registered, ArrayList<Ticket> sc, ArrayList<Ticket> ph, String password, String name, Address address, ArrayList<Card> cards) {
        super(id, email, credits, registered, sc, ph);
        this.password = password;
        this.name = name;
        this.address = address;
        this.cards = new ArrayList<>();
        for (Card c : cards) {
            this.cards.add(c);
        }
        this.payment = new Payment();
        this.payment.setPaymentStrategy(new RegisteredPaymentStrategy());
    }
    
    public RegisteredUser(int id, String email, double credits, boolean registered, String name, String password, Address address, ArrayList<Card> cards) {
    	super(id, email, credits, registered);
    	this.password = password;
    	this.name = name;
    	this.address = address;
    	this.cards = new ArrayList<>();
    	for (Card c : cards) {
    		this.cards.add(c);
    	}
    	this.payment = new Payment();
    	this.payment.setPaymentStrategy(new RegisteredPaymentStrategy());
    }
    
    public RegisteredUser(String email, double credits, boolean registered, String name, String password, Address address, ArrayList<Card> cards) {
    	super(email, credits, registered);
    	this.password = password;
    	this.name = name;
    	this.address = address;
    	this.cards = new ArrayList<>();
    	for (Card c : cards) {
    		this.cards.add(c);
    	}
    	this.payment = new Payment();
    	this.payment.setPaymentStrategy(new RegisteredPaymentStrategy());
    }

    public RegisteredUser(String email, double credits, boolean registered, ArrayList<Ticket> sc, ArrayList<Ticket> ph, String password, String name, Address address, ArrayList<Card> cards) {
        super(email, credits, registered, sc, ph);
        this.password = password;
        this.name = name;
        this.address = address;
        this.cards = new ArrayList<>();
        for (Card c : cards) {
            this.cards.add(c);
        }
        this.payment = new Payment();
        this.payment.setPaymentStrategy(new RegisteredPaymentStrategy());
    }

    public boolean authenticate(String email, String password) {
        if (this.email.compareTo(email) == 0 && this.password.compareTo(password) == 0) {
            return true;
        }
        return false;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public Address getAddress() {
    	return this.address;
    }
    
    public ArrayList<Card> getCards() {
    	return this.cards;
    }
}
