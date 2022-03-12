package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

public class PropertyManagementAppGUI extends JFrame {

    // Setting JFrame width and height
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;

    // Swing components needed
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

        initializeGraphics();
      //  initializeActionListeners();
    }

    public void initializeGraphics() {

        JFrame frame = new JFrame("Propulus  V2.0.1");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(100, 100);

        initializeMenuBar();

        frame.setVisible(true);
    }

    public void initializeMenuBar() {

        menuBar = new JMenuBar();
        JMenu save = new JMenu("Save");
        JMenuItem savePortfolio = new JMenuItem("Save Portfolio");
        save.add(savePortfolio);
        menuBar.add(save);
        JMenu load = new JMenu("Load");
        JMenuItem loadPortfolio = new JMenuItem("Load Portfolio");
        load.add(loadPortfolio);
        menuBar.add(load);



    }



}
