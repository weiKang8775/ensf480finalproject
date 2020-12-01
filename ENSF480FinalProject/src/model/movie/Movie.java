package model.movie;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Movie {
    private int id;
    private String name;
    private String description;
    private int length;
    private ArrayList<MovieOffering> offerings;
    private Date availableDate;

    public Movie(int id, String name, String description, int length, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.length = length;
        this.offerings = new ArrayList<>();
        this.availableDate = date;
    }

    public void addOffering(MovieOffering offering) {
        this.offerings.add(offering);
    }

    public Movie searchByShowtime(String input) {
        for (MovieOffering mo : this.offerings) {
            if (String.valueOf(mo).compareTo(input) == 0) {
                return this;
            }
        }
        return null;
    }

    // Getters and Setters

    public String getName() {
        return this.name;
    }

    public ArrayList<MovieOffering> getOfferings() {
        return this.offerings;
    }

    public Date getAvailableDate() {
        return this.availableDate;
    }

    public int getLength() {
        return this.length;
    }

    public String getDescription() {
        return this.description;
    }
    
    @Override
    public String toString() {
        String result = "";
        
        result += "Movie Title: " + name + "\n\n";
        result += "Description: " + description + "\n\n";
        result += "Length: " + length + " min\n\n";
        
        for(MovieOffering off: offerings){
            result += off.toString();
        }

        result += "\n";
        
        return result;
    }

    public int getId() {
        return this.id;
    }

    public void setMovieOfferings(ArrayList<MovieOffering> offerings) {
        this.offerings = offerings;
    }
}
