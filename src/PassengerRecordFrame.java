import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PassengerRecordFrame {
    public static void showPassengerRecord() {
        JFrame frame = new JFrame("Passenger Record");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel recordPanel = new JPanel();
        GroupLayout layout = new GroupLayout(recordPanel);
        recordPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JRadioButton idCardRadioButton = new JRadioButton("Passenger ID");
        JRadioButton travelCardRadioButton = new JRadioButton("Card ID");
        ButtonGroup cardTypeGroup = new ButtonGroup();
        cardTypeGroup.add(idCardRadioButton);
        cardTypeGroup.add(travelCardRadioButton);

        JLabel stationLabel = new JLabel("Station:");
        JTextField stationTextField = new JTextField(10);
        JLabel timeLabel = new JLabel("Time:");
        JTextField timeTextField = new JTextField(10);
        JButton confirmButton = new JButton("Confirm");

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(idCardRadioButton)
                                .addComponent(stationLabel)
                                .addComponent(timeLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(travelCardRadioButton)
                                .addComponent(stationTextField)
                                .addComponent(timeTextField)
                                .addComponent(confirmButton)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idCardRadioButton)
                                .addComponent(travelCardRadioButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(stationLabel)
                                .addComponent(stationTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(timeLabel)
                                .addComponent(timeTextField))
                        .addComponent(confirmButton)
        );

        confirmButton.addActionListener((ActionEvent e) -> {
            String cardType = idCardRadioButton.isSelected() ? "Passenger ID" : "Card ID";
            String station = stationTextField.getText();
            String time = timeTextField.getText();
            System.out.println("记录乘客信息：" + cardType + " 上车站点：" + station + " 上车时间：" + time);
        });

        frame.add(recordPanel);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}  