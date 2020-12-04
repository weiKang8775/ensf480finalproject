package model.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.movie.*;
import model.payment.*;
import model.shared.*;
import model.strategies.paymentStrategy.*;
import model.strategies.searchStrategy.*;
import model.user.*;

public class Client {
    private User user;
    private MovieCatalogue movieCatalogue;
    private LoginServer loginServer;
    private Payment payment;
    private ArrayList<Ticket> shoppingCart;

    public Client() {
        this.loginServer = LoginServer.getInstance();
        this.movieCatalogue = new MovieCatalogue();
        this.retrieveMovies();
        this.payment = new Payment();
        payment.setPaymentStrategy(new RegularPaymentStrategy());
        this.shoppingCart = new ArrayList<>();
    }

    private void retrieveMovies() {
        // Get movies from database
    }

    /*---------- USER RELATED ------------*/

    public RegisteredUser register(String email, String password, String name, Address address, Card card) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        RegisteredUser newRU = new RegisteredUser(email, 0, true, new ArrayList<Ticket>(), new ArrayList<Ticket>(), password, name, address, cards);
        this.user = newRU;
        return this.loginServer.addRegisteredUser(newRU);
    }

    public User login(String email, String password) {
        this.user = this.loginServer.authenticate(email, password);
        if (user != null) {
            this.payment.setPaymentStrategy(new RegisteredPaymentStrategy());
        }
        return this.user;
    }

    public boolean hasUser() {
        return this.user != null;
    }
    
    public void setRegisteredUser(RegisteredUser registeredUser) {
    	user = registeredUser;
    	registeredUser.setShoppingCart(shoppingCart);
    }

    /*---------- MOVIES RELATED ------------*/

    public void addMovie(Movie movie) {
        this.movieCatalogue.addMovie(movie);
    }
    
    public MovieCatalogue getMovies() {
        return this.movieCatalogue;
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.movieCatalogue.SetStrategy(searchStrategy);
    }

    public ArrayList<Movie> search(String input) {
        return this.movieCatalogue.search(input);
    }

    public Ticket addTicket(Seat seat) {
        Ticket t = new Ticket(seat);
        this.shoppingCart.add(t);
        return t;
    }
    
    public void setMovieCatalogue(MovieCatalogue movieCatalogue) {
    	this.movieCatalogue = movieCatalogue;
    }

    /*---------- PAYMENT RELATED ------------*/
    
    public ArrayList<Ticket> checkout(Card card, String email) {
    	ArrayList<Ticket> tickets = new ArrayList<>();
    	for(Ticket t: shoppingCart) {
    		tickets.add(t);
    	}
    	if (user == null) {
    		pay(card, email);
    	}
    	else {
    		for(Ticket t: tickets) {
    			t.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
    			t.setUser(user);
    		}
    		pay(card);
    	}
    	shoppingCart.clear();
    	return tickets;
    }

    public void pay(Card card) {
        this.user.pay(card);
    }

    public void pay(Card card, String email) {
        this.user = this.loginServer.findUser(email);
        for(Ticket t: shoppingCart) {
        	t.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
        	t.setUser(user);
        }
        this.user.setShoppingCart(this.shoppingCart);
        this.user.pay(card);
    }

    public void cancel(Ticket ticket) {
        this.user.cancel(ticket);
    }
}
