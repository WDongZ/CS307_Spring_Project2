package view;

import model.DbCtrl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PassengerQueryFrame extends JFrame {

    public PassengerQueryFrame() {
        setTitle("Passenger Information");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JList<String> passengerList = new JList<>();
        JScrollPane passengerScrollPane = new JScrollPane(passengerList);
        tabbedPane.addTab("Passenger Information", passengerScrollPane);
        ArrayList<String> passengerData = DbCtrl.queryPassenger();
        DefaultListModel<String> passengerListModel = new DefaultListModel<>();
        for (String data : passengerData) {
            passengerListModel.addElement(data);
        }
        passengerList.setModel(passengerListModel);

        JList<String> cardList = new JList<>();
        JScrollPane cardScrollPane = new JScrollPane(cardList);
        tabbedPane.addTab("Card Information", cardScrollPane);
        ArrayList<String> cardData = DbCtrl.queryCard();
        DefaultListModel<String> cardListModel = new DefaultListModel<>();
        for (String data : cardData) {
            cardListModel.addElement(data);
        }
        cardList.setModel(cardListModel);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> setVisible(false));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton);

        add(tabbedPane);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}  