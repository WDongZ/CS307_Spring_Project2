package view;

import model.DbCtrl;
import model.ModifyLines;
import model.ModifyStations;
import model.SearchStation;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserFrame extends JFrame {

    public void SubwayManagementUI(LoginFrame.User user) {
        Font font = new Font("微软雅黑", Font.PLAIN, 12);
        setTitle("深圳地铁管理系统");
        setSize(1200, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(560, 300);
        JLabel userInfoLabel = new JLabel("<html>用户名: " + user.username + "<br>权限: " + user.getRole() + "</html>");
        userInfoLabel.setFont(new Font("宋体", Font.BOLD, 20));
        userInfoLabel.setBounds(30, -10, 200, 80);
        userInfoLabel.setForeground(Color.WHITE);
        add(userInfoLabel);
        JTextArea sqlTextArea = new JTextArea();
        JScrollPane sqlScrollPane = new JScrollPane(sqlTextArea);
        sqlScrollPane.setBounds(40, 100, 500, 120);
        sqlScrollPane.setBorder(BorderFactory.createTitledBorder("输入SQL语句"));
        add(sqlScrollPane);
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
        resultScrollPane.setBounds(40, 240, 500, 150);
        resultScrollPane.setBorder(BorderFactory.createTitledBorder("结果输出"));
        add(resultScrollPane);
        JButton executeButton = new JButton("执行");
        executeButton.setBounds(280, 400, 80, 30);
        executeButton.setFont(font);
        executeButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        executeButton.setForeground(Color.white);
        executeButton.addActionListener(e -> {
            resultTextArea.setText(DbCtrl.query(sqlTextArea.getText()));
        });
        add(executeButton);
        int buttonWidth = 150;
        int buttonHeight = 35;
        int buttonSpacing = 20;
        int startX = 650; // 按钮起始X坐标
        int startY = 20; // 按钮起始Y坐标

        JButton modifyStationButton = new JButton("修改地铁站");
        modifyStationButton.setBounds(startX, startY, buttonWidth, buttonHeight);
        modifyStationButton.setFont(font);
        modifyStationButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        modifyStationButton.setForeground(Color.white);
        add(modifyStationButton);

        JButton modifyLineButton = new JButton("修改地铁线");
        modifyLineButton.setBounds(startX, startY + (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        modifyLineButton.setFont(font);
        modifyLineButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        modifyLineButton.setForeground(Color.white);
        add(modifyLineButton);

        JButton modifyStationAndLineButton = new JButton("地铁站和地铁线");
        modifyStationAndLineButton.setBounds(startX, startY + 2 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        modifyStationAndLineButton.setFont(font);
        modifyStationAndLineButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        modifyStationAndLineButton.setForeground(Color.white);
        add(modifyStationAndLineButton);

        JButton searchStationButton = new JButton("查询站点");
        searchStationButton.setBounds(startX, startY + 3 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        searchStationButton.setFont(font);
        searchStationButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        searchStationButton.setForeground(Color.white);
        add(searchStationButton);

        JButton passengerBoardButton = new JButton("乘客上车");
        passengerBoardButton.setBounds(startX, startY + 4 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        passengerBoardButton.setFont(font);
        passengerBoardButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        passengerBoardButton.setForeground(Color.white);
        add(passengerBoardButton);

        JButton passengerDisembarkButton = new JButton("乘客下车");
        passengerDisembarkButton.setBounds(startX, startY + 5 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        passengerDisembarkButton.setFont(font);
        passengerDisembarkButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        passengerDisembarkButton.setForeground(Color.white);
        add(passengerDisembarkButton);

        JButton searchPassengerButton = new JButton("查询乘客");
        searchPassengerButton.setBounds(startX, startY + 6 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        searchPassengerButton.setFont(font);
        searchPassengerButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        searchPassengerButton.setForeground(Color.white);
        add(searchPassengerButton);

        JButton searchPathButton = new JButton("查询路径");
        searchPathButton.setBounds(startX, startY + 7 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        searchPathButton.setFont(font);
        searchPathButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        searchPathButton.setForeground(Color.white);
        add(searchPathButton);

        JButton searchRideButton = new JButton("查询记录");
        searchRideButton.setBounds(startX+200, startY + 5 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        searchRideButton.setFont(font);
        searchRideButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        searchRideButton.setForeground(Color.white);
        add(searchRideButton);

        JButton searchBusButton = new JButton("查询公交");
        searchBusButton.setBounds(startX+200, startY + 6 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        searchBusButton.setFont(font);
        searchBusButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        searchBusButton.setForeground(Color.white);
        add(searchBusButton);

        JButton searchLandmarkButton = new JButton("查询地标");
        searchLandmarkButton.setBounds(startX+200, startY + 7 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
        searchLandmarkButton.setFont(font);
        searchLandmarkButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        searchLandmarkButton.setForeground(Color.white);
        add(searchLandmarkButton);


        modifyStationButton.addActionListener(e -> {
            if (user.getRole().equals("ADMIN")) ModifyStations.ModifyStation();
            else JOptionPane.showMessageDialog(null, "您没有权限修改地铁信息");
        });

        modifyLineButton.addActionListener(e -> {
            if (user.getRole().equals("ADMIN")) ModifyLines.ModifyLine();
            else JOptionPane.showMessageDialog(null, "您没有权限修改地铁信息");
        });

        modifyStationAndLineButton.addActionListener(e -> {
            if (user.getRole().equals("ADMIN")) StationLineFrame.showStationLine();
            else JOptionPane.showMessageDialog(null, "您没有权限修改地铁信息");
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
            if (user.getRole().equals("ADMIN")) new PassengerQueryFrame();
            else JOptionPane.showMessageDialog(null, "您没有权限查询乘客信息");
        });

        searchPathButton.addActionListener(e -> {
            QueryPathFrame.showPathQuery();
        });

        searchRideButton.addActionListener(e -> {
            if (user.getRole().equals("ADMIN")) new QueryRideFrame();
            else JOptionPane.showMessageDialog(null, "您没有权限查询乘车记录");
        });

        searchBusButton.addActionListener(e -> {
            QueryBusFrame.showBusQuery();
        });

        searchLandmarkButton.addActionListener(e -> {
            QueryLandmarkFrame.showQueryLandmark();
        });


        JButton exitButton = new JButton("退出");
        exitButton.setBounds(350, 20, buttonWidth/2-10, buttonHeight);
        exitButton.setFont(font);
        exitButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        exitButton.setForeground(Color.white);
        add(exitButton);
        JButton modifyPasswordButton = new JButton("修改用户密码");
        modifyPasswordButton.setBounds(200, 20, buttonWidth-20, buttonHeight);
        modifyPasswordButton.setFont(font);
        modifyPasswordButton.setBackground(Color.getHSBColor(0.45f, 0.8f, 0.6f));
        modifyPasswordButton.setForeground(Color.white);
        add(modifyPasswordButton);
        modifyPasswordButton.addActionListener(e -> {
            ModifyPasswordFrame.showModifyPassword(user);
        });
        exitButton.addActionListener(e -> {
            try {
                DbCtrl.disconnect();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        });
        JLabel lblBackground = new JLabel();
        lblBackground.setBounds(-730, 0, 1920, 540);
        lblBackground.setIcon(new ImageIcon("src/pics/img_1.png"));
        add(lblBackground);
        setVisible(true);
    }
}