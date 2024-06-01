package view;

import model.DbCtrl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PassengerRecordFrame {
    public static void showPassengerRecord() {
        JFrame frame = new JFrame("Passenger Record");
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

        JLabel carriageLabel = new JLabel("Carriage:");
        JRadioButton normalButton = new JRadioButton("normal");
        JRadioButton businessButton = new JRadioButton("business");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(normalButton);
        statusGroup.add(businessButton);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(idCardRadioButton)
                                .addComponent(stationLabel)
                                .addComponent(IDLabel)
                                .addComponent(carriageLabel)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(travelCardRadioButton)
                                .addComponent(stationTextField)
                                .addComponent(IDTextField)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(normalButton)
                                        .addComponent(businessButton)
                                )
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
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(carriageLabel)
                                .addComponent(normalButton)
                                .addComponent(businessButton))
                        .addComponent(confirmButton)
                        .addComponent(exitButton)
        );

        confirmButton.addActionListener((ActionEvent e) -> {
            String cardType = idCardRadioButton.isSelected() ? "Passenger ID" : "Card ID";
            String station = stationTextField.getText();
            String ID = IDTextField.getText();
            String carriage = normalButton.isSelected() ? "normal" : "business";
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(date);
            if (cardType.equals("Passenger ID")) {
                String psInfo = DbCtrl.passengerRecord(station, time, ID, carriage);
                JOptionPane.showMessageDialog(null, psInfo);
            } else {
                String cdInfo = DbCtrl.cardRecord(station, time, ID, carriage);
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