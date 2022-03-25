package ui;

import com.sun.glass.ui.Clipboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Represents a login / landing page for my application
public class LoginScreen extends JFrame {
    private JFrame frame;
    private static final Color BACKGROUND_COLOR = new Color(45, 47, 48);
    JPanel loginPanel;

    // EFFECTS: creates a new LoginScreen
    public LoginScreen() {

        initializeFrame();
        initializeLoginScreen();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes all components and functionality for the login screen
    private void initializeLoginScreen() {

        loginPanel = new JPanel(new BorderLayout());
        loginPanel.setPreferredSize(new Dimension(600, 400));
        loginPanel.setBackground(BACKGROUND_COLOR);

        JPanel buffer = new JPanel();
        buffer.setPreferredSize(new Dimension(loginPanel.getWidth(), 100));
        buffer.setBackground(new Color(42, 42, 42));
        loginPanel.add(buffer, BorderLayout.SOUTH);

        try {
            BufferedImage image = ImageIO.read(new File("data/newIcon.png"));
            JLabel label = new JLabel(new ImageIcon(image));
            loginPanel.add(label);
        } catch (IOException e) {
            System.out.println();
        }

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> new PropertyManagementApp());
        enterButton.setPreferredSize(new Dimension(100, 50));
        buffer.add(enterButton);

        frame.add(loginPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes the login screen frame
    private void initializeFrame() {
        frame = new JFrame("MiProperties");

        frame.setSize(600, 400);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        int widthDimension = dimension.width / 2 - frame.getSize().width / 2;
        int heightDimension = dimension.height / 2 - frame.getSize().height / 2;

        frame.setLocation(widthDimension, heightDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        ImageIcon img = new ImageIcon("./data/icon.png");
        frame.setIconImage(img.getImage());
    }

}
