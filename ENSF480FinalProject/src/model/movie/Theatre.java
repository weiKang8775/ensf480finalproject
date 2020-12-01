package model.movie;

import java.util.ArrayList;

public class Theatre {
    private int id;
    private int number;
    private int capacity;
    private int seatsFilled;
    private MovieOffering movieOffering;
    private ArrayList<Seat> seats;

    public Theatre(int id, int number, int capacity, int seatsFilled, MovieOffering movieOffering) {
    	this.seats = new ArrayList<>();
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.seatsFilled = seatsFilled;
        this.movieOffering = movieOffering;
    }
    
    public int getId() {
        return id;
    }
    
    public int getCapacity() {
    	return this.capacity;
    }
    
    public MovieOffering getMovieOffering() {
    	return this.movieOffering;
    }
    
    public void setSeats(ArrayList<Seat> seats) {
    	this.seats = seats;
    }
    
    public ArrayList<Seat> getSeats() {
    	return this.seats;
    }
    
    public int getSeatsFilled() {
    	return this.seatsFilled;
    }

//    public void addSeat(Seat seat) {
//        
//        if (isFull)
//            return;
//        
//        this.seats.add(seat);
//        
//        if (seats.size() == number)
//            isFull = true;
//    }
//
//    public ArrayList<Seat> getSeats() {
//        return seats;
//    }
//
//    public void setSeat(ArrayList<Seat> seats){
//        this.seats = seats;
//    }

    @Override 
    public String toString(){
        return "" + number; 
    }

}
