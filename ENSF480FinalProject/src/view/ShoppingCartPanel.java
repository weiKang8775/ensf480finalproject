package view;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

import model.movie.Ticket;

public class ShoppingCartPanel extends JPanel {
	private ArrayList<Ticket> tickets;
	private JTextArea infoDisplayArea;
	private JButton doActionButton;

	public ShoppingCartPanel(ViewCheckoutListener listener) {
		tickets = new ArrayList<Ticket>();
		
		setLayout(new BorderLayout());
		infoDisplayArea = new JTextArea("");
		infoDisplayArea.setEditable(false);

		doActionButton = new JButton("Checkout");
		doActionButton.addActionListener((e) -> {
			listener.checkout(tickets);
			clearInfo();
			disableButtons();
		});

		doActionButton.setEnabled(false);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());

		buttonsPanel.add(doActionButton);
		buttonsPanel.setBackground(new Color(255, 255, 255));

		setBackground(new Color(255, 255, 255));
		add(infoDisplayArea, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public void enableButtons() {
		doActionButton.setEnabled(true);
	}

	public void disableButtons() {
		doActionButton.setEnabled(false);
	}

	public void clearInfo() {
		infoDisplayArea.setText("");
		disableButtons();
	}

	public void appendText(String text) {
		infoDisplayArea.append(text + "\n");
		enableButtons();
	}

	public void setText(String text) {
		infoDisplayArea.setText(text);
		enableButtons();
	}

	// MAYBE TODO
	// public void setTicket(Movie m) {
	// this.movie = m;
	// }

	public void addTicket(Ticket t) {
		this.tickets.add(t);
		appendText(t.toString());
	}
}
