package model;

import javax.swing.*;
import java.awt.*;

public class ModifyStations {
    public static void ModifyStation() {
        JFrame frame = new JFrame("Modify Stations");
        frame.setSize(600, 400);
        frame.setLocation(560, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each tab
        JPanel modifyPanel = createModifyPanel();
        JPanel insertPanel = createInsertPanel();
        JPanel deletePanel = createDeletePanel();

        // Add each panel to the tabbed pane with a title
        tabbedPane.addTab("Modify", modifyPanel);
        tabbedPane.addTab("Insert", insertPanel);
        tabbedPane.addTab("Delete", deletePanel);

        // Add the tabbed pane to the frame
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Add exit button
        JButton exitButton = new JButton("Exit");
        frame.add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(e -> frame.setVisible(false));

        frame.setVisible(true);
    }

    private static JPanel createModifyPanel() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel stationNameLabel = new JLabel("Station Name:");
        JTextField modifyStationField = new JTextField(20);
        JLabel districtLabel = new JLabel("District:");
        JTextField districtField = new JTextField(20);
        JLabel introLabel = new JLabel("Introduction:");
        JTextField introField = new JTextField(20);
        JLabel englishNameLabel = new JLabel("English Name:");
        JTextField englishNameField = new JTextField(20);
        JLabel chineseNameLabel = new JLabel("Chinese Name:");
        JTextField chineseNameField = new JTextField(20);
        JButton confirmModifyButton = new JButton("Confirm");

        JLabel statusLabel = new JLabel("Status:");
        JRadioButton operatingButton = new JRadioButton("运营中");
        JRadioButton constructingButton = new JRadioButton("建设中");
        JRadioButton closedButton = new JRadioButton("关闭中");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(operatingButton);
        statusGroup.add(constructingButton);
        statusGroup.add(closedButton);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(stationNameLabel)
                        .addComponent(districtLabel)
                        .addComponent(introLabel)
                        .addComponent(englishNameLabel)
                        .addComponent(chineseNameLabel)
                        .addComponent(statusLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(modifyStationField)
                        .addComponent(districtField)
                        .addComponent(introField)
                        .addComponent(englishNameField)
                        .addComponent(chineseNameField)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(operatingButton)
                                .addComponent(constructingButton)
                                .addComponent(closedButton))
                        .addComponent(confirmModifyButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(stationNameLabel)
                        .addComponent(modifyStationField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(districtLabel)
                        .addComponent(districtField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(introLabel)
                        .addComponent(introField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(englishNameLabel)
                        .addComponent(englishNameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chineseNameLabel)
                        .addComponent(chineseNameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(statusLabel)
                        .addComponent(operatingButton)
                        .addComponent(constructingButton)
                        .addComponent(closedButton))
                .addComponent(confirmModifyButton));

        confirmModifyButton.addActionListener(e -> {
            String stationName = modifyStationField.getText();
            String district = districtField.getText();
            String intro = introField.getText();
            String englishName = englishNameField.getText();
            String chineseName = chineseNameField.getText();
            String status = operatingButton.isSelected() ? "运营中" : constructingButton.isSelected() ? "建设中" : "关闭中";
            String result = DbCtrl.modifyStation(stationName, new DbCtrl.Station(district, intro, chineseName, englishName, status));
            JOptionPane.showMessageDialog(null, result);
        });

        return panel;
    }

    private static JPanel createInsertPanel() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel districtLabel = new JLabel("District:");
        JTextField districtField = new JTextField(20);
        JLabel introLabel = new JLabel("Introduction:");
        JTextField introField = new JTextField(20);
        JLabel englishNameLabel = new JLabel("English Name:");
        JTextField englishNameField = new JTextField(20);
        JLabel chineseNameLabel = new JLabel("Chinese Name:");
        JTextField chineseNameField = new JTextField(20);
        JButton confirmInsertButton = new JButton("Confirm");

        JLabel statusLabel = new JLabel("Status:");
        JRadioButton operatingButton = new JRadioButton("运营中");
        JRadioButton constructingButton = new JRadioButton("建设中");
        JRadioButton closedButton = new JRadioButton("关闭中");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(operatingButton);
        statusGroup.add(constructingButton);
        statusGroup.add(closedButton);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(districtLabel)
                        .addComponent(introLabel)
                        .addComponent(englishNameLabel)
                        .addComponent(chineseNameLabel)
                        .addComponent(statusLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(districtField)
                        .addComponent(introField)
                        .addComponent(englishNameField)
                        .addComponent(chineseNameField)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(operatingButton)
                                .addComponent(constructingButton)
                                .addComponent(closedButton))
                        .addComponent(confirmInsertButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(districtLabel)
                        .addComponent(districtField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(introLabel)
                        .addComponent(introField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(englishNameLabel)
                        .addComponent(englishNameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chineseNameLabel)
                        .addComponent(chineseNameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(statusLabel)
                        .addComponent(operatingButton)
                        .addComponent(constructingButton)
                        .addComponent(closedButton))
                .addComponent(confirmInsertButton));

        confirmInsertButton.addActionListener(e -> {
            String district = districtField.getText();
            String intro = introField.getText();
            String englishName = englishNameField.getText();
            String chineseName = chineseNameField.getText();
            String status = operatingButton.isSelected() ? "运营中" : constructingButton.isSelected() ? "建设中" : "关闭中";
            String result = DbCtrl.insertStation(new DbCtrl.Station(district, intro, chineseName, englishName, status));
            JOptionPane.showMessageDialog(null, result);
        });

        return panel;
    }

    private static JPanel createDeletePanel() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel stationNameLabel = new JLabel("Station Name:");
        JTextField deleteStationField = new JTextField(20);
        JButton confirmDeleteButton = new JButton("Confirm");

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(stationNameLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(deleteStationField)
                        .addComponent(confirmDeleteButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(stationNameLabel)
                        .addComponent(deleteStationField))
                .addComponent(confirmDeleteButton));

        confirmDeleteButton.addActionListener(e -> {
            String stationName = deleteStationField.getText();
            String result = DbCtrl.deleteStation(stationName);
            JOptionPane.showMessageDialog(null, result);
        });

        return panel;
    }

}
