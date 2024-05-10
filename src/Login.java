import javax.swing.*;

public class Login {
    public static void login() {

        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
        waitForLogin(panel, frame);
    }


    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }

    private static void waitForLogin(JPanel panel, JFrame frame) {
        JButton loginButton = (JButton) panel.getComponent(4);
        loginButton.addActionListener(e -> {
            String user = ((JTextField)panel.getComponent(1)).getText();
            String password = ((JPasswordField)panel.getComponent(3)).getText();
            if (user.equals("checker") && password.equals("123456")) {
                User u = new User(user, password, Role.ADMIN);
                JOptionPane.showMessageDialog(null, "Login successful");
                frame.setVisible(false);
                UserFrame userFrame = new UserFrame();
                userFrame.SubwayManagementUI(u);
            } else {
                JOptionPane.showMessageDialog(null, "Login failed");
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

}
