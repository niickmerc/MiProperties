package ui;

import javax.swing.*;

public class Testing {
    public static void main(String[] args) {
        JTextField xField = new JTextField(30);
        JTextField yField = new JTextField(30);

        JPanel myPanel = new JPanel();
//        myPanel.add(new JLabel("x:"));
        Box vertical = new Box(BoxLayout.Y_AXIS);
        Box vertical2 = new Box(BoxLayout.Y_AXIS);
        vertical.add(new JLabel("x:"));
        vertical2.add(xField);
        vertical.add(new JLabel("y:"));
        vertical2.add(yField);
        myPanel.add(vertical);
        myPanel.add(vertical2);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("x value: " + xField.getText());
            System.out.println("y value: " + yField.getText());
        }
    }
}