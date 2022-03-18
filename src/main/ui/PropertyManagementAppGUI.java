package ui;

import model.Portfolio;
import model.Property;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

public class PropertyManagementAppGUI extends JFrame {

    // Setting JFrame width and height
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;
    private static final Color BACKGROUND_COLOR = new Color(45, 47, 48);
    private static final Font BUTTON_FONT = new Font("Avenir", 1, 15);

    // Swing components needed
    private JFrame frame;
    private JMenuBar menuBar;
    private JButton addCommand;
    private JButton deleteCommand;
    private JButton viewCommand;
    private JButton manageCommand;

    private JList list;
    private JTable testTableNeedToDelete;

    private Object selectedPropertyObject;
    private Property selectedProperty;
    private int selectedIndex;

    private JPanel optionPanel;
    private JPanel summaryPanel;

    private Portfolio portfolio;
    private DefaultListModel listOfPropertyObjects;
    private DefaultListModel listOfPropertyAddresses; // Addresses to display within app

    // Fields required for loading and saving the file
    private static final String JSON_STORE = "./data/portfolio.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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

        // Creating Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension((WIDTH * 2 / 3), HEIGHT));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setLayout(new BorderLayout());

        frame.add(mainPanel, BorderLayout.WEST);

        // Create Summary Panel (Will display portfolio summary statistics in real-time)
        summaryPanel = new JPanel();
        summaryPanel.setPreferredSize(new Dimension((WIDTH * 1 / 3), HEIGHT));
        summaryPanel.setBackground(Color.GRAY);
        JLabel summaryPanelLabel = new JLabel("Your Portfolio Summary:");
        summaryPanelLabel.setForeground(Color.WHITE);
        summaryPanelLabel.setFont(new Font("Avenir", 10, 20));
        summaryPanel.add(summaryPanelLabel);

        createSummaryTable();

        frame.add(summaryPanel, BorderLayout.EAST);

        // Create Main Panel Borders
        JPanel mainPanelNorthBorder = new JPanel();
        mainPanelNorthBorder.setPreferredSize(new Dimension(mainPanel.getWidth(), 100));
        mainPanelNorthBorder.setBackground(BACKGROUND_COLOR);

        //  Code to add application logo TBD - Need to resize it
        ImageIcon appLogoImage = new ImageIcon("./data/appLogo.png");
        JLabel appLogoLabel = new JLabel(appLogoImage);
        mainPanelNorthBorder.add(appLogoLabel, BorderLayout.WEST);
        mainPanel.add(mainPanelNorthBorder, BorderLayout.NORTH);

        JPanel mainPanelSouthBorder = new JPanel();
        mainPanelSouthBorder.setPreferredSize(new Dimension(mainPanel.getWidth(), 100));
        mainPanelSouthBorder.setBackground(BACKGROUND_COLOR);
        mainPanel.add(mainPanelSouthBorder, BorderLayout.SOUTH);

        // Create Properties Panel
        JPanel propertiesPanel = new JPanel();
        propertiesPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), mainPanel.getHeight() - 200));
        propertiesPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(propertiesPanel);

        // Create Properties List
        listOfPropertyObjects = new DefaultListModel();
        listOfPropertyAddresses = new DefaultListModel();

        loadProperties();

        list = new JList(listOfPropertyAddresses);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellWidth(780);
