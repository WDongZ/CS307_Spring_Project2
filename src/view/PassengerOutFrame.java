package view;

import model.DbCtrl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PassengerOutFrame {
    public static void showPassengerOut() {
        JFrame frame = new JFrame("Passenger Out");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel recordPanel = new JPanel();
        GroupLayout layout = new GroupLayout(recordPanel);
        recordPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JRadioButton idCardRadioButton = new JRadioButton("Passenger ID");
        JRadioButton travelCardRadioButton = new JRadioButton("Card ID");
        ButtonGroup cardTypeGroup = new ButtonGroup();
        cardTypeGroup.add(idCardRadioButton);
        cardTypeGroup.add(travelCardRadioButton);

        JLabel stationLabel = new JLabel("Station:");
        JTextField stationTextField = new JTextField(10);
        JLabel IDLabel = new JLabel("ID:");
        JTextField IDTextField = new JTextField(20);
        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(idCardRadioButton)
                                .addComponent(stationLabel)
                        .addComponent(IDLabel)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(travelCardRadioButton)
                                .addComponent(stationTextField)
                                .addComponent(IDTextField)
                                .addComponent(confirmButton)
                                .addComponent(exitButton)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idCardRadioButton)
                                .addComponent(travelCardRadioButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(stationLabel)
                                .addComponent(stationTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(IDLabel)
                                .addComponent(IDTextField))
                        .addComponent(confirmButton)
                        .addComponent(exitButton)
        );

        confirmButton.addActionListener((ActionEvent e) -> {
            String cardType = idCardRadioButton.isSelected() ? "Passenger ID" : "Card ID";
            String station = stationTextField.getText();
            String ID = IDTextField.getText();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(date);
            if (cardType.equals("Passenger ID")) {
                String psInfo = DbCtrl.outPassengerRecord(station, time, ID);
                JOptionPane.showMessageDialog(null, psInfo);
            } else {
                String cdInfo = DbCtrl.outCardRecord(station, time, ID);
                JOptionPane.showMessageDialog(null, cdInfo);
            }
        });

        exitButton.addActionListener(e -> {
            frame.setVisible(false);
        });

        frame.add(recordPanel);
        frame.setSize(600, 400);
        frame.setLocation(560, 300);
        frame.setVisible(true);
    }
}  