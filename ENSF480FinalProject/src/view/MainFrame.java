package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import controller.*;
import model.movie.*;
import model.shared.Address;
import model.user.*;

public class MainFrame extends JFrame {
    private LoginFrame loginFrame;
    private SearchBar searchBar;
    private JButton loginButton;
    private JButton registerButton;
    private MoviesPanel moviesPanel;
    private MovieDetailsPanel movieDetailsPanel;
    private ShoppingCartPanel shoppingCartPanel;
    private PurchaseHistoryPanel purchaseHistoryPanel;
    private RegisterFrame registerFrame;
    private User user;

    public MainFrame(ControllerLoginListener cll, ControllerSearchListener csl, ControllerAddToCart cac, ControllerCheckoutListener ccl, ControllerCancelTicketListener cct,
    		ControllerRegisterListener crl) {
        super("Ticket Reservation");
        this.loginFrame = new LoginFrame((String email, String password) -> {
        	RegisteredUser ru = (RegisteredUser) cll.login(email, password);
            user = ru;
            if (user != null) {
                loginFrame.dispose();
                loginButton.setEnabled(false);
                registerButton.setEnabled(false);
                JOptionPane.showMessageDialog(loginFrame, "Welcome back, " + ru.getName() , "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                loginFrame.clearFields();
                JOptionPane.showMessageDialog(loginFrame, "Your email or password is incorrect.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.registerFrame = new RegisterFrame((String email, String name, String password, Address address, Card card)-> {
        	ArrayList<Card> cards = new ArrayList<>();
        	cards.add(card);
        	RegisteredUser ru = new RegisteredUser(email, 0, true, name, password, address, cards);
        	user = ru;
        	crl.setRegisteredUser(ru);
        	JOptionPane.showMessageDialog(registerFrame, "Registered. $45 has been charged to your card.", "Registered.", JOptionPane.INFORMATION_MESSAGE);
        	registerFrame.dispose();
        	registerButton.setEnabled(false);
        	loginButton.setEnabled(false);
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
        	String email = "";
        	Card card;
        	if (user == null) {
                // ask the user for card.
                CardInputFrame cif = new CardInputFrame();
                card = cif.getCardFromUser();
                email = cif.getEmailFromUser();
                
                ArrayList<Ticket> currentTickets = ccl.checkout(card, email);
                purchaseHistoryPanel.addTickets(currentTickets);
        	}
        	else {
        		RegisteredUser ru = (RegisteredUser) user;
        		card = ru.getCards().get(0);
                email = ru.getEmail();
                
                ArrayList<Ticket> currentTickets = ccl.checkout(card, email);
                purchaseHistoryPanel.addTickets(currentTickets);
        	}
        });
        
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(searchBar, BorderLayout.CENTER);
        topPanel.add(loginButton, BorderLayout.EAST);
        topPanel.add(registerButton, BorderLayout.WEST);
        
        loginButton.addActionListener((e)->{
        	loginFrame.setVisible(true);
        });
        
        registerButton.addActionListener((e)->{
        	registerFrame.setVisible(true);
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
