//package ui;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class LoginScreen extends JFrame {
//    JFrame frame;
//    private static final int WIDTH = 1200;
//    private static final int HEIGHT = 600;
//    private static final Color BACKGROUND_COLOR = new Color(45, 47, 48);
//
//    public LoginScreen() {
//
//        initializeFrame();
//        initializeLoginScreen();
//
//        frame.setVisible(true);
//    }
//
//    private void initializeLoginScreen() {
//
//        JPanel loginPanel = new JPanel();
//        loginPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        loginPanel.setBackground(BACKGROUND_COLOR);
//
//        frame.add(loginPanel);
//    }
//
//    // REQUIRES:
//    // MODIFIES:
//    // EFFECTS: initializes the application frame
//    private void initializeFrame() {
//        frame = new JFrame("MiProperties");
//
//        frame.setSize(WIDTH, HEIGHT);
//
//        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//
//        int widthDimension = dimension.width / 2 - frame.getSize().width / 2;
//        int heightDimension = dimension.height / 2 - frame.getSize().height / 2;
//
//        frame.setLocation(widthDimension, heightDimension);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//
//        ImageIcon img = new ImageIcon("./data/icon.png");
//        frame.setIconImage(img.getImage());
//    }
//
//}
