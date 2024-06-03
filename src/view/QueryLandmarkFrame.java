package view;

import model.DbCtrl;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class QueryLandmarkFrame {
    public static void showQueryLandmark() {
        JFrame frame = new JFrame("Query Landmark");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel recordPanel = new JPanel();
        GroupLayout layout = new GroupLayout(recordPanel);
        recordPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel landmarkLabel = new JLabel("Landmark:");
        JTextField landmarkTextField = new JTextField(20);
        JList<String> landmarkList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(landmarkList);
        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(landmarkLabel)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(landmarkTextField)
                                .addComponent(scrollPane)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(confirmButton)
                                        .addComponent(exitButton)
                                )
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(landmarkLabel)
                                .addComponent(landmarkTextField))
                        .addComponent(scrollPane)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(confirmButton)
                                .addComponent(exitButton)
                        )
        );

        confirmButton.addActionListener((ActionEvent e) -> {
            String landmark = landmarkTextField.getText();
            ArrayList<String> landmarkData = DbCtrl.queryLandmark(landmark);
            DefaultListModel<String> landmarkListModel = new DefaultListModel<>();
            for (String data : landmarkData) {
                landmarkListModel.addElement(data);
            }
            landmarkList.setModel(landmarkListModel);
        });

        exitButton.addActionListener(e -> frame.setVisible(false));

        frame.add(recordPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        frame.setVisible(true);
    }
}