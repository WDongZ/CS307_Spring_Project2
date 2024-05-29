package view;

import model.DbCtrl;

import javax.swing.*;
import java.awt.event.ActionEvent;
public class QueryPathFrame {
    public static void showPathQuery() {
        JFrame frame = new JFrame("Path Query");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel operationPanel = new JPanel();
        GroupLayout layout = new GroupLayout(operationPanel);
        operationPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel startStationLabel = new JLabel("Start Station:");
        JTextField startStationTextField = new JTextField(10);
        JLabel endStationLabel = new JLabel("End Station:");
        JTextField endStationTextField = new JTextField(10);
        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");
        JTextArea resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(startStationLabel)
                                .addComponent(endStationLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(startStationTextField)
                                .addComponent(endStationTextField)
                                .addComponent(confirmButton)
                                .addComponent(exitButton)
                                .addComponent(resultTextArea)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(startStationLabel)
                                .addComponent(startStationTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(endStationLabel)
                                .addComponent(endStationTextField))
                        .addComponent(confirmButton)
                        .addComponent(exitButton)
                        .addComponent(resultTextArea)
        );

        confirmButton.addActionListener((ActionEvent e) -> {
            String startStation = startStationTextField.getText();
            String endStation = endStationTextField.getText();
            String result = DbCtrl.queryPath(startStation, endStation);
            resultTextArea.setText(result);
        });

        exitButton.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(operationPanel);
        frame.setSize(1500, 400);
        frame.setVisible(true);
    }
}