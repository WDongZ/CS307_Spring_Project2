package model;

import javax.swing.*;
import java.awt.*;

public class ModifyLines {
    public static void ModifyLine() {
        JFrame frame = new JFrame("Modify Lines");
        frame.setSize(600, 400);
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

        JLabel modifyLineLabel = new JLabel("Modify Line:");
        JTextField modifyLineField = new JTextField(20);
        JLabel lineNameLabel = new JLabel("Line Name:");
        JTextField lineNameField = new JTextField(20);
        JLabel startTimeLabel = new JLabel("Start Time:");
        JTextField startTimeField = new JTextField(20);
        JLabel endTimeLabel = new JLabel("End Time:");
        JTextField endTimeField = new JTextField(20);
        JLabel introLabel = new JLabel("Intro:");
        JTextField introField = new JTextField(20);
        JLabel mileageLabel = new JLabel("Mileage:");
        JTextField mileageField = new JTextField(20);
        JLabel colorLabel = new JLabel("Color:");
        JTextField colorField = new JTextField(20);
        JLabel firstOpeningLabel = new JLabel("First Opening:");
        JTextField firstOpeningField = new JTextField(20);
        JLabel urlLabel = new JLabel("URL:");
        JTextField urlField = new JTextField(20);
        JButton confirmModifyButton = new JButton("Confirm");

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(modifyLineLabel)
                        .addComponent(lineNameLabel)
                        .addComponent(startTimeLabel)
                        .addComponent(endTimeLabel)
                        .addComponent(introLabel)
                        .addComponent(mileageLabel)
                        .addComponent(colorLabel)
                        .addComponent(firstOpeningLabel)
                        .addComponent(urlLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(modifyLineField)
                        .addComponent(lineNameField)
                        .addComponent(startTimeField)
                        .addComponent(endTimeField)
                        .addComponent(introField)
                        .addComponent(mileageField)
                        .addComponent(colorField)
                        .addComponent(firstOpeningField)
                        .addComponent(urlField)
                        .addComponent(confirmModifyButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(modifyLineLabel)
                        .addComponent(modifyLineField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lineNameLabel)
                        .addComponent(lineNameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(startTimeLabel)
                        .addComponent(startTimeField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(endTimeLabel)
                        .addComponent(endTimeField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(introLabel)
                        .addComponent(introField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mileageLabel)
                        .addComponent(mileageField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(colorLabel)
                        .addComponent(colorField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(firstOpeningLabel)
                        .addComponent(firstOpeningField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(urlLabel)
                        .addComponent(urlField))
                .addComponent(confirmModifyButton));

        confirmModifyButton.addActionListener(e -> {
            String modifyLine = modifyLineField.getText();
            String lineName = lineNameField.getText();
            String startTime = startTimeField.getText();
            String endTime = endTimeField.getText();
            String intro = introField.getText();
            String mileage = mileageField.getText();
            String color = colorField.getText();
            String firstOpening = firstOpeningField.getText();
            String url = urlField.getText();
            String result = DbCtrl.modifyLine(modifyLine, new DbCtrl.Line(lineName, startTime, endTime, intro, mileage, color, firstOpening, url));
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

        JLabel lineNameLabel = new JLabel("Line Name:");
        JTextField lineNameField = new JTextField(20);
        JLabel startTimeLabel = new JLabel("Start Time:");
        JTextField startTimeField = new JTextField(20);
        JLabel endTimeLabel = new JLabel("End Time:");
        JTextField endTimeField = new JTextField(20);
        JLabel introLabel = new JLabel("Intro:");
        JTextField introField = new JTextField(20);
        JLabel mileageLabel = new JLabel("Mileage:");
        JTextField mileageField = new JTextField(20);
        JLabel colorLabel = new JLabel("Color:");
        JTextField colorField = new JTextField(20);
        JLabel firstOpeningLabel = new JLabel("First Opening:");
        JTextField firstOpeningField = new JTextField(20);
        JLabel urlLabel = new JLabel("URL:");
        JTextField urlField = new JTextField(20);
        JButton confirmInsertButton = new JButton("Confirm");

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lineNameLabel)
                        .addComponent(startTimeLabel)
                        .addComponent(endTimeLabel)
                        .addComponent(introLabel)
                        .addComponent(mileageLabel)
                        .addComponent(colorLabel)
                        .addComponent(firstOpeningLabel)
                        .addComponent(urlLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lineNameField)
                        .addComponent(startTimeField)
                        .addComponent(endTimeField)
                        .addComponent(introField)
                        .addComponent(mileageField)
                        .addComponent(colorField)
                        .addComponent(firstOpeningField)
                        .addComponent(urlField)
                        .addComponent(confirmInsertButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lineNameLabel)
                        .addComponent(lineNameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(startTimeLabel)
                        .addComponent(startTimeField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(endTimeLabel)
                        .addComponent(endTimeField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(introLabel)
                        .addComponent(introField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mileageLabel)
                        .addComponent(mileageField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(colorLabel)
                        .addComponent(colorField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(firstOpeningLabel)
                        .addComponent(firstOpeningField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(urlLabel)
                        .addComponent(urlField))
                .addComponent(confirmInsertButton));

        confirmInsertButton.addActionListener(e -> {
            String lineName = lineNameField.getText();
            String startTime = startTimeField.getText();
            String endTime = endTimeField.getText();
            String intro = introField.getText();
            String mileage = mileageField.getText();
            String color = colorField.getText();
            String firstOpening = firstOpeningField.getText();
            String url = urlField.getText();
            String result = DbCtrl.insertLine(new DbCtrl.Line(lineName, startTime, endTime, intro, mileage, color, firstOpening, url));
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

        JLabel lineNameLabel = new JLabel("Line Name:");
        JTextField lineNameField = new JTextField(20);
        JButton confirmDeleteButton = new JButton("Confirm");

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(lineNameLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lineNameField)
                        .addComponent(confirmDeleteButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lineNameLabel)
                        .addComponent(lineNameField))
                .addComponent(confirmDeleteButton));

        confirmDeleteButton.addActionListener(e -> {
            String lineName = lineNameField.getText();
            String result = DbCtrl.deleteLine(lineName);
            JOptionPane.showMessageDialog(null, result);
        });

        return panel;
    }

}
