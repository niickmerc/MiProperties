package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PropertyManagementAppGUI extends JFrame implements ActionListener {

    // Setting JFrame width and height
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;
    private static final Color BACKGROUND_COLOR = new Color(45,47, 48);

    // Swing components needed
    private JFrame frame;
    private JMenuBar menuBar;
    private JButton addCommand;
    private JButton deleteCommand;
    private JButton viewCommand;
    private JButton manageCommand;

    // Fields required for loading and saving the file
    private static final String JSON_STORE = "./data/portfolio.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public PropertyManagementAppGUI() {

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        // Create JFrame
        frame = new JFrame("MiProperties  V2.0.1");

        // Customize JFrame Attributes
        frame.setSize(WIDTH, HEIGHT);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int widthDimension = dimension.width / 2 - frame.getSize().width / 2;
        int heightDimension = dimension.height / 2 - frame.getSize().height / 2;
        frame.setLocation(widthDimension, heightDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Creating Main Panel (Displays list of currently-owned properties)
        JPanel propertiesPanel = new JPanel();
        propertiesPanel.setPreferredSize(new Dimension(800, HEIGHT));
        propertiesPanel.setBackground(BACKGROUND_COLOR);

        JLabel propertiesLabel = new JLabel("Properties Panel Placeholder.");
        propertiesLabel.setForeground(Color.WHITE);
        propertiesLabel.setFont(new Font("Avenir", 10, 20));
        propertiesPanel.add(propertiesLabel);
        frame.add(propertiesPanel, BorderLayout.WEST);

        // Create Summary Panel (Will display portfolio summary statistics in real-time)
        JPanel summaryPanel = new JPanel();
        summaryPanel.setPreferredSize(new Dimension(400, HEIGHT));
        summaryPanel.setBackground(Color.GRAY);
        JLabel summaryLabel = new JLabel("Summary Panel Placeholder.");
        summaryLabel.setForeground(Color.WHITE);
        summaryLabel.setFont(new Font("Avenir", 10, 20));
        summaryPanel.add(summaryLabel);
        frame.add(summaryPanel, BorderLayout.EAST);

        // Create Option Panel (Will display all behaviour available to users)
        // EVENTUALLY: buildOptionPanel();
        JPanel optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(WIDTH, 50));
        optionPanel.setBackground(Color.BLACK);
        optionPanel.setLayout(new FlowLayout());
        frame.add(optionPanel, BorderLayout.SOUTH);

        // Create Menu Bar
        buildMenuBar();


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void buildMenuBar() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");


        fileMenu.add(save);
        fileMenu.add(load);
        fileMenu.add(exit);

        frame.setJMenuBar(menuBar);
    }
}
