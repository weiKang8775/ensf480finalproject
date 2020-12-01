package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

import model.user.*;

public class CardInputFrame{
	
	JTextField emailField;
	JTextField numField;
	JTextField nameField;
	JTextField cvsField;
	JTextField expiryField;
	
	JPanel myPanel;
	
	public CardInputFrame() {
		
		emailField = new JTextField("", 20);
		numField = new JTextField("", 20);
		nameField = new JTextField("", 20);
		cvsField = new JTextField("", 3);
		expiryField = new JTextField("", 5);
		
		myPanel = new JPanel(new GridLayout(5, 2));
		
		myPanel.add(new JLabel("Email"));
		myPanel.add(emailField);
		myPanel.add(new JLabel("Card Number"));
		myPanel.add(numField);
		myPanel.add(new JLabel("Cardholder Name"));
		myPanel.add(nameField);
		myPanel.add(new JLabel("CVS"));
		myPanel.add(cvsField);
		myPanel.add(new JLabel("Expiry (MM/YY)"));
		myPanel.add(expiryField);
	}
	
	public Card getCardFromUser() {
		int input = -1;
		
		do {
			input = JOptionPane.showConfirmDialog(null, myPanel, "Enter Card Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		} while (input == JOptionPane.OK_OPTION && (numField.getText().isEmpty() || nameField.getText().isEmpty() || 
				cvsField.getText().isEmpty()) || !cvsField.getText().chars().allMatch(Character::isDigit) || expiryField.getText().isEmpty()||
				emailField.getText().isEmpty());
		
		if (input == JOptionPane.OK_OPTION) {
			Date date = new Date(System.currentTimeMillis());
			return new Card(nameField.getText(), numField.getText(), cvsField.getText(), date);
		}
		return null;
	}
	
	public String getEmailFromUser() {
		return emailField.getText();
	}
	// public static void main(String[] args) {
	// 	CardInputFrame cif = new CardInputFrame();
	// 	Card c = cif.getCardFromUser();
	// 	System.out.println(c.toString());
	// }
}
