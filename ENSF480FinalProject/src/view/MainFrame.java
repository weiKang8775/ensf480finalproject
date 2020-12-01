package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import controller.*;
import model.movie.*;
import model.user.*;

public class MainFrame extends JFrame {
    private LoginFrame loginFrame;
    private SearchBar searchBar;
    private JButton loginButton;
    private MoviesPanel moviesPanel;
    private MovieDetailsPanel movieDetailsPanel;
    private ShoppingCartPanel shoppingCartPanel;
    private PurchaseHistoryPanel purchaseHistoryPanel;
    private User user;

    public MainFrame(ControllerLoginListener cll, ControllerSearchListener csl, ControllerAddToCart cac, ControllerCheckoutListener ccl, ControllerCancelTicketListener cct) {
        super("Ticket Reservation");
        this.loginFrame = new LoginFrame((String email, String password) -> {
        	RegisteredUser ru = (RegisteredUser) cll.login(email, password);
            user = ru;
            if (user != null) {
                loginFrame.dispose();
                JOptionPane.showMessageDialog(loginFrame, "Welcome back, " + ru.getName() , "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                loginFrame.clearFields();
                JOptionPane.showMessageDialog(loginFrame, "Your email or password is incorrect.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.moviesPanel = new MoviesPanel((Movie m) -> {
            movieDetailsPanel.setMovie(m);
        });

        this.searchBar = new SearchBar((String input, int mode) -> {
            this.moviesPanel.setMovies(csl.search(input, mode));
        });

        this.purchaseHistoryPanel = new PurchaseHistoryPanel((Ticket t) -> {
            cct.cancel(t);
        });

        this.shoppingCartPanel = new ShoppingCartPanel((ArrayList<Ticket> tickets) -> {
        	if (user == null) {
                // ask the user for card.
                CardInputFrame cif = new CardInputFrame();
                Card card = cif.getCardFromUser();
                String email = cif.getEmailFromUser();
                
                ArrayList<Ticket> currentTickets = ccl.checkout(card, email);
                purchaseHistoryPanel.addTickets(currentTickets);
        	}
        });
        
        loginButton = new JButton("Login");
        JPanel topPanel = new JPanel();
        topPanel.add(searchBar);
        topPanel.add(loginButton);
        
        loginButton.addActionListener((e)->{
        	loginFrame.setVisible(true);
        });
        
        
        JTabbedPane ticketPane = new JTabbedPane();
        ticketPane.addTab("Shopping Cart", shoppingCartPanel);
        ticketPane.addTab("Purchase History", purchaseHistoryPanel);

        Dimension d = ticketPane.getPreferredSize();
		d.width = 350;
        ticketPane.setPreferredSize(d);
        ticketPane.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));
        ticketPane.setBackground(new Color(255, 255, 255));


        this.movieDetailsPanel = new MovieDetailsPanel((Seat seat) -> {
            this.shoppingCartPanel.addTicket(cac.add(seat));
        });

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(this.moviesPanel, BorderLayout.WEST);
        this.add(this.movieDetailsPanel, BorderLayout.CENTER);
        this.add(ticketPane, BorderLayout.EAST);
        this.setVisible(true);
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void initializeMovieList(ArrayList<Movie> movies) {
        moviesPanel.setMovies(movies);
    }
    
}
