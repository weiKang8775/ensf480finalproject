package model.movie;

public class Seat {
    private int id;
    private int row;
    private int col;
    private int seatNumber;
    private boolean reserved;
    private Theatre theatre;
    private double price;

    public Seat(int id, int row, int col, int seatNumber, boolean reserved, double price, Theatre theatre) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.seatNumber = seatNumber;
        this.reserved = reserved;
        this.price = price;
        this.theatre = theatre;
    }
    
    public Seat(int row, int col, int seatNumber, boolean reserved, double price, Theatre theatre) {
        this.row = row;
        this.col = col;
        this.seatNumber = seatNumber;
        this.reserved = reserved;
        this.price = price;
        this.theatre = theatre;
    }

    public boolean reserved() {
        return this.reserved;
    }

    public void reserve() {
        this.reserved = true;
    }
    
    public MovieOffering getOffering() {
        return this.theatre.getMovieOffering();
    }

    public double getPrice() {
        return this.price;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public String getMovieName() {
        return this.theatre.getMovieOffering().getMovieName();
    }

    public void cancel() {
        this.reserved = false;
    }
    
    public Theatre getTheatre() {
    	return this.theatre;
    }
    
    public int getSeatNumber() {
    	return this.seatNumber;
    }
    
    public int getId() {
    	return this.id;
    }

    public String toString() {
        return String.valueOf(seatNumber);
    }

}
