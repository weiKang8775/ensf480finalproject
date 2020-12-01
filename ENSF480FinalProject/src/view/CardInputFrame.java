package view;

import javax.swing.*;
import java.awt.*;
import model.user.*;

public class CardInputFrame{
	
	JTextField numField;
	JTextField nameField;
	JTextField cvsField;
	
	JPanel myPanel;
	
	public CardInputFrame() {
		
		numField = new JTextField("", 20);
		nameField = new JTextField("", 20);
		cvsField = new JTextField("", 3);
		
		myPanel = new JPanel(new GridLayout(3, 2));
		
		myPanel.add(new JLabel("Card Number"));
		myPanel.add(numField);
		myPanel.add(new JLabel("Cardholder Name"));
		myPanel.add(nameField);
		myPanel.add(new JLabel("CVS"));
		myPanel.add(cvsField);
	}
	
	public Card getCardFromUser() {
//		int input = -1;
//		
//		do {
//			input = JOptionPane.showConfirmDialog(null, myPanel, "Enter Card Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
//		} while (input == JOptionPane.OK_OPTION && (numField.getText().isEmpty() || nameField.getText().isEmpty() || cvsField.getText().isEmpty()) || !cvsField.getText().chars().allMatch(Character::isDigit));
//		
//		if (input == JOptionPane.OK_OPTION)
//			return new Card(nameField.getText(), numField.getText(), Integer.parseInt(cvsField.getText()));
		
		return null;
	}
	// public static void main(String[] args) {
	// 	CardInputFrame cif = new CardInputFrame();
	// 	Card c = cif.getCardFromUser();
	// 	System.out.println(c.toString());
	// }
}
