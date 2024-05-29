package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyLines {
    public static void ModifyLine() {
        JFrame frame = new JFrame("Modify Lines");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SubwayManagementPanel panel = new SubwayManagementPanel();
        frame.add(panel);
        setExitButton(panel.exitButton, frame);
        frame.setVisible(true);
    }
    private static void setExitButton(JButton exitButton, JFrame frame) {
        exitButton.addActionListener(e -> {
            frame.setVisible(false);
        });
    }

    static class SubwayManagementPanel extends JPanel {
        private String[] actions = {"Insert", "Modify", "Delete"};
        private JComboBox<String> actionComboBox;
        private JTextField textField1;
        private JTextField textField2;
        private JTextField textField3;
        private JTextField textField4;
        private JTextField textField5;
        private JTextField textField6;
        private JTextField textField7;
        private JTextField textField8;
        private JTextField textField9;
        private JButton confirmButton;
        private JButton exitButton;

        public SubwayManagementPanel() {
            setLayout(new GridLayout(0, 2));

            // Dropdown for selecting action
            actionComboBox = new JComboBox<>(actions);
            add(new JLabel("Select Action:"));
            add(actionComboBox);

            // Text fields for action parameters
            textField1 = new JTextField(20);
            textField2 = new JTextField(20);
            textField3 = new JTextField(20);
            textField4 = new JTextField(20);
            textField5 = new JTextField(20);
            textField6 = new JTextField(20);
            textField7 = new JTextField(20);
            textField8 = new JTextField(20);
            textField9 = new JTextField(20);

            // Hide all text fields initially
            textField1.setVisible(false);
            textField2.setVisible(false);
            textField3.setVisible(false);
            textField4.setVisible(false);
            textField5.setVisible(false);
            textField6.setVisible(false);
            textField7.setVisible(false);
            textField8.setVisible(false);
            textField9.setVisible(false);

            // Action listener for the dropdown
            actionComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedAction = (String) actionComboBox.getSelectedItem();
                    if (selectedAction.equals("Modify")) {
                        textField1.setVisible(true);
                        textField2.setVisible(true);
                        textField3.setVisible(true);
                        textField4.setVisible(true);
                        textField5.setVisible(true);
                        textField6.setVisible(true);
                        textField7.setVisible(true);
                        textField8.setVisible(true);
                        textField9.setVisible(true);
                    } else if (selectedAction.equals("Insert")) {
                        textField1.setVisible(false);
                        textField2.setVisible(true);
                        textField3.setVisible(true);
                        textField4.setVisible(true);
                        textField5.setVisible(true);
                        textField6.setVisible(true);
                        textField7.setVisible(true);
                        textField8.setVisible(true);
                        textField9.setVisible(true);
                    } else if (selectedAction.equals("Delete")) {
                        textField1.setVisible(false);
                        textField2.setVisible(true);
                        textField3.setVisible(false);
                        textField4.setVisible(false);
                        textField5.setVisible(false);
                        textField6.setVisible(false);
                        textField7.setVisible(false);
                        textField8.setVisible(false);
                        textField9.setVisible(false);
                    } else {
                        // Handle other actions if needed
                    }
                    revalidate();
                    repaint();
                }
            });

            // Add components based on the selected action
            add(new JLabel("Modify Line:"));
            add(textField1);
            add(new JLabel("Line Name:"));
            add(textField2);
            add(new JLabel("Start Time:"));
            add(textField3);
            add(new JLabel("End Time:"));
            add(textField4);
            add(new JLabel("Intro:"));
            add(textField5);
            add(new JLabel("Mileage:"));
            add(textField6);
            add(new JLabel("Color:"));
            add(textField7);
            add(new JLabel("First Opening:"));
            add(textField8);
            add(new JLabel("URL:"));
            add(textField9);


            // Confirmation button
            confirmButton = new JButton("Confirm");
            add(confirmButton);
            exitButton = new JButton("Exit");
            add(exitButton);

            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedAction = (String) actionComboBox.getSelectedItem();
                    String modifyLine = textField1.getText();
                    String lineName = textField2.getText();
                    String startTime = textField3.getText();
                    String endTime = textField4.getText();
                    String intro = textField5.getText();
                    String mileage = textField6.getText();
                    String color = textField7.getText();
                    String firstOpening = textField8.getText();
                    String url = textField9.getText();
                    switch (selectedAction) {
                        case "Insert":
                            String infoInsert = DbCtrl.insertLine(new DbCtrl.Line(lineName, startTime, endTime, intro, mileage, color, firstOpening, url));
                            JOptionPane.showMessageDialog(null, infoInsert);
                            break;
                        case "Modify":
                            String infoModify = DbCtrl.modifyLine(modifyLine, new DbCtrl.Line(lineName, startTime, endTime, intro, mileage, color, firstOpening, url));
                            JOptionPane.showMessageDialog(null, infoModify);
                            break;
                        case "Delete":
                            String infoDelete = DbCtrl.deleteLine(lineName);
                            JOptionPane.showMessageDialog(null, infoDelete);
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

}
