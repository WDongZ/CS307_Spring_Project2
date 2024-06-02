package view;

import model.DbCtrl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class QueryBusFrame {
    public static void showBusQuery() {
        JFrame frame = new JFrame("Bus Query");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel operationPanel = new JPanel();
        GroupLayout layout = new GroupLayout(operationPanel);
        operationPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel startStationLabel = new JLabel("Station:");
        JTextField startStationTextField = new JTextField(10);
        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");
        DefaultListModel<String> resultListModel = new DefaultListModel<>();
        JList<String> resultList = new JList<>(resultListModel);
        JScrollPane resultScrollPane = new JScrollPane(resultList);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(startStationLabel)
                                .addComponent(confirmButton)
                                .addComponent(exitButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(startStationTextField)
                                .addComponent(resultScrollPane))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(startStationLabel)
                                .addComponent(startStationTextField))
                        .addGap(20)
                        .addComponent(confirmButton)
                        .addComponent(exitButton)
                        .addGap(20)
                        .addComponent(resultScrollPane)
        );

        confirmButton.addActionListener((ActionEvent e) -> {
            resultListModel.clear();
            String station = startStationTextField.getText();
            ArrayList<String> result = DbCtrl.queryBus(station);
            for (String s : result) {
                resultListModel.addElement(s);
            }
        });

        exitButton.addActionListener(e -> frame.dispose());

        frame.add(operationPanel);
        frame.setSize(800, 400);
        frame.setLocation(560, 300);
        frame.setVisible(true);
    }

}