//        list.addListSelectionListener(e -> selectedPropertyObject = list.getSelectedValue());
        list.addListSelectionListener(e -> testMethodLmao());
        list.setBackground(BACKGROUND_COLOR);
        list.setFont(new Font("Avenir", 1, 18));
        list.setForeground(Color.white);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setBorder(BorderFactory.createEmptyBorder());
        propertiesPanel.add(listScrollPane, BorderLayout.WEST);

        // Create Menu Bar
        buildMenuBar();

        // Create Option Panel
        buildOptionPanel();

        frame.setVisible(true);
    }

    private void testMethodLmao() {
        selectedPropertyObject = list.getSelectedValue();
        selectedIndex = list.getSelectedIndex();

    }

    private void createSummaryTable() {

        String[] rows = {"Total Properties",
                "Total Portfolio Value",
                "Rental Income",
                "Monthly Expenses",
                "NOI"};

        JTable summaryTable = new JTable();
        summaryTable.setBackground(Color.GREEN);
        JScrollPane sp = new JScrollPane(summaryTable);

        summaryPanel.add(sp);
    }

    private void loadProperties() {
        loadPropertyList();
        refreshProperties();
    }

    // MODIFIES: this
    // EFFECTS: loads the employee list from a file
    protected void loadPropertyList() {
        try {
            portfolio = jsonReader.read();
            System.out.println("Successfully loaded employee list from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Refreshes the employees on screen with the latest data
    public void refreshProperties() {
        listOfPropertyAddresses.clear();
        listOfPropertyObjects.clear();
        List<Property> listofProperties = portfolio.getPropertyList();
        for (Property property : listofProperties) {
            String civicAddress = property.getCivicAddress();
            listOfPropertyObjects.addElement(property);
            listOfPropertyAddresses.addElement(civicAddress);
        }
    }

    public void buildMenuBar() {

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(e -> savePortfolio());
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(e -> loadPortfolio());

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(1));

        fileMenu.add(save);
        fileMenu.add(load);
        fileMenu.add(exit);

        frame.setJMenuBar(menuBar);
    }

    private void savePortfolio() {
        try {
            jsonWriter.open();
            jsonWriter.write(portfolio);
            jsonWriter.close();
            System.out.println();
            System.out.println("Saved " + portfolio.getName() + " to " + JSON_STORE + " !");
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadPortfolio() {
        try {
            portfolio = jsonReader.read();
            System.out.println();
            System.out.println("Loaded " + portfolio.getName() + " from " + JSON_STORE + " !");
            refreshProperties();
        } catch (IOException e) {
            System.out.println();
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void buildOptionPanel() {

        optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        optionPanel.setPreferredSize(new Dimension(WIDTH, 50));
        optionPanel.setBackground(Color.BLACK);
        frame.add(optionPanel, BorderLayout.SOUTH);

        initializeOptions();
    }

    public void initializeOptions() {

        addCommand = new AppButton("add");
        addCommand.addActionListener(e -> addNewProperty());
        addCommand.setFont(BUTTON_FONT);
        addCommand.setForeground(Color.white);
        addCommand.setVerticalAlignment(SwingConstants.CENTER);
        optionPanel.add(addCommand);

        deleteCommand = new AppButton("delete");
        deleteCommand.addActionListener(e -> removeExistingProperty());
        deleteCommand.setFont(BUTTON_FONT);
        deleteCommand.setForeground(Color.white);
        deleteCommand.setVerticalAlignment(SwingConstants.CENTER);
        optionPanel.add(deleteCommand);

        manageCommand = new AppButton("manage");
        manageCommand.addActionListener(e -> manageExistingProperty());
        manageCommand.setFont(BUTTON_FONT);
        manageCommand.setForeground(Color.white);
        manageCommand.setVerticalAlignment(SwingConstants.CENTER);
        optionPanel.add(manageCommand);

        viewCommand = new AppButton("view");
        viewCommand.addActionListener(e -> viewCurrentProperty());
        viewCommand.setFont(BUTTON_FONT);
        viewCommand.setForeground(Color.white);
        viewCommand.setVerticalAlignment(SwingConstants.CENTER);
        optionPanel.add(viewCommand);
    }

    private void manageExistingProperty() {
        selectedProperty = (Property) listOfPropertyObjects.get(list.getSelectedIndex());

        JTextField civicAddressField = new JTextField(20);
        JTextField propertyValueField = new JTextField(20);
        JTextField monthlyRentalIncomeField = new JTextField(20);
        JPanel managePropertyPanel = new JPanel();
        Box columnOne = new Box(BoxLayout.Y_AXIS);
        Box columnTwo = new Box(BoxLayout.Y_AXIS);
        columnOne.add(new JLabel("Civic Address:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Property Value:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Desired Monthly Rent:"));
        columnTwo.add(civicAddressField);
        columnTwo.add(propertyValueField);
        columnTwo.add(monthlyRentalIncomeField);
        managePropertyPanel.add(columnOne);
        managePropertyPanel.add(columnTwo);


    }

    private void viewCurrentProperty() {
        selectedProperty = (Property) listOfPropertyObjects.get(list.getSelectedIndex());

        JPanel viewPropertyPanel = new JPanel();
        viewPropertyPanel.setLayout(new GridBagLayout());
        Box columnOne = new Box(BoxLayout.Y_AXIS);
        Box columnTwo = new Box(BoxLayout.Y_AXIS);
        columnOne.add(new JLabel("Civic Address:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Property Value:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Desired Monthly Rent:"));
        columnTwo.add(new JLabel(" " + selectedProperty.getCivicAddress()));
        columnTwo.add(Box.createVerticalStrut(10));
        columnTwo.add(new JLabel(" " + currencyConverter(selectedProperty.getPropertyValue())));
        columnTwo.add(Box.createVerticalStrut(10));
        columnTwo.add(new JLabel(" " + currencyConverter(selectedProperty.getMonthlyRent())));
        viewPropertyPanel.add(columnOne);
        viewPropertyPanel.add(columnTwo);

        JOptionPane viewPropertyPane = new JOptionPane();
        viewPropertyPane.setIcon(new ImageIcon("./data/appIcon.png"));
        viewPropertyPane.showConfirmDialog(null, viewPropertyPanel,
                "Selected Property Details:", JOptionPane.OK_CANCEL_OPTION);
    }

    private String currencyConverter(int num) {
        return NumberFormat.getCurrencyInstance().format(num);
    }

    private void removeExistingProperty() {
        String propertyToRemove = selectedPropertyObject.toString();
        portfolio.removeExistingProperty(propertyToRemove);
        refreshProperties();
    }

    private void addNewProperty() {
        JTextField civicAddressField = new JTextField(20);
        JTextField propertyValueField = new JTextField(20);
        JTextField monthlyRentalIncomeField = new JTextField(20);
        JPanel newPropertyPanel = new JPanel();
        Box columnOne = new Box(BoxLayout.Y_AXIS);
        Box columnTwo = new Box(BoxLayout.Y_AXIS);
        columnOne.add(new JLabel("Civic Address:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Property Value:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Desired Monthly Rent:"));
        columnTwo.add(civicAddressField);
        columnTwo.add(propertyValueField);
        columnTwo.add(monthlyRentalIncomeField);
        newPropertyPanel.add(columnOne);
        newPropertyPanel.add(columnTwo);


        int result = JOptionPane.showConfirmDialog(null, newPropertyPanel,
                "Enter New Property Information:", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String civicAddress = civicAddressField.getText();
            int propertyValue = Integer.parseInt(propertyValueField.getText());
            int monthyRentalIncome = Integer.parseInt(monthlyRentalIncomeField.getText());
            portfolio.addNewProperty(civicAddress, propertyValue, monthyRentalIncome);
            refreshProperties();
        }
    }
}
