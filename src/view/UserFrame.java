package view;

import model.DbCtrl;
import model.ModifyLines;
import model.ModifyStations;
import model.SearchStation;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserFrame extends JFrame {
    private JTextArea sqlTextArea;
    private JTextArea resultTextArea;

    public void SubwayManagementUI(LoginFrame.User user) {
        setTitle("深圳地铁管理系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(560, 300);
        JLabel userInfoLabel = new JLabel("<html>用户名: " + user.username + "<br>权限: " + user.getRole() + "</html>");
        userInfoLabel.setFont(new Font("", Font.BOLD, 20));
        userInfoLabel.setBounds(30, -10, 200, 80);
        add(userInfoLabel);
        JTextArea sqlTextArea = new JTextArea();
        JScrollPane sqlScrollPane = new JScrollPane(sqlTextArea);
        sqlScrollPane.setBounds(10, 100, 500, 120);
        sqlScrollPane.setBorder(BorderFactory.createTitledBorder("输入SQL语句"));
        add(sqlScrollPane);
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
        resultScrollPane.setBounds(10, 240, 500, 150);
        resultScrollPane.setBorder(BorderFactory.createTitledBorder("结果输出"));
        add(resultScrollPane);
        JButton executeButton = new JButton("执行");
        executeButton.setBounds(240, 400, 80, 30);
        executeButton.addActionListener(e -> {
            resultTextArea.setText(DbCtrl.query(sqlTextArea.getText()));
        });
        add(executeButton);
        int buttonWidth = 150;
        int buttonHeight = 35;
        int buttonSpacing = 20;
        int startX = 600; // 按钮起始X坐标
        int startY = 20; // 按钮起始Y坐标

        JButton modifyStationButton = new JButton("修改地铁站");
        modifyStationButton.setBounds(startX, startY, buttonWidth, buttonHeight);
        add(modifyStationButton);

        JButton modifyLineButton = new JButton("修改地铁线");
        modifyLineButton.setBounds(startX, startY + (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        add(modifyLineButton);

        JButton modifyStationAndLineButton = new JButton("地铁站和地铁线");
        modifyStationAndLineButton.setBounds(startX, startY + 2 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        add(modifyStationAndLineButton);

        JButton searchStationButton = new JButton("查询站点");
        searchStationButton.setBounds(startX, startY + 3 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        add(searchStationButton);

        JButton passengerBoardButton = new JButton("乘客上车");
        passengerBoardButton.setBounds(startX, startY + 4 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        add(passengerBoardButton);

        JButton passengerDisembarkButton = new JButton("乘客下车");
        passengerDisembarkButton.setBounds(startX, startY + 5 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        add(passengerDisembarkButton);

        JButton searchPassengerButton = new JButton("查询乘客");
        searchPassengerButton.setBounds(startX, startY + 6 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        add(searchPassengerButton);

        JButton searchPathButton = new JButton("查询路径");
        searchPathButton.setBounds(startX, startY + 7 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        add(searchPathButton);

        JButton searchRideButton = new JButton("查询记录");
        searchRideButton.setBounds(startX, startY + 8 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        add(searchRideButton);



        modifyStationButton.addActionListener(e -> {
            ModifyStations.ModifyStation();
        });

        modifyLineButton.addActionListener(e -> {
            ModifyLines.ModifyLine();
        });

        modifyStationAndLineButton.addActionListener(e -> {
            StationLineFrame.showStationLine();
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

        searchPathButton.addActionListener(e -> {
            QueryPathFrame.showPathQuery();
        });

        searchRideButton.addActionListener(e -> {
            new QueryRideFrame();
        });

        JButton exitButton = new JButton("退出");
        exitButton.setBounds(350, 20, buttonWidth/2-10, buttonHeight);
        add(exitButton);
        JButton modifyPasswordButton = new JButton("修改用户密码");
        modifyPasswordButton.setBounds(200, 20, buttonWidth-20, buttonHeight);
        add(modifyPasswordButton);
        exitButton.addActionListener(e -> {
            try {
                DbCtrl.disconnect();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        });
        setVisible(true);
    }
}