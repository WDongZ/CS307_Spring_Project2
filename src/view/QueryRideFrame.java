package view;

import model.DbCtrl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QueryRideFrame extends JFrame {
    private JComboBox<String> queryTypeComboBox;
    private JPanel queryPanel;
    private JPanel resultPanel;
    private DefaultListModel<String> resultListModel;

    public QueryRideFrame() {
        setTitle("Query Ride Frame");
        setSize(600, 800);
        setLocation(560, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));  // Add padding between components

        // Create components
        String[] queryTypes = {"card", "passenger"};
        queryTypeComboBox = new JComboBox<>(queryTypes);
        queryPanel = new JPanel(new GridBagLayout());
        queryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultListModel = new DefaultListModel<>();
        JList<String> resultList = new JList<>(resultListModel);
        resultPanel = new JPanel(new BorderLayout(10, 10));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");

        // Add action listeners
        queryTypeComboBox.addActionListener(e -> updateQueryPanel());

        confirmButton.addActionListener(e -> performQuery());
        exitButton.addActionListener(e -> setVisible(false));

        // Layout the components
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Query Type:"));
        topPanel.add(queryTypeComboBox);

        resultPanel.add(new JScrollPane(resultList), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(confirmButton);
        buttonPanel.add(exitButton);
        resultPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(queryPanel), BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        // Initialize query panel
        updateQueryPanel();
        setVisible(true);
    }

    private void updateQueryPanel() {
        queryPanel.removeAll();
        String selectedType = (String) queryTypeComboBox.getSelectedItem();
        if (selectedType != null) {
            switch (selectedType) {
                case "card":
                    addQueryField("Code", 0);
                    addQueryField("Start Station", 1);
                    addQueryField("End Station", 2);
                    addQueryField("Start Time", 3);
                    addQueryField("End Time", 4);
                    addQueryField("Carriage", 5);
                    break;
                case "passenger":
                    addQueryField("Name", 0);
                    addQueryField("Phone Number", 1);
                    addQueryField("Gender", 2);
                    addQueryField("District", 3);
                    addQueryField("ID", 4);
                    addQueryField("Start Station",5);
                    addQueryField("End Station", 6);
                    addQueryField("Start Time", 7);
                    addQueryField("End Time", 8);
                    addQueryField("Carriage", 9);
                    break;
            }
        }
        queryPanel.revalidate();
        queryPanel.repaint();
    }

    private void addQueryField(String fieldName, int yPos) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JCheckBox checkBox = new JCheckBox(fieldName);
        queryPanel.add(checkBox, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JTextField textField = new JTextField();
        textField.setEnabled(false);
        checkBox.addActionListener(e -> textField.setEnabled(checkBox.isSelected()));

        queryPanel.add(textField, gbc);
    }

    private void performQuery() {
        resultListModel.clear();
        String selectedType = (String) queryTypeComboBox.getSelectedItem();
        if (selectedType != null) {
            switch (selectedType) {
                case "card":
                    String code = queryPanel.getComponent(1).isEnabled() ? ((JTextField) queryPanel.getComponent(1)).getText() : "null";
                    String startStation_c = queryPanel.getComponent(3).isEnabled() ? ((JTextField) queryPanel.getComponent(3)).getText() : "null";
                    String endStation_c = queryPanel.getComponent(5).isEnabled() ? ((JTextField) queryPanel.getComponent(5)).getText() : "null";
                    String startTime_c = queryPanel.getComponent(7).isEnabled() ? ((JTextField) queryPanel.getComponent(7)).getText() : "null";
                    String endTime_c = queryPanel.getComponent(9).isEnabled() ? ((JTextField) queryPanel.getComponent(9)).getText() : "null";
                    String carriage_c = queryPanel.getComponent(11).isEnabled() ? ((JTextField) queryPanel.getComponent(11)).getText() : "null";
                    ArrayList<String> data_c = DbCtrl.queryCardRide(code, startStation_c, endStation_c, startTime_c, endTime_c, carriage_c);
                    for (String s : data_c) {
                        resultListModel.addElement(s);
                    }
                    break;
                case "passenger":
                    String name = queryPanel.getComponent(1).isEnabled() ? ((JTextField) queryPanel.getComponent(1)).getText() : "null";
                    String phoneNumber = queryPanel.getComponent(3).isEnabled() ? ((JTextField) queryPanel.getComponent(3)).getText() : "null";
                    String gender = queryPanel.getComponent(5).isEnabled() ? ((JTextField) queryPanel.getComponent(5)).getText() : "null";
                    String district = queryPanel.getComponent(7).isEnabled() ? ((JTextField) queryPanel.getComponent(7)).getText() : "null";
                    String ID = queryPanel.getComponent(9).isEnabled() ? ((JTextField) queryPanel.getComponent(9)).getText() : "null";
                    String startStation = queryPanel.getComponent(11).isEnabled() ? ((JTextField) queryPanel.getComponent(11)).getText() : "null";
                    String endStation = queryPanel.getComponent(13).isEnabled() ? ((JTextField) queryPanel.getComponent(13)).getText() : "null";
                    String startTime = queryPanel.getComponent(15).isEnabled() ? ((JTextField) queryPanel.getComponent(15)).getText() : "null";
                    String endTime = queryPanel.getComponent(17).isEnabled() ? ((JTextField) queryPanel.getComponent(17)).getText() : "null";
                    String carriage = queryPanel.getComponent(19).isEnabled() ? ((JTextField) queryPanel.getComponent(19)).getText() : "null";
                    ArrayList<String> data = DbCtrl.queryPassengerRide(name, phoneNumber, gender, district, ID, startTime, endTime, carriage, startStation, endStation);
                    for (String s : data) {
                        resultListModel.addElement(s);
                    }
                    break;
            }
        }
    }

}