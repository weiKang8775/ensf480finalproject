package model.movie;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MovieOffering {
    private int id;
    private Timestamp showtime;
    private Theatre theatre;
    private Movie movie;
    private double seatPrice;

    public MovieOffering(int id, double seatPrice, Timestamp showtime, Movie movie) {
        this.id = id;
        this.seatPrice = seatPrice;
        this.showtime = showtime;
        this.movie = movie;
    }

    public Timestamp getShowtime() {
        return this.showtime;
    }

    public String getMovieName() {
        return this.movie.getName();
    }
    
    public void setTheatre(Theatre t) {
    	this.theatre = t;
    }
    
    public Theatre getTheatre() {
    	return this.theatre;
    }
    
    @Override
    public String toString() {
        String st = "\n";
        String isFull;
        if (this.theatre.getSeatsFilled() == this.theatre.getCapacity()) {
        	isFull = "FULL";
        }
        else {
        	isFull = "" + this.theatre.getSeatsFilled() + "/" + this.theatre.getCapacity();
        }
        
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(showtime);
        
        st +=" Showtime: " + date + " - seats: " + isFull;
        return st;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getId() {
        return this.id;
    }
    
    public double getSeatPrice() {
    	return this.seatPrice;
    }
}
