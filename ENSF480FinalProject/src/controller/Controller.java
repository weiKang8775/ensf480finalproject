package controller;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import model.controller.*;
import model.movie.*;
import model.strategies.searchStrategy.SearchByMovie;
import model.strategies.searchStrategy.SearchByShowtime;
import model.user.Card;
import model.user.User;
import view.MainFrame;

public class Controller {
    private Client client;
    private MainFrame mf;
    private ModelDataController mdc;


    public Controller() {
    	init();
    	
        // Setting up login listener
        ControllerLoginListener cll = new ControllerLoginListener() {
            @Override
            public User login(String email, String password) {
                return client.login(email, password);
            }
        };

        // Setting up search listener
        ControllerSearchListener csl = new ControllerSearchListener() {
            @Override
            public ArrayList<Movie> search(String input, int mode) {
                if (mode == 1) {
                    client.setSearchStrategy(new SearchByMovie());
                } else {
                    client.setSearchStrategy(new SearchByShowtime());
                }
                return client.search(input);
            }
        };

        // Setting up add to cart listener
        ControllerAddToCart cac = new ControllerAddToCart() {
            @Override
            public Ticket add(Seat s) {
                return client.addTicket(s);
            }
        };

        // Setting up checkout listener
        ControllerCheckoutListener ccl = new ControllerCheckoutListener() {
            @Override
            public ArrayList<Ticket> checkout(Card card, String email) {
                return client.checkout(card, email);
            }
        };

        // Setting up cancel ticket listener
        ControllerCancelTicketListener cct = new ControllerCancelTicketListener() {
            @Override
            public void cancel(Ticket t) {
                client.cancel(t);
            }
        };

        // SwingUtilities.invokeLater(() -> {
            mf = new MainFrame(cll, csl, cac, ccl, cct);
        // });

    }

    /*---------- USER RELATED ------------*/

    public User login(String email, String password) {
        return client.login(email, password);
    }

    public boolean hasUser() {
        return this.client.hasUser();
    }

    /*---------- MOVIES RELATED ------------*/

    public MovieCatalogue getMovies() {
        return this.client.getMovies();
    }
    
    private void init() {
    	this.mdc = new ModelDataController();
        mdc.getUsers();
        this.client = new Client();
        this.client.setMovieCatalogue(mdc.retrieveMoiveCatalogue());
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
