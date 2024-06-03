package view;

import model.DbCtrl;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class LoginFrame extends JFrame{
    public static void login() {
        JFrame frame = new JFrame("登录");
        frame.setSize(380, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setLocation(760, 360);
        frame.setVisible(true);
        waitForLogin(panel, frame);
    }


    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel userLabel = new JLabel("用户名");
        userLabel.setFont(new Font("", Font.BOLD, 20));
        userLabel.setBounds(35, 30, 80, 40);
        panel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(110, 35, 200, 30);
        panel.add(userText);
        JLabel passwordLabel = new JLabel("密码");
        passwordLabel.setFont(new Font("", Font.BOLD, 20));
        passwordLabel.setBounds(55, 80, 50, 40);
        panel.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(110, 85, 200, 30);
        panel.add(passwordText);
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(40, 140, 120, 40);
        loginButton.setFont(new Font("宋体", Font.BOLD, 16));
        panel.add(loginButton);
        JButton regButton = new JButton("注册");
        regButton.setBounds(200, 140, 120, 40);
        regButton.setFont(new Font("宋体", Font.BOLD, 16));
        panel.add(regButton);
    }

    private static void waitForLogin(JPanel panel, JFrame frame) {
        JButton loginButton = (JButton) panel.getComponent(4);
        loginButton.addActionListener(e -> {
            String username = ((JTextField)panel.getComponent(1)).getText();
            String password = ((JPasswordField)panel.getComponent(3)).getText();
            try {
                String role = DbCtrl.login(username, password);
                if (Objects.equals(role, "null")) {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "登录成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    User user = new User(username, password, Role.valueOf(role));
                    DbCtrl.getConnect(user);
                    frame.setVisible(false);
                    UserFrame userFrame = new UserFrame();
                    userFrame.SubwayManagementUI(user);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton regButton = (JButton) panel.getComponent(5);
        regButton.addActionListener(e -> {
            String username = ((JTextField)panel.getComponent(1)).getText();
            String password = ((JPasswordField)panel.getComponent(3)).getText();
            try {
                String result = DbCtrl.register(username, password);
                JOptionPane.showMessageDialog(null, result);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static class User {
        public String username;
        public String password;
        public Role role;

        public User(String username, String password, Role role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }
        public String getRole() {
            return role.toString();
        }

    }

    public enum Role {
        ADMIN, USER
    }
    static JLabel createMessage(String str){
        JLabel passwordLabel = new JLabel(str);
        passwordLabel.setFont(new Font("", Font.BOLD, 16));
        return passwordLabel;
    }
}
