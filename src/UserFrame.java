import javax.swing.*;
import java.awt.*;

public class UserFrame extends JFrame {

    public void SubwayManagementUI(Login.User user) {
        setTitle("Subway Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 用户信息
        JLabel userInfoLabel = new JLabel("User: " + user.username + "\n权限:" + user.getRole(), SwingConstants.LEFT);
        add(userInfoLabel, BorderLayout.NORTH);

        // 按钮
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));

        JButton modifyStationButton = new JButton("修改地铁站");
        JButton modifyLineButton = new JButton("修改地铁线");
        JButton modifyStationAndLineButton = new JButton("地铁站和地铁线");
        JButton searchStationButton = new JButton("查询站点");
        JButton passengerBoardButton = new JButton("乘客上车");
        JButton passengerDisembarkButton = new JButton("乘客下车");
        JButton searchPassengerButton = new JButton("查询乘客");


        buttonPanel.add(modifyStationButton);
        buttonPanel.add(modifyLineButton);
        buttonPanel.add(modifyStationAndLineButton);
        buttonPanel.add(searchStationButton);
        buttonPanel.add(passengerBoardButton);
        buttonPanel.add(passengerDisembarkButton);
        buttonPanel.add(searchPassengerButton);

        modifyStationButton.addActionListener(e -> {
            ModifyStations.ModifyStation();
        });

        modifyLineButton.addActionListener(e -> {
            ModifyLines.ModifyLine();
        });

        modifyStationAndLineButton.addActionListener(e -> {
            StationLine.showStationLine();
        });

        searchStationButton.addActionListener(e -> {
            SearchStation.showStationQuery();
        });

        passengerBoardButton.addActionListener(e -> {
            PassengerRecordFrame.showPassengerRecord();
        });

        passengerDisembarkButton.addActionListener(e -> {
            PassengerOutFrame.showPassengerOut();
        });

        searchPassengerButton.addActionListener(e -> {
            new PassengerQueryFrame();
        });

        add(buttonPanel, BorderLayout.CENTER);
        JButton exitButton = new JButton("退出");
        add(exitButton, BorderLayout.SOUTH);

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        setVisible(true);
    }
}