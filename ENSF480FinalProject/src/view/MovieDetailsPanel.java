package view;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

import model.movie.*;

public class MovieDetailsPanel extends JPanel {
	private Movie movie;
	private MovieOffering movieOffering;
	private JTextArea infoDisplayArea;
	private DefaultComboBoxModel<String> seatsModel;
	private DefaultComboBoxModel<String> showtimeModel;
	private JComboBox<String> seats;
	private JComboBox<String> showtimes;
	private JButton doActionButton;

	public MovieDetailsPanel(ViewAddToCartListener listener) {
		setLayout(new BorderLayout());
		infoDisplayArea = new JTextArea();
		infoDisplayArea.setEditable(false);
		setBorder(BorderFactory.createTitledBorder("Movie Details"));
		Dimension d = getPreferredSize();
		d.width = 400;
		setPreferredSize(d);
		
		Font f = infoDisplayArea.getFont();
		Font newFont = new Font(f.getFontName(), f.getStyle(), f.getSize() + 2);
		infoDisplayArea.setFont(newFont);

		seatsModel = new DefaultComboBoxModel<>();
		showtimeModel = new DefaultComboBoxModel<>();

		seats = new JComboBox<>(seatsModel);
		showtimes = new JComboBox<>(showtimeModel);

		doActionButton = new JButton("Add to Cart");
		doActionButton.addActionListener((e) -> {
			int selectedShowtime = showtimes.getSelectedIndex();
			MovieOffering mo = movie.getOfferings().get(selectedShowtime);
			int seatNumber = Integer.valueOf((String) seats.getSelectedItem());
			int row = seatNumber / 15;
			int col = seatNumber % 15;
			
			Seat newSeat = new Seat(row, col, seatNumber, false, mo.getSeatPrice(), mo.getTheatre());

			listener.add(newSeat);
			clearInfo();
			clearSection();
			disableButtons();
		});

		doActionButton.setEnabled(false);
		seats.setEnabled(false);
		showtimes.setEnabled(false);
		
		showtimes.addActionListener((e) -> {
			selectSeats(showtimes.getSelectedIndex());
		});

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());

		buttonsPanel.add(showtimes);
		buttonsPanel.add(seats);
		buttonsPanel.add(doActionButton);
		buttonsPanel.setBackground(new Color(255, 255, 255));

		add(infoDisplayArea, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		setBackground(new Color(255, 255, 255));
	}
	
	private void selectSeats(int selected) {
		if (selected < 0) {
			return;
		}
		this.seatsModel.removeAllElements();
		ArrayList<Seat> seats = movie.getOfferings().get(selected).getTheatre().getSeats();
		int capacity = movie.getOfferings().get(selected).getTheatre().getCapacity();
		for (int i = 1; i <= capacity; i++) {
			boolean reserved = false;
			for (Seat s : seats) {
				if (s.getSeatNumber() == i) {
					reserved = true;
					break;
				}
			}
			if (!reserved) {
				this.seatsModel.addElement(String.valueOf(i));
			}
		}
	}

	private Seat findSeat(int row, int col) {
		for (Seat s : this.movieOffering.getTheatre().getSeats()) {
			if (s.getRow() == row && s.getCol() == col) {
				return s;
			}
		}
		return null;
	}

	private void findOffering(String date) {
		for (MovieOffering mo : this.movie.getOfferings()) {
			if (mo.getShowtime().toString().equals(date)) {
				this.movieOffering = mo;
			}
		}
	}

	public void enableButtons() {
		showtimes.setEnabled(true);
		seats.setEnabled(true);
		doActionButton.setEnabled(true);
	}

	public void disableButtons() {
		showtimes.setEnabled(false);
		seats.setEnabled(false);
		doActionButton.setEnabled(false);
	}

	public void clearInfo() {
		infoDisplayArea.setText("");
	}

	public void appendText(String text) {
		infoDisplayArea.append(text + "\n");
	}

	public void setText(String text) {
		infoDisplayArea.setText(text);
	}

	public void addShowtime(String showtime) {
		showtimeModel.addElement(showtime);
	}
	
	public void clearShowtime() {
		showtimeModel.removeAllElements();
	}
	
	public void clearSeats() {
		seatsModel.removeAllElements();
	}

	public void addSeat(String seat) {
		seatsModel.addElement(seat);
	}

	public void clearSection() {
		seatsModel.removeAllElements();
		showtimeModel.removeAllElements();
	}

	public void setMovie(Movie m) {
		enableButtons();
		clearSection();
		
		clearInfo();
		
		this.movie = m;
		for (MovieOffering mo : m.getOfferings()) {
			String showtime = new SimpleDateFormat("MM/dd HH:mm").format(mo.getShowtime());
			this.addShowtime(showtime);
		}
		
		setText(m.toString());
	}
}
