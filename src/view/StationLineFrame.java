package view;

import model.DbCtrl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StationLineFrame {
    public static void showStationLine() {
        JFrame frame = new JFrame("Station Lines");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel operationPanel = new JPanel();
        GroupLayout layout = new GroupLayout(operationPanel);
        operationPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JRadioButton addRadioButton = new JRadioButton("Insert Single");
        JRadioButton addMultiButton = new JRadioButton("Insert Multiple");
        JRadioButton deleteRadioButton = new JRadioButton("Delete");
        ButtonGroup operationGroup = new ButtonGroup();
        operationGroup.add(addRadioButton);
        operationGroup.add(addMultiButton);
        operationGroup.add(deleteRadioButton);

        JLabel metroLineLabel = new JLabel("Metro Line:");
        JTextField metroLineTextField = new JTextField(10);
        JLabel metroStationLabel = new JLabel("Metro Station:");
        JTextField metroStationTextField = new JTextField(10);
        JLabel stationPositionLabel = new JLabel("Station Position:");
        JTextField stationPosition = new JTextField(10);
        JLabel stationNumberLabel = new JLabel("Station Number:");
        JTextField stationNumber = new JTextField(10);
        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");

        deleteRadioButton.addActionListener(e -> {
            metroStationLabel.setVisible(true);
            metroStationTextField.setVisible(true);
            stationPosition.setVisible(false);
            stationPositionLabel.setVisible(false);
            stationNumber.setVisible(false);
            stationNumberLabel.setVisible(false);
        });

        addRadioButton.addActionListener(e -> {
            metroStationLabel.setVisible(true);
            metroStationTextField.setVisible(true);
            stationPosition.setVisible(true);
            stationPositionLabel.setVisible(true);
            stationNumber.setVisible(false);
            stationNumberLabel.setVisible(false);
        });

        addMultiButton.addActionListener(e -> {
            metroStationLabel.setVisible(false);
            metroStationTextField.setVisible(false);
            stationPosition.setVisible(true);
            stationPositionLabel.setVisible(true);
            stationNumber.setVisible(true);
            stationNumberLabel.setVisible(true);
        });

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(addRadioButton)
                                .addComponent(metroLineLabel)
                                .addComponent(metroStationLabel)
                                .addComponent(stationPositionLabel)
                                .addComponent(stationNumberLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(deleteRadioButton)
                                .addComponent(metroLineTextField)
                                .addComponent(metroStationTextField)
                                .addComponent(stationPosition)
                                .addComponent(stationNumber)
                                .addComponent(confirmButton)
                                .addComponent(exitButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(addMultiButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addRadioButton)
                                .addComponent(addMultiButton)
                                .addComponent(deleteRadioButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(metroLineLabel)
                                .addComponent(metroLineTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(metroStationLabel)
                                .addComponent(metroStationTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(stationPositionLabel)
                                .addComponent(stationPosition))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(stationNumberLabel)
                                .addComponent(stationNumber))
                        .addComponent(confirmButton)
                        .addComponent(exitButton)
        );

        confirmButton.addActionListener(e -> {
            String metroLine = metroLineTextField.getText();
            String metroStation = metroStationTextField.getText();
            String position = stationPosition.getText();
            if (addRadioButton.isSelected()) {
                String insertInfo = DbCtrl.insertStationLine(metroLine, metroStation, Integer.parseInt(position));
                JOptionPane.showMessageDialog(null, insertInfo);
            } else if (deleteRadioButton.isSelected()) {
                String deleteInfo = DbCtrl.deleteStationLine(metroLine, metroStation);
                JOptionPane.showMessageDialog(null, deleteInfo);
            } else if (addMultiButton.isSelected()) {
                String stationNum = stationNumber.getText();
                int numStations = Integer.parseInt(stationNum);
                ArrayList<String> stations = new ArrayList<>();
                for (int i = 0; i < numStations; i++) {
                    String stationName = JOptionPane.showInputDialog(frame, "Enter station name " + (i + 1) + ":");
                    stations.add(stationName);
                }
                StringBuilder stationList = new StringBuilder();
                for (String s : stations) {
                    stationList.append(DbCtrl.insertStationLine(metroLine, s, Integer.parseInt(position))).append("\n");
                }
                JOptionPane.showMessageDialog(null, stationList);
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
