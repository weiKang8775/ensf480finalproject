package view;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import model.movie.Ticket;

public class PurchaseHistoryPanel extends JPanel {
    private ArrayList<Ticket> tickets;
    private JList<String> ticketList;
    private JButton removeBtn;
    private DefaultListModel<String> listModel;

    public PurchaseHistoryPanel(ViewCancelTicketListener listener) {
        this.tickets = new ArrayList<>();

        ticketList = new JList<>();
        listModel = new DefaultListModel<>();
        ticketList.setModel(listModel);

        removeBtn = new JButton("Remove");
        removeBtn.addActionListener((e) -> {
            int selected = ticketList.getSelectedIndex();
            if (selected != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this ticket?", "Confirmation", JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    Ticket t = tickets.remove(selected);
                    listModel.removeElementAt(selected);
                    listener.remove(t);
                }
                else {
                    ticketList.clearSelection();
                    removeBtn.setEnabled(false);
                }
            }
        });
        removeBtn.setEnabled(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(removeBtn);
        bottomPanel.setBackground(new Color(255, 255, 255));

        ticketList.addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting()) {
                if (!ticketList.isSelectionEmpty()) {
                    removeBtn.setEnabled(true);
                }
                else {
                    removeBtn.setEnabled(false);
                }
            }
        });

        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));
        add(ticketList, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void addTickets(ArrayList<Ticket> tickets) {
        for (Ticket t : tickets) {
            this.listModel.addElement(t.getMovieName());
            this.tickets.add(t);
        }
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets.clear();
        listModel.clear();
        for (Ticket t : tickets) {
            this.tickets.add(t);
            listModel.addElement(t.getMovieName());
        }
    }
}
