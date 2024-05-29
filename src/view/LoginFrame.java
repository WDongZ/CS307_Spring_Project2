package view;

import javax.swing.*;
import java.awt.*;

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
        loginButton.setBounds(130, 140, 120, 40);
        loginButton.setFont(new Font("Rockwell", Font.BOLD, 16));
        panel.add(loginButton);
    }

    private static void waitForLogin(JPanel panel, JFrame frame) {
        JButton loginButton = (JButton) panel.getComponent(4);
        loginButton.addActionListener(e -> {
            String username = ((JTextField)panel.getComponent(1)).getText();
            String password = ((JPasswordField)panel.getComponent(3)).getText();
//            if (username.equals("")){
//                JOptionPane.showMessageDialog(null, createMessage("请输入账户"), "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            } else if (password.length() == 0){
//                JOptionPane.showMessageDialog(null, createMessage("请输入密码"), "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            if (username.equals("") && password.equals("")) {
                User u = new User(username, password, Role.ADMIN);
                JOptionPane.showMessageDialog(null, createMessage("登录成功！"));
                frame.setVisible(false);
                UserFrame userFrame = new UserFrame();
                userFrame.SubwayManagementUI(u);
            } else {
                JOptionPane.showMessageDialog(null, createMessage("登录失败！"));
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
