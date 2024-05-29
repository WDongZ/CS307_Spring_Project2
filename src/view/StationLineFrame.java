package view;

import model.DbCtrl;

import javax.swing.*;

public class StationLineFrame {
    public static void showStationLine() {
        JFrame frame = new JFrame("Station Lines");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel operationPanel = new JPanel();
        GroupLayout layout = new GroupLayout(operationPanel);
        operationPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JRadioButton addRadioButton = new JRadioButton("Insert");
        JRadioButton deleteRadioButton = new JRadioButton("Delete");
        ButtonGroup operationGroup = new ButtonGroup();
        operationGroup.add(addRadioButton);
        operationGroup.add(deleteRadioButton);

        JLabel metroLineLabel = new JLabel("Metro Line:");
        JTextField metroLineTextField = new JTextField(10);
        JLabel metroStationLabel = new JLabel("Metro Station:");
        JTextField metroStationTextField = new JTextField(10);
        JLabel stationPositionLabel = new JLabel("Station Position:");
        JTextField stationPosition = new JTextField(10);
        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(addRadioButton)
                                .addComponent(metroLineLabel)
                                .addComponent(metroStationLabel)
                                .addComponent(stationPositionLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(deleteRadioButton)
                                .addComponent(metroLineTextField)
                                .addComponent(metroStationTextField)
                                .addComponent(stationPosition)
                                .addComponent(confirmButton)
                                .addComponent(exitButton)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addRadioButton)
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
            }
        });

        exitButton.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(operationPanel);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }


}
