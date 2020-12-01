package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class SearchBar extends JPanel {
    private JTextField searchBar;
    private int searchMode;

	public SearchBar(ViewSearchListener listener) {
        this.searchMode = 1;
		Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border innerBorder = BorderFactory.createTitledBorder("Search");
		setBorder(BorderFactory.createCompoundBorder(padding, innerBorder));
		setLayout(new BorderLayout());
		searchBar = new JTextField(20);

		searchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if ((e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') || (e.getKeyChar() >= 'A' && e.getKeyChar() <= 'Z')
						|| (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == 8 || e.getKeyChar() == 32) {
					listener.search(searchBar.getText(), searchMode);
				}
			}
		});
		
		listener.search("", 1);

		add(searchBar, BorderLayout.CENTER);
		setBackground(new Color(255, 255, 255));
	}
}
