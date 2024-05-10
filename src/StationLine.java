import javax.swing.*;
import java.awt.*;

public class StationLine {
    public static void showStationLine() {
        JFrame frame = new JFrame("Station Lines");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel operationPanel = new JPanel(new GridLayout(3, 2));

        JRadioButton addRadioButton = new JRadioButton("添加");
        JRadioButton deleteRadioButton = new JRadioButton("删除");
        ButtonGroup operationGroup = new ButtonGroup();
        operationGroup.add(addRadioButton);
        operationGroup.add(deleteRadioButton);

        JTextField metroLineTextField = new JTextField(10);
        JTextField metroStationTextField = new JTextField(10);
        JTextField stationPosition = new JTextField(10);

        operationPanel.add(addRadioButton);
        operationPanel.add(deleteRadioButton);
        operationPanel.add(new JLabel("Metro Line:"));
        operationPanel.add(metroLineTextField);
        operationPanel.add(new JLabel("Metro Station:"));
        operationPanel.add(metroStationTextField);
        operationPanel.add(new JLabel("Station Position:"));
        operationPanel.add(stationPosition);

        // 添加操作面板到主面板
        frame.add(operationPanel);
        frame.setVisible(true);
    }


}
