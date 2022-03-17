//package ui;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class LaunchPage implements ActionListener {
//
//    JFrame frame = new JFrame();
//    JButton accessButton = new JButton("Click me");
//
//    public LaunchPage() {
//
//        accessButton.setBounds(100, 160, 100, 100);
//        accessButton.setFocusable(false);
//        accessButton.addActionListener(this);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//        frame.setLayout(null);
//        frame.add(accessButton);
//        frame.setVisible(true);
//
//
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        new PropertyManagementAppGUI();
//    }
//}
