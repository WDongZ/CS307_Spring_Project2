package view;
import model.DbCtrl;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class ModifyPasswordFrame {
    public static void showModifyPassword(LoginFrame.User user) {
        JFrame frame = new JFrame("Modify Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel operationPanel = new JPanel();
        GroupLayout layout = new GroupLayout(operationPanel);
        operationPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        JPasswordField oldPasswordField = new JPasswordField(20);
        JLabel newPasswordLabel = new JLabel("New Password:");
        JPasswordField newPasswordField = new JPasswordField(20);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);
        JButton confirmButton = new JButton("Confirm");
        JButton exitButton = new JButton("Exit");

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(oldPasswordLabel)
                                .addComponent(newPasswordLabel)
                                .addComponent(confirmPasswordLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(oldPasswordField)
                                .addComponent(newPasswordField)
                                .addComponent(confirmPasswordField)
                                .addComponent(confirmButton)
                                .addComponent(exitButton)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(oldPasswordLabel)
                                .addComponent(oldPasswordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(newPasswordLabel)
                                .addComponent(newPasswordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(confirmPasswordLabel)
                                .addComponent(confirmPasswordField))
                        .addComponent(confirmButton)
                        .addComponent(exitButton)
        );

        confirmButton.addActionListener((ActionEvent e) -> {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            if (newPassword.equals(confirmPassword)) {
                String result = DbCtrl.modifyPassword(user.username ,oldPassword, newPassword);
                JOptionPane.showMessageDialog(frame, result);
                if (result.equals("Password modified successfully")) {
                    frame.dispose();
                    try {
                        DbCtrl.disconnect();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "New password and confirm password do not match!");
            }
        });

        exitButton.addActionListener(e -> frame.dispose());

        frame.add(operationPanel);
        frame.pack();
        frame.setLocation(560, 300);
        frame.setVisible(true);
    }
}
