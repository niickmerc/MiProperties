package ui;

import model.Portfolio;
import model.Property;
import model.Tenant;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.OptionButton;
import ui.OptionPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class PropertyManagementAppGUI extends JFrame {

    // Setting JFrame width and height
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;
    private static final Color BACKGROUND_COLOR = new Color(45, 47, 48);
    private static final Font BUTTON_FONT = new Font("Avenir", 1, 15);
    private static final Font LABEL_FONT = new Font("Avenir", 1, 20);

    // Swing components needed
    private JFrame frame;
    private JMenuBar menuBar;
    private OptionButton addCommand;
    private OptionButton deleteCommand;
    private OptionButton viewCommand;
    private OptionButton manageCommand;

    private JList list;

    private Object selectedPropertyFromList;
    private Property selectedProperty;
    private int selectedIndex;

    private JLabel totalPortfolioSize;
    private JLabel totalPortfolioValue;
    private JLabel totalOccupancyRate;
    private JLabel monthlyRentalIncome;

    private JLabel portfolioSizeHeader;
    private JLabel portfolioValueHeader;
    private JLabel occupancyRateHeader;
    private JLabel totalRentalIncomeHeader;

    private JPanel optionPanel;
    private JPanel summaryPanel;
    private JPanel summaryTable;
    private JPanel mainPanel;
    private JPanel propertiesPanel;

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

        initializeGraphics();

        // Create Properties List
        loadProperties();

        list = new JList(listOfPropertyAddresses);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellWidth(780);
        list.addListSelectionListener(e -> selectProperty());
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

        createSummaryTable();

        frame.add(summaryPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void initializeGraphics() {
        initializeFrame();

        initializePanels();
    }

    private void initializePanels() {
        // Creating Main Panel
        initializeMainPanel();

        // Create Summary Panel (Will display portfolio summary statistics in real-time)
        summaryPanel = new JPanel();
        summaryPanel.setPreferredSize(new Dimension((WIDTH * 1 / 3), HEIGHT));
        summaryPanel.setBackground(new Color(42, 42, 42));
        JLabel summaryBanner = new JLabel("Portfolio Summary");
        stylePanel(summaryBanner, 25);
        summaryPanel.add(summaryBanner);

        // Create Main Panel Border
        JPanel mainPanelNorthBorder = new JPanel();
        mainPanelNorthBorder.setPreferredSize(new Dimension(mainPanel.getWidth(), 50));
        JLabel banner = new JLabel("<html>Welcome to<b>MiProperties!</b>Your portfolio is below:</html>");
        stylePanel(banner, 25);
        mainPanelNorthBorder.add(banner);
        mainPanelNorthBorder.setBackground(BACKGROUND_COLOR);
        mainPanel.add(mainPanelNorthBorder, BorderLayout.NORTH);

        JPanel mainPanelSouthBorder = new JPanel();
        mainPanelSouthBorder.setPreferredSize(new Dimension(mainPanel.getWidth(), 100));
        mainPanelSouthBorder.setBackground(BACKGROUND_COLOR);
        mainPanel.add(mainPanelSouthBorder, BorderLayout.SOUTH);

        // Create Properties Panel
        propertiesPanel = new JPanel();
        propertiesPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), mainPanel.getHeight() - 200));
        propertiesPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(propertiesPanel);
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension((WIDTH * 2 / 3), HEIGHT));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setLayout(new BorderLayout());

        frame.add(mainPanel, BorderLayout.WEST);
    }

    private void initializeFrame() {
        frame = new JFrame("MiProperties  V2.0.1");

        frame.setSize(WIDTH, HEIGHT);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int widthDimension = dimension.width / 2 - frame.getSize().width / 2;
        int heightDimension = dimension.height / 2 - frame.getSize().height / 2;
        frame.setLocation(widthDimension, heightDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private void selectProperty() {
        selectedPropertyFromList = list.getSelectedValue();
        selectedIndex = list.getSelectedIndex();
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void createSummaryTable() {

        summaryTable = new JPanel();
        Box columnOne = new Box(BoxLayout.Y_AXIS);
        columnOne.setFont(BUTTON_FONT);

        columnOne.add(portfolioSizeHeader = new JLabel("Total Properties: "));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(portfolioValueHeader = new JLabel("Total Portfolio Value: "));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(occupancyRateHeader = new JLabel("Occupancy Rate: "));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(totalRentalIncomeHeader = new JLabel("Total Rental Income: "));
        summaryTable.add(columnOne);

        Box columnTwo = new Box(BoxLayout.Y_AXIS);
        columnTwo.add(totalPortfolioSize = new JLabel(String.valueOf(portfolio.getPropertyList().size())));
        columnTwo.add(Box.createVerticalStrut(10));
        columnTwo.add(totalPortfolioValue = new JLabel(currencyConverter(portfolio.getTotalPortfolioValue())));
        columnTwo.add(Box.createVerticalStrut(10));
        columnTwo.add(totalOccupancyRate = new JLabel((portfolio.getOccupanyRate()) + "%"));
        columnTwo.add(Box.createVerticalStrut(10));
        columnTwo.add(monthlyRentalIncome =  new JLabel(currencyConverter(portfolio.getTotalMonthlyRent())));
        summaryTable.add(columnTwo);
        summaryTable.setBackground(BACKGROUND_COLOR);

        stylePanel(portfolioSizeHeader);
        stylePanel(portfolioValueHeader);
        stylePanel(occupancyRateHeader);
        stylePanel(totalRentalIncomeHeader);

        stylePanel(totalPortfolioSize);
        stylePanel(totalPortfolioValue);
        stylePanel(totalOccupancyRate);
        stylePanel(monthlyRentalIncome);

        summaryPanel.add(summaryTable);
    }

    private void stylePanel(JLabel label) {
        label.setForeground(Color.white);
        label.setFont(LABEL_FONT);
    }

    private void stylePanel(JLabel label, int fontSize) {
        label.setForeground(Color.white);
        label.setFont(new Font("Avenir", 1, fontSize));
    }

    private void loadProperties() {

        listOfPropertyObjects = new DefaultListModel();
        listOfPropertyAddresses = new DefaultListModel();

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
        optionPanel = new OptionPanel();
        initializeOptionButtons();
        frame.add(optionPanel, BorderLayout.SOUTH);
    }

    public void initializeOptionButtons() {
        addCommand = new OptionButton("add");
        addCommand.addActionListener(e -> addNewProperty());
        optionPanel.add(addCommand);

        deleteCommand = new OptionButton("delete");
        deleteCommand.addActionListener(e -> removeExistingProperty());
        optionPanel.add(deleteCommand);

        manageCommand = new OptionButton("manage");
        manageCommand.addActionListener(e -> manageExistingProperty());
        optionPanel.add(manageCommand);

        viewCommand = new OptionButton("view");
        viewCommand.addActionListener(e -> viewCurrentProperty());
        optionPanel.add(viewCommand);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void manageExistingProperty() {
        selectedProperty = (Property) listOfPropertyObjects.get(list.getSelectedIndex());

        JPanel managePropertyPanel = new JPanel();
        JTextField civicAddressField = new JTextField(20);
        JTextField propertyValueField = new JTextField(20);
        JTextField monthlyRentalIncomeField = new JTextField(20);
        JTextField currentTenantsField = new JTextField(20);
        JCheckBox clearTenantsCheckBox = new JCheckBox("Yes");

        Box columnOne = new Box(BoxLayout.Y_AXIS);
        Box columnTwo = new Box(BoxLayout.Y_AXIS);

        columnOne.add(new JLabel("Update Civic Address:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Update Property Value:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Update Desired Monthly Rent:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Update Tenant List:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Clear Tenant List?"));

        columnTwo.add(civicAddressField);
        columnTwo.add(propertyValueField);
        columnTwo.add(monthlyRentalIncomeField);
        columnTwo.add(currentTenantsField);
        columnTwo.add(clearTenantsCheckBox);

        managePropertyPanel.add(columnOne);
        managePropertyPanel.add(columnTwo);

        int result = JOptionPane.showConfirmDialog(null, managePropertyPanel,
                "Manage Selected Property:", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            updatePropertyDetails(selectedProperty, civicAddressField, propertyValueField, monthlyRentalIncomeField,
                    currentTenantsField, clearTenantsCheckBox);
        }
    }

    private void updatePropertyDetails(Property selectedProperty, JTextField civicAddressField,
                                       JTextField propertyValueField, JTextField monthlyRentalIncomeField,
                                       JTextField currentTenantsField, JCheckBox clearTenantsCheckBox) {

        if (isTextFieldFilled(civicAddressField.getText())) {
            selectedProperty.setCivicAddress(civicAddressField.getText());
        }
        if (isTextFieldFilled(propertyValueField.getText())) {
            int newPropertyValue = Integer.parseInt(propertyValueField.getText());
            selectedProperty.setPropertyValue(newPropertyValue);
        }
        if (isTextFieldFilled(monthlyRentalIncomeField.getText())) {
            int newMonthlyRentalIncome = Integer.parseInt(monthlyRentalIncomeField.getText());
            selectedProperty.setMonthlyRent(newMonthlyRentalIncome);
        }
        if (isTextFieldFilled(currentTenantsField.getText())) {
            updateTenantList(selectedProperty, initializeTenantList(currentTenantsField.getText()));
        } else if (clearTenantsCheckBox.isSelected()) {
            clearTenantList(selectedProperty);
        }
        refresh();
    }

    private void refresh() {
        refreshProperties();
        refreshSummaryStatistics();
    }

    private void clearTenantList(Property selectedProperty) {
        int tenantListLength = selectedProperty.getTenantList().size();
        for (int i = 0; i < tenantListLength; i++) {
            String tenantName = selectedProperty.getTenantList().get(0).getTenantName();
            selectedProperty.removeTenant(tenantName);
        }

    }

    private void updateTenantList(Property selectedProperty, ArrayList<Tenant> updatedTenantList) {
        for (Tenant t : updatedTenantList) {
            selectedProperty.addNewTenant(t.getTenantName());
        }
    }


    private boolean isTextFieldFilled(String textField) {
        if (textField.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private void viewCurrentProperty() {
        selectedProperty = (Property) listOfPropertyObjects.get(list.getSelectedIndex());

        JPanel viewPropertyPanel = new JPanel();
        Box columnOne = new Box(BoxLayout.Y_AXIS);
        Box columnTwo = new Box(BoxLayout.Y_AXIS);
        columnOne.add(new JLabel("Civic Address:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Property Value:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Desired Monthly Rent:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Current Tenants:"));
        columnTwo.add(new JLabel(" " + selectedProperty.getCivicAddress()));
        columnTwo.add(Box.createVerticalStrut(10));
        columnTwo.add(new JLabel(" " + currencyConverter(selectedProperty.getPropertyValue())));
        columnTwo.add(Box.createVerticalStrut(10));
        columnTwo.add(new JLabel(" " + currencyConverter(selectedProperty.getMonthlyRent())));
        columnTwo.add(Box.createVerticalStrut(10));
        columnTwo.add(new JLabel(" " + getTenantString(selectedProperty)));
        viewPropertyPanel.add(columnOne);
        viewPropertyPanel.add(columnTwo);

        JOptionPane viewPropertyPane = new JOptionPane();
        viewPropertyPane.showConfirmDialog(null, viewPropertyPanel,
                "Selected Property Details:", JOptionPane.OK_CANCEL_OPTION);
    }

    private String getTenantString(Property selectedProperty) {
        if (selectedProperty.getTenantList().isEmpty()) {
            return "Vacant Property";
        }
        String tenantString = "";
        for (Tenant t : selectedProperty.getTenantList()) {
            tenantString += t.getTenantName() + ", ";
        }
        return tenantString.substring(0, tenantString.length() - 2);
    }

    private String currencyConverter(long num) {
        return NumberFormat.getCurrencyInstance().format(num);
    }

    private void removeExistingProperty() {
        String propertyToRemove = selectedPropertyFromList.toString();
        portfolio.removeExistingProperty(propertyToRemove);
        refresh();
    }

    private void refreshSummaryStatistics() {
        totalPortfolioSize.setText(String.valueOf(portfolio.getPropertyList().size()));
        totalPortfolioValue.setText(currencyConverter(portfolio.getTotalPortfolioValue()));
        totalOccupancyRate.setText(portfolio.getOccupanyRate() + "%");
        monthlyRentalIncome.setText(currencyConverter(portfolio.getTotalMonthlyRent()));
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addNewProperty() {
        JPanel newPropertyPanel = new JPanel();
        Box columnOne = new Box(BoxLayout.Y_AXIS);
        Box columnTwo = new Box(BoxLayout.Y_AXIS);
        JTextField civicAddressField = new JTextField(20);
        JTextField propertyValueField = new JTextField(20);
        JTextField monthlyRentalIncomeField = new JTextField(20);
        JTextField currentTenantsField = new JTextField(20);
        columnOne.add(new JLabel("Civic Address:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Property Value:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Desired Monthly Rent:"));
        columnOne.add(Box.createVerticalStrut(10));
        columnOne.add(new JLabel("Current Tenants:"));
        columnTwo.add(civicAddressField);
        columnTwo.add(propertyValueField);
        columnTwo.add(monthlyRentalIncomeField);
        columnTwo.add(currentTenantsField);
        newPropertyPanel.add(columnOne);
        newPropertyPanel.add(columnTwo);
        int result = JOptionPane.showConfirmDialog(null, newPropertyPanel,
                "Enter New Property Information:", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            processInput(civicAddressField, propertyValueField, monthlyRentalIncomeField, currentTenantsField);
        }
    }

    private void processInput(JTextField civAdd, JTextField propValue, JTextField monthlyRent, JTextField tenants) {
        String civicAddress = civAdd.getText();
        int propertyValue = Integer.parseInt(propValue.getText());
        int monthyRentalIncome = Integer.parseInt(monthlyRent.getText());

        portfolio.addNewProperty(civicAddress, propertyValue, monthyRentalIncome,
                initializeTenantList(tenants.getText()));
        refresh();
    }

    private ArrayList<Tenant> initializeTenantList(String tenantString) {

        String [] tenantNames = tenantString.split(", ");
        ArrayList<Tenant> tenantList = new ArrayList<>();

        if (tenantNames[0].equals("") && tenantNames.length == 1) {
            return tenantList;
        }
        for (String name : tenantNames) {
            tenantList.add(new Tenant(name));
        }
        return tenantList;
    }
}
