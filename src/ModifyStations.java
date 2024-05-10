import javax.swing.*;
import java.awt.*;

public class ModifyStations {
    public static void ModifyStation() {
        JFrame frame = new JFrame("Modify Stations");
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
        private final JComboBox<String> actionComboBox;
        private final JTextField textField1;
        private final JTextField textField2;
        private final JTextField textField3;
        private final JTextField textField4;
        private final JTextField textField5;
        private final JButton exitButton;

        public SubwayManagementPanel() {
            setLayout(new GridLayout(0, 2));

            // Dropdown for selecting action
            String[] actions = {"Insert", "Modify", "Delete"};
            actionComboBox = new JComboBox<>(actions);
            add(new JLabel("Select Action:"));
            add(actionComboBox);

            // Text fields for action parameters
            textField1 = new JTextField(20);
            textField2 = new JTextField(20);
            textField3 = new JTextField(20);
            textField4 = new JTextField(20);
            textField5 = new JTextField(20);

            // Hide all text fields initially
            textField1.setVisible(false);
            textField2.setVisible(false);
            textField3.setVisible(false);
            textField4.setVisible(false);
            textField5.setVisible(false);

            // Action listener for the dropdown
            actionComboBox.addActionListener(e -> {
                String selectedAction = (String) actionComboBox.getSelectedItem();
                if (selectedAction != null) {
                    switch (selectedAction) {
                        case "Modify" -> {
                            textField1.setVisible(true);
                            textField2.setVisible(true);
                            textField3.setVisible(true);
                            textField4.setVisible(true);
                            textField5.setVisible(true);
                        }
                        case "Insert" -> {
                            textField1.setVisible(false);
                            textField2.setVisible(true);
                            textField3.setVisible(true);
                            textField4.setVisible(true);
                            textField5.setVisible(true);
                        }
                        case "Delete" -> {
                            textField1.setVisible(false);
                            textField2.setVisible(false);
                            textField3.setVisible(false);
                            textField4.setVisible(true);
                            textField5.setVisible(false);
                        }
                        default -> {
                            // Handle other actions if needed
                        }
                    }
                }
                revalidate();
                repaint();
            });

            // Add components based on the selected action
            add(new JLabel("Station Name:"));
            add(textField1);
            add(new JLabel("District:"));
            add(textField2);
            add(new JLabel("Intro:"));
            add(textField3);
            add(new JLabel("Chinese name:"));
            add(textField4);
            add(new JLabel("English name"));
            add(textField5);

            // Confirmation button
            JButton confirmButton = new JButton("Confirm");
            add(confirmButton);
            exitButton = new JButton("Exit");
            add(exitButton);

            confirmButton.addActionListener(e -> {
                String selectedAction = (String) actionComboBox.getSelectedItem();
                String modifyStation = textField1.getText();
                String district = textField2.getText();
                String intro = textField3.getText();
                String chineseName = textField4.getText();
                String englishName = textField5.getText();
                if (selectedAction != null) {
                    switch (selectedAction) {
                        case "Insert":
                            String infoInsert = DbCtrl.insertStation(new DbCtrl.Station(district, intro, chineseName, englishName));
                            JOptionPane.showMessageDialog(null, infoInsert);
                            break;
                        case "Modify":
                            String infoModify = DbCtrl.modifyStation(modifyStation, new DbCtrl.Station(district, intro, chineseName, englishName));
                            JOptionPane.showMessageDialog(null, infoModify);
                            break;
                        case "Delete":
                            String infoDelete = DbCtrl.deleteStation(chineseName);
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
