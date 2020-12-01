package view;

import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

import model.movie.Movie;

public class MoviesPanel extends JPanel {
	private ArrayList<Movie> movies;
	private JList<String> results;
	private DefaultListModel<String> model;

	public MoviesPanel(ViewMoviesListener listener) {
		this.movies = new ArrayList<>();
		Dimension d = getPreferredSize();
		d.width = 300;
		setPreferredSize(d);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Movies"));

		results = new JList<>();
		model = new DefaultListModel<>();
		results.setModel(model);

		results.addListSelectionListener((ListSelectionEvent event) -> {
			if (!event.getValueIsAdjusting()) {
				String selected = results.getSelectedValue();
				if (selected != null) {
					Movie m = findMovie(selected);
					listener.submit(m);
				}
			}
		});

		add(results);
		setBackground(new Color(255, 255, 255));
	}

	public void addResult(ArrayList<Movie> searchResults) {

		model.clear();
		for (Movie m : searchResults) {
			model.addElement(m.getName());
		}
		results.setModel(model);
	}

	public void clearResult() {
		model.removeAllElements();
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
		this.addResult(this.movies);
	}

	private Movie findMovie(String name) {
		for (Movie m : this.movies) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}
}
