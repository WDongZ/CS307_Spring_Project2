package model;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SearchStation {
    public static void showStationQuery() {
        JFrame frame = new JFrame("Station Query");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel operationPanel = new JPanel();
        GroupLayout layout = new GroupLayout(operationPanel);
        operationPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JRadioButton forwardRadioButton = new JRadioButton("Count Forward");
        JRadioButton backwardRadioButton = new JRadioButton("Count Backward");
        ButtonGroup directionGroup = new ButtonGroup();
        directionGroup.add(forwardRadioButton);
        directionGroup.add(backwardRadioButton);

        JLabel metroLineLabel = new JLabel("Line:");
        JTextField metroLineTextField = new JTextField(10);
        JLabel metroStationLabel = new JLabel("Station:");
        JTextField metroStationTextField = new JTextField(10);
        JLabel numberLabel = new JLabel("Station Number:");
        JTextField numberTextField = new JTextField(10);
        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");
        JTextArea resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(forwardRadioButton)
                                .addComponent(metroLineLabel)
                                .addComponent(metroStationLabel)
                                .addComponent(numberLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(backwardRadioButton)
                                .addComponent(metroLineTextField)
                                .addComponent(metroStationTextField)
                                .addComponent(numberTextField)
                                .addComponent(confirmButton)
                                .addComponent(exitButton)
                                .addComponent(resultTextArea)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(forwardRadioButton)
                                .addComponent(backwardRadioButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(metroLineLabel)
                                .addComponent(metroLineTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(metroStationLabel)
                                .addComponent(metroStationTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(numberLabel)
                                .addComponent(numberTextField))
                        .addComponent(confirmButton)
                        .addComponent(exitButton)
                        .addComponent(resultTextArea)
        );

        confirmButton.addActionListener((ActionEvent e) -> {
            String metroLine = metroLineTextField.getText();
            String metroStation = metroStationTextField.getText();
            int number = Integer.parseInt(numberTextField.getText());
            if (forwardRadioButton.isSelected()) {
                String result = DbCtrl.queryForwardStation(metroLine, metroStation, number);
                resultTextArea.setText(result);
            } else if (backwardRadioButton.isSelected()) {
                String result = DbCtrl.queryForwardStation(metroLine, metroStation, number * -1);
                resultTextArea.setText(result);
            }
        });

        exitButton.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(operationPanel);
        frame.setSize(600, 400);
        frame.setLocation(560, 300);
        frame.setVisible(true);
    }
}
