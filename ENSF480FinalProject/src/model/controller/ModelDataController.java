package model.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.movie.Movie;
import model.movie.MovieCatalogue;
import model.movie.MovieOffering;
import model.movie.Seat;
import model.movie.Theatre;
import model.movie.Ticket;
import model.shared.Address;
import model.user.Card;
import model.user.RegisteredUser;
import model.user.User;

public class ModelDataController implements IDBCredentials {
    private Connection conn;

    public ModelDataController() {
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MovieCatalogue retrieveMoiveCatalogue () {
        return new MovieCatalogue(retrieveMovies());
    }
    
    public void saveTickets(ArrayList<Ticket> tickets) {
    	for (Ticket t : tickets) {
    		String query = "INSERT INTO tickets ( user_id, seat_id, purchase_date)"
    				+ "VALUES ("
    				+ t.getUserId() + ", "
    				+ t.getSeatId() + ", "
    				+ "'" + t.getPurchaseDate() + "')";
    		Statement s;
			try {
				s = conn.createStatement();
				s.executeUpdate(query);
				Seat seat = t.getSeat();
				query = "INSERT INTO seats (`row`, col, `number`, theatre_id, price)"
						+ "VALUES ("
						+ seat.getRow() + ", "
						+ seat.getCol() + ", "
						+ seat.getSeatNumber() + ", "
						+ seat.getTheatre().getId() + ", "
						+ seat.getPrice() + ")";
				System.out.println(query);
				s.executeUpdate(query);
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void getUsers() {
    	String query = "SELECT * FROM users";
    	LoginServer loginServer = LoginServer.getInstance();
    	try {
    		Statement s = conn.createStatement();
    		ResultSet rs = s.executeQuery(query);
    		while (rs.next()) {
    			boolean registered = rs.getBoolean("registered");
    			int userId = rs.getInt("id");
    			if (registered) {
    				Address addr = null;
    				ArrayList<Card> cards = new ArrayList<>();
    				query = "SELECT * FROM addresses WHERE user_id = " + userId;
    				Statement stmt = conn.createStatement();
    				ResultSet RS = stmt.executeQuery(query);
    				if (RS.next()) {
    					addr = new Address(RS.getString("street_addr"), RS.getString("city"), RS.getString("state"), RS.getString("country"), RS.getString("postal_code"));
    				}
    				query = "SELECT * FROM cards WHERE user_id = " + userId;
    				RS = stmt.executeQuery(query);
    				if (RS.next()) {
    					cards.add(new Card(RS.getString("name_on_card"), RS.getString("card_number"), RS.getString("cvv"), RS.getDate("expiry_date")));
    				}
    				RegisteredUser user = new RegisteredUser(userId, rs.getString("email"), rs.getDouble("credits"), true, rs.getString("name"), rs.getString("password"), addr, cards);
    				loginServer.addRegisteredUser(user);
    				stmt.close();
    			}
    			else {
    				User user = new User(userId, rs.getString("email"), rs.getDouble("credits"), false);
    				loginServer.addUser(user);
    			}
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void getUserPurchaseHistory(User user) {
    	String query = "SELECT * FROM tickets WHERE user_id = " + user.getId();
    	
    }
    
    public void saveUser(User user) {
    	if (user.isRegistered()) {
    		this.saveRU((RegisteredUser) user);
    		return;
    	}
    	
    	String query = "INSERT INTO users (email, registered) VALUES (" + "'" + user.getEmail() + "', FALSE)";
    	try {
			Statement s = conn.createStatement();
			s.executeUpdate(query);
			query = "SELECT id from users ORDER BY id DESC LIMIT 1";
			ResultSet rs = s.executeQuery(query);
			if (rs.next()) {
				user.setId(rs.getInt("id"));
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void saveRU(RegisteredUser user) {
    	String query = 
    			"INSERT INTO users (email, registered, `password`, `name`)"
    			+ "VALUES ("
    			+ "'" + user.getEmail() + "', "
    			+ "TRUE, "
    			+ "'" + user.getPassword() + "', "
    			+ "'" + user.getName() + "')";
    	try {
    		Statement s = conn.createStatement();
    		s.executeUpdate(query);
    		query = "SELECT id from users ORDER BY id DESC LIMIT 1";
    		ResultSet rs = s.executeQuery(query);
    		int userId = -1;
    		if (rs.next()) {
    			userId = rs.getInt("id");
    			user.setId(userId);
    		}
    		Address addr = user.getAddress();
    		ArrayList<Card> cards = user.getCards();
    		query = "INSERT INTO addresses (street_addr, city, state, country, postal_code, user_id)"
    				+ "VALUES ("
    				+ "'" + addr.getStreetAddress() + "', "
    				+ "'" + addr.getCity() + "', "
    				+ "'" + addr.getState() + "', "
    				+ "'" + addr.getCountry() + "', "
    				+ "'" + addr.getPostalCode() + "', "
    				+ userId + ")";
    		s.executeUpdate(query);
    		
    		for (Card c : cards) {
    			String expDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getExpiryDate());
    			query = "INSERT INTO cards (name_on_card, card_number, expiry_date, cvv, user_id)"
    					+ "VALUES ("
    					+ "'" + c.getNameOnCard() + "', "
    					+ "'" + c.getCardNumber() + "', "
    					+ "'" + expDate + "', "
    					+ "'" + c.getSecNum() + "', "
    					+ userId + ")";
    			s.executeUpdate(query);
    		}
    		s.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    private ArrayList<Movie> retrieveMovies () {
        String query = "SELECT * FROM movie_catalogue";
        ArrayList<Movie> movies = new ArrayList<>();
        try {
        	Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
            	Movie movie = new Movie(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("length"), rs.getDate("available_date"));
                movie.setMovieOfferings(retrieveMovieOffering(movie));
                movies.add(movie);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private ArrayList<MovieOffering> retrieveMovieOffering(Movie movie) {
        String query = "SELECT * FROM movie_offerings WHERE movie_id = " + movie.getId();
        ArrayList<MovieOffering> movies = new ArrayList<>();
        try {
        	Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                MovieOffering movieOffering = new MovieOffering(rs.getInt("id"), rs.getDouble("seat_price"), rs.getTimestamp("showtime"), movie);
                Theatre t = retrieveTheatre(movieOffering);
                t.setSeats(retrieveSeats(t));
                movieOffering.setTheatre(t);
                movies.add(movieOffering);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    private Theatre retrieveTheatre(MovieOffering movieOffering) {
    	String query = "SELECT * FROM theatres WHERE movie_offering_id = " + movieOffering.getId();
    	try {
    		Statement s = conn.createStatement();
    		ResultSet rs = s.executeQuery(query);
    		if (rs.next()) {
    			return new Theatre(rs.getInt("id"), rs.getInt("number"), rs.getInt("capacity"), rs.getInt("seats_filled"), movieOffering);
    		}
    		s.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    private ArrayList<Seat> retrieveSeats(Theatre theatre) {
        String query = "SELECT * FROM seats WHERE theatre_id = " + theatre.getId();
        ArrayList<Seat> seats = new ArrayList<>();
        try {
        	Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                seats.add(new Seat(rs.getInt("id"), rs.getInt("row"), rs.getInt("col"), rs.getInt("number"), rs.getBoolean("reserved"), rs.getDouble("price"), theatre));
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }
    
}
