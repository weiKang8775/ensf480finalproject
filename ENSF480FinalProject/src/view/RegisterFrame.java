package view;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.shared.Address;
import model.user.Card;

public class RegisterFrame extends JFrame{
	private JTextField emailField;
	private JPasswordField passwordField;
	private JTextField streetField;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField countryField;
	private JTextField postalCodeField;
	private JTextField nameField;
	private JTextField cardNumberField;
	private JTextField secNumField;
	private JTextField expiryField;
	private JButton registerBtn;
	
	public RegisterFrame(ViewRegisterListener listener) {
		super("Register");
		setVisible(false);
		
		emailField = new JTextField(15);
		passwordField = new JPasswordField(15);
		streetField = new JTextField(15);
		cityField = new JTextField(15);
		stateField = new JTextField(15);
		countryField = new JTextField(15);
		postalCodeField = new JTextField(15);
		nameField = new JTextField(15);
		cardNumberField = new JTextField(15);
		secNumField = new JTextField(15);
		expiryField = new JTextField(15);
		registerBtn = new JButton("Register");
		
		registerBtn.addActionListener((ActionEvent e) -> {
			Address address = new Address(streetField.getText(), cityField.getText(), stateField.getText(), countryField.getText(), 
					postalCodeField.getText());
			Date date = new Date(System.currentTimeMillis());
			Card card = new Card(nameField.getText(), cardNumberField.getText(), secNumField.getText(), date);
			
			listener.register(emailField.getText(), nameField.getText(), String.valueOf(passwordField.getPassword()), address, card);
		});
		
		JPanel myPanel = new JPanel();
		myPanel = new JPanel(new GridLayout(11, 2));
		
		myPanel.add(new JLabel("Email"));
		myPanel.add(emailField);
		myPanel.add(new JLabel("Password"));
		myPanel.add(passwordField);
		myPanel.add(new JLabel("Street"));
		myPanel.add(streetField);
		myPanel.add(new JLabel("City"));
		myPanel.add(cityField);
		myPanel.add(new JLabel("Country"));
		myPanel.add(countryField);
		myPanel.add(new JLabel("PostalCode"));
		myPanel.add(postalCodeField);
		myPanel.add(new JLabel("Cardholder Name"));
		myPanel.add(nameField);
		myPanel.add(new JLabel("Card Number"));
		myPanel.add(cardNumberField);
		myPanel.add(new JLabel("CVV"));
		myPanel.add(secNumField);
		myPanel.add(new JLabel("Expiry (MM/YY)"));
		myPanel.add(expiryField);
		myPanel.add(new JLabel(""));
		myPanel.add(registerBtn);		
		add(myPanel);
		
		myPanel.setBorder(new EmptyBorder(new Insets(50, 30, 50, 30)));
		pack();
		this.setLocationRelativeTo(null);
	}
}

