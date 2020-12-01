package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {
	private JTextField emailField;
	private JPasswordField passwordField;
    private JButton loginBtn;

	public LoginFrame(ViewLoginListener listener) {
		super("Login");
		setVisible(false);

		emailField = new JTextField(15);
		passwordField = new JPasswordField(15);
		loginBtn = new JButton("Login");

		loginBtn.addActionListener((ActionEvent e) -> {
			listener.login(emailField.getText(), String.valueOf(passwordField.getPassword()));
		});

        JPanel panel = new JPanel();
        
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 20, 5);

		/*----------ROW 1----------*/
		gc.gridx = 0;
		gc.gridy = 3;
		gc.weightx = 0.2;
		gc.weighty = 0.2;
		gc.anchor = GridBagConstraints.LINE_END;

		panel.add(new JLabel("Email"), gc);

		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;

		panel.add(emailField, gc);

		/*----------ROW 2----------*/

		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_END;

		panel.add(new JLabel("Password"), gc);

		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;

		panel.add(passwordField, gc);

		/*----------ROW 3----------*/

		gc.gridx = 1;
		gc.gridy = 5;
		gc.weightx = 5.0;
		gc.weighty = 5.0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;

		panel.add(loginBtn, gc);

		panel.setBorder(new EmptyBorder(new Insets(50, 30, 50, 30)));

		add(panel);

		pack();
    }
    
    public void clearFields() {
        this.emailField.setText("");
        this.passwordField.setText("");
    }
}
