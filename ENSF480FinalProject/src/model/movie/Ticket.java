package model.movie;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import model.user.User;

public class Ticket {
    private int id;
    private Seat seat;
    private User user;
    private Timestamp purchaseDate;

    public Ticket(int id, Seat seat, Timestamp purchaseDate) {
        this.id = id;
        this.seat = seat;
        this.purchaseDate = purchaseDate;
    }
    
    public Ticket(int id, Seat seat) {
        this.id = id;
        this.seat = seat;
    }

    public Ticket(Seat seat) {
        this.seat = seat;
    }

    public void reserve() {
        this.seat.reserve();
        this.purchaseDate = new Timestamp(System.currentTimeMillis());
    }

    public void cancel() {
        this.seat.cancel();
    }

    // Getters and Setters

    public String getMovieName() {
        return this.seat.getMovieName();
    }

    public double getPrice() {
        return this.seat.getPrice();
    }

    public void setDate(Timestamp date) {
        this.purchaseDate = date;
    }
    
    public int getUserId() {
    	return this.user.getId();
    }
    
    public int getSeatId() {
    	return this.seat.getId();
    }
    
    public Timestamp getPurchaseDate() {
    	return this.purchaseDate;
    }
    
    public Seat getSeat() {
    	return this.seat;
    }
    
    @Override
    public String toString() {
    	String showtime = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(seat.getOffering().getShowtime());
        String st = "\n";
        st += "Movie: " + getMovieName() + "\n";
        st += "Showtime: " + showtime + "\n";
        st += "Theatre: " + seat.getTheatre() + "\n";
        st += "Seat: " + seat.toString()  + "\n";
        st += "Price: " + getPrice() + "\n";
        return  st;
    }
}
