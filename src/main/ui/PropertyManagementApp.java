package ui;

import model.Portfolio;
import model.Property;
import model.Tenant;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class PropertyManagementApp extends JFrame {

    // Setting JFrame width and height
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;
    private static final Color BACKGROUND_COLOR = new Color(45, 47, 48);

    // Swing components needed
    private JFrame frame;
    private PropertyList list;

    private Object selectedPropertyFromList;
    private Property selectedProperty;

    private Label totalPortfolioSize;
    private Label totalPortfolioValue;
    private Label totalOccupancyRate;
    private Label monthlyRentalIncome;

    private JPanel optionPanel = new JPanel();
    private JPanel summaryPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel propertiesPanel = new JPanel();

    private Portfolio portfolio;
    private DefaultListModel listOfPropertyObjects = new DefaultListModel();
    private DefaultListModel listOfPropertyAddresses = new DefaultListModel();

    // Fields required for loading and saving the file
    private static final String JSON_STORE = "./data/portfolio.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    // MODIFIES: this
    // EFFECTS: initializes all required elements and adds them to the main frame
    public PropertyManagementApp() {

        initializeGraphics();
        loadProperties();
        displayPropertyList();
        buildMenuBar();
        buildOptionPanel();
        createSummaryTable();

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: constructs list of property addresses and adds it to the properties panel
    private void displayPropertyList() {
        list = new PropertyList(listOfPropertyAddresses);
        list.addListSelectionListener(e -> selectProperty());
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setBorder(BorderFactory.createEmptyBorder());
        propertiesPanel.add(listScrollPane, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: initializes all required elements and adds them to the main frame
    private void initializeGraphics() {
        initializeFrame();

        initializeMainPanel();

        initializeSummaryPanel();

        initializePropertiesPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes the properties panel and adds it to the main panel
    private void initializePropertiesPanel() {
        propertiesPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        propertiesPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(propertiesPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes the summary panel and adds it to the frame
    private void initializeSummaryPanel() {
        summaryPanel.setPreferredSize(new Dimension((WIDTH * 1 / 3), HEIGHT));
        summaryPanel.setBackground(new Color(42, 42, 42));
        Label summaryBanner = new Label("Portfolio Summary", 25);
        summaryPanel.add(summaryBanner);
        frame.add(summaryPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: initializes the main panel and adds it to the frame
    private void initializeMainPanel() {
        mainPanel.setPreferredSize(new Dimension((WIDTH * 2 / 3), HEIGHT));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setLayout(new BorderLayout());

        JPanel mainPanelNorthBorder = new JPanel(new FlowLayout(FlowLayout.LEADING));
        mainPanelNorthBorder.setPreferredSize(new Dimension(mainPanel.getWidth(), 75));
        Label banner = new Label("Welcome to MiProperties! Your portfolio is below:", 22);

        mainPanelNorthBorder.add(banner, BorderLayout.WEST);
        mainPanelNorthBorder.setBackground(BACKGROUND_COLOR);
        mainPanel.add(mainPanelNorthBorder, BorderLayout.NORTH);

        frame.add(mainPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: initializes the application frame
    private void initializeFrame() {
        frame = new JFrame("MiProperties");

        frame.setSize(WIDTH, HEIGHT);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int widthDimension = dimension.width / 2 - frame.getSize().width / 2;
        int heightDimension = dimension.height / 2 - frame.getSize().height / 2;
        frame.setLocation(widthDimension, heightDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        ImageIcon img = new ImageIcon("./data/icon.png");
        frame.setIconImage(img.getImage());
    }

    // MODIFIES: selectedPropertyFromList
    // EFFECTS: assigns the selected property object to selectedPropertyFromList
    private void selectProperty() {
        selectedPropertyFromList = list.getSelectedValue();
    }

    // MODIFIES: this
    // EFFECTS:  initializes the summary table and adds it to the summary panel
    private void createSummaryTable() {
        JPanel summaryTable = new JPanel();

        designTableHeaders(summaryTable);
        designTableValues(summaryTable);

        summaryPanel.add(summaryTable);
    }

    // MODIFIES: this
    // EFFECTS: constructs summary panel headers and adds them to the summary panel
    private void designTableHeaders(JPanel summaryTable) {
        Box summaryTableHeaders = new Box(BoxLayout.Y_AXIS);
        Label portfolioSizeHeader  = new Label("Total Properties: ");
        summaryTableHeaders.add(portfolioSizeHeader);
        summaryTableHeaders.add(Box.createVerticalStrut(10));
        Label portfolioValueHeader  = new Label("Total Portfolio Value: ");
        summaryTableHeaders.add(portfolioValueHeader);
        summaryTableHeaders.add(Box.createVerticalStrut(10));
        Label occupancyRateHeader = new Label("Occupancy Rate: ");
        summaryTableHeaders.add(occupancyRateHeader);
        summaryTableHeaders.add(Box.createVerticalStrut(10));
        Label totalRentalIncomeHeader  = new Label("Total Rental Income: ");
        summaryTableHeaders.add(totalRentalIncomeHeader);
        summaryTable.add(summaryTableHeaders);
    }

    // MODIFIES: this
    // EFFECTS: constructs summary panel values and adds them to the summary panel
    private void designTableValues(JPanel summaryTable) {
        Box summaryTableValues = new Box(BoxLayout.Y_AXIS);
        summaryTableValues.add(totalPortfolioSize = new Label(String.valueOf(portfolio.getPropertyList().size())));
        summaryTableValues.add(Box.createVerticalStrut(10));
        summaryTableValues.add(totalPortfolioValue = new Label(currencyConverter(portfolio.getTotalPortfolioValue())));
        summaryTableValues.add(Box.createVerticalStrut(10));
        summaryTableValues.add(totalOccupancyRate = new Label((portfolio.getOccupancyRate()) + "%"));
        summaryTableValues.add(Box.createVerticalStrut(10));
        summaryTableValues.add(monthlyRentalIncome =  new Label(currencyConverter(portfolio.getTotalMonthlyRent())));
        summaryTable.add(summaryTableValues);
        summaryTable.setBackground(BACKGROUND_COLOR);
    }

    // MODIFIES: this
    // EFFECTS: loads a user's portfolio from file
    private void loadProperties() {

        loadPropertyList();
        refreshProperties();

    }

    // MODIFIES: this
    // EFFECTS: loads portfolio data from file
    protected void loadPropertyList() {
        try {
            portfolio = jsonReader.read();
            System.out.println("Successfully loaded employee list from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS:  refreshes properties displayed on the properties panel
    public void refreshProperties() {
        listOfPropertyAddresses.clear();
        listOfPropertyObjects.clear();

        List<Property> listOfProperties = portfolio.getPropertyList();

        for (Property property : listOfProperties) {
            String civicAddress = property.getCivicAddress();
            listOfPropertyObjects.addElement(property);
            listOfPropertyAddresses.addElement(civicAddress);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the menu bar and adds it to the frame
    public void buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

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

    // EFFECTS: saves a user's portfolio to file
    private void savePortfolio() {
        try {
            jsonWriter.open();
            jsonWriter.write(portfolio);
            jsonWriter.close();
            System.out.println("Saved " + portfolio.getName() + " to " + JSON_STORE + " !");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads a user's portfolio from file
    private void loadPortfolio() {
        try {
            portfolio = jsonReader.read();
            System.out.println("Loaded " + portfolio.getName() + " from " + JSON_STORE + " !");
            refreshProperties();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the option panel and adds it to the frame
    public void buildOptionPanel() {
        optionPanel = new OptionPanel();

        initializeOptionButtons();

        frame.add(optionPanel, BorderLayout.SOUTH);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: initializes all option buttons and adds them to the option panel
    public void initializeOptionButtons() {
        OptionButton addCommand = new OptionButton("add");
        addCommand.addActionListener(e -> addNewProperty());
        optionPanel.add(addCommand);

        OptionButton deleteCommand = new OptionButton("delete");
        deleteCommand.addActionListener(e -> removeExistingProperty());
        optionPanel.add(deleteCommand);

        OptionButton manageCommand = new OptionButton("manage");
        manageCommand.addActionListener(e -> manageExistingProperty());
        optionPanel.add(manageCommand);

        OptionButton viewCommand = new OptionButton("view");
        viewCommand.addActionListener(e -> viewCurrentProperty());
//        viewCommand.addActionListener(e -> new ViewFrame(selectedProperty));
        optionPanel.add(viewCommand);
    }

    // MODIFIES: this
    // EFFECTS: gathers input from user and updates the selected property's fields

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void manageExistingProperty() {
        selectedProperty = (Property) listOfPropertyObjects.get(list.getSelectedIndex());

        Box manageExistingPropertyHeaders = new Box(BoxLayout.Y_AXIS);
        JPanel manageExistingPropertyPanel = new JPanel();
        JTextField civicAddressField = new JTextField(20);
        JTextField propertyValueField = new JTextField(20);
        JTextField monthlyRentalIncomeField = new JTextField(20);
        JTextField addTenantsField = new JTextField(20);
        JTextField removeTenantsField = new JTextField(20);

        manageExistingPropertyHeaders.add(new JLabel("Update Civic Address:"));
        manageExistingPropertyHeaders.add(Box.createVerticalStrut(10));
        manageExistingPropertyHeaders.add(new JLabel("Update Property Value:"));
        manageExistingPropertyHeaders.add(Box.createVerticalStrut(10));
        manageExistingPropertyHeaders.add(new JLabel("Update Desired Monthly Rent:"));
        manageExistingPropertyHeaders.add(Box.createVerticalStrut(10));
        manageExistingPropertyHeaders.add(new JLabel("Add New Tenant(s):"));
        manageExistingPropertyHeaders.add(Box.createVerticalStrut(10));
        manageExistingPropertyHeaders.add(new JLabel("Remove Existing Tenant(s):"));

        Box manageExistingPropertyFields = new Box(BoxLayout.Y_AXIS);
        manageExistingPropertyFields.add(civicAddressField);
        manageExistingPropertyFields.add(propertyValueField);
        manageExistingPropertyFields.add(monthlyRentalIncomeField);
        manageExistingPropertyFields.add(addTenantsField);
        manageExistingPropertyFields.add(removeTenantsField);
        manageExistingPropertyPanel.add(manageExistingPropertyFields);

        manageExistingPropertyPanel.add(manageExistingPropertyHeaders);
        manageExistingPropertyPanel.add(manageExistingPropertyFields);

        int result = JOptionPane.showConfirmDialog(null, manageExistingPropertyPanel,
                "Manage Selected Property:", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            updatePropertyDetails(selectedProperty, civicAddressField, propertyValueField, monthlyRentalIncomeField,
                    addTenantsField, removeTenantsField);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates relevant fields for the selected property
    private void updatePropertyDetails(Property selectedProperty, JTextField civicAddressField,
                                       JTextField propertyValueField, JTextField monthlyRentalIncomeField,
                                       JTextField addTenantsField, JTextField removeTenantsField) {

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

        if (isTextFieldFilled(addTenantsField.getText())) {
            addTenantsToList(selectedProperty, initTenants(addTenantsField.getText()));
        }
        if (isTextFieldFilled(removeTenantsField.getText())) {
            removeTenantsFromList(selectedProperty, initTenants(removeTenantsField.getText()));
        }
        refreshPortfolio();
    }

    // MODIFIES: this
    // EFFECTS: removes all specified tenants from selectedProperty
    private void removeTenantsFromList(Property selectedProperty, ArrayList<Tenant> tenantsToRemove) {
        for (Tenant t : tenantsToRemove) {
            selectedProperty.removeTenant(t.getTenantName());
        }
    }

    // MODIFIES: this
    // EFFECTS: refreshes portfolio information displayed on screen
    private void refreshPortfolio() {
        refreshProperties();
        refreshSummaryStatistics();
        list.setSelectedIndex(0);
    }

    // MODIFIES: selectedProperty
    // EFFECTS: adds all specified tenants to selectedProperty
    private void addTenantsToList(Property selectedProperty, ArrayList<Tenant> tenantsToAdd) {
        for (Tenant t : tenantsToAdd) {
            selectedProperty.addNewTenant(t.getTenantName());
        }
    }

    // EFFECTS: returns true if a textField has been filled out by the user
    private boolean isTextFieldFilled(String textField) {
        if (textField.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: displays information relating to selectedProperty on a JPanel
    private void viewCurrentProperty() {
        selectedProperty = (Property) listOfPropertyObjects.get(list.getSelectedIndex());

        JPanel viewPropertyPanel = createPropertyPanel();

        JOptionPane viewPropertyPane = new JOptionPane();
        viewPropertyPane.showConfirmDialog(null, viewPropertyPanel,
                "Selected Property Details:", JOptionPane.OK_CANCEL_OPTION);
    }

    // EFFECTS: creates a panel that displays information on selectedProperty
    private JPanel createPropertyPanel() {
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
        return viewPropertyPanel;
    }

    // EFFECTS: returns information about selectedProperty's tenants in string format
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

    // EFFECTS: converts an integer value to a currency value
    private String currencyConverter(long num) {
        return NumberFormat.getCurrencyInstance().format(num);
    }

    // MODIFIES: portfolio
    // EFFECTS: removes the selected property from a user's portfolio and refreshes visible data
    private void removeExistingProperty() {
        String propertyToRemove = selectedPropertyFromList.toString();
        portfolio.removeExistingProperty(propertyToRemove);
        refreshPortfolio();
    }

    // MODIFIES: this
    // EFFECTS: refreshes the displayed summary statistics displayed on the application homepage
    private void refreshSummaryStatistics() {
        totalPortfolioSize.setText(String.valueOf(portfolio.getPropertyList().size()));
        totalPortfolioValue.setText(currencyConverter(portfolio.getTotalPortfolioValue()));
        totalOccupancyRate.setText(portfolio.getOccupancyRate() + "%");
        monthlyRentalIncome.setText(currencyConverter(portfolio.getTotalMonthlyRent()));
    }

    // MODIFIES: selectedProperty
    // EFFECTS: gathers user input and passes information to the addNewProperty method
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
            addNewProperty(civicAddressField, propertyValueField, monthlyRentalIncomeField, currentTenantsField);
        }
    }

    // REQUIRES: value and rent must be non-zero integers
    // MODIFIES: this, portfolio
    // EFFECTS: creates a new property using information gathered from the user
    private void addNewProperty(JTextField civic, JTextField value, JTextField rent, JTextField tenants) {
        String civicAddress = civic.getText();
        int propertyValue = Integer.parseInt(value.getText());
        int monthyRentalIncome = Integer.parseInt(rent.getText());

        portfolio.addNewProperty(civicAddress, propertyValue, monthyRentalIncome, initTenants(tenants.getText()));
        refreshPortfolio();
    }

    // EFFECTS: Parses a string of tenant names and returns them in an ArrayList
    private ArrayList<Tenant> initTenants(String tenantString) {

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
