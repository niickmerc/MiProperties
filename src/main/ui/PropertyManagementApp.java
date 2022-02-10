package ui;

import model.Portfolio;
import model.Property;
import model.Tenant;

import java.util.Locale;
import java.util.Scanner;

// This class represents the user interface for my property management application
public class PropertyManagementApp {
    private Portfolio portfolio;
    private Scanner input;

    // EFFECTS: runs the property management application
    public PropertyManagementApp() {
        System.out.println("Hello, and welcome to MyPropertyManagement App v1.");
        runApp();
    }

    // MODIFIES: this
    // EFFECTS:  instantiates initial objects and processes user input
    private void runApp() {

        boolean runProgram = true;
        String userInput;

        initializeFields();

        while (runProgram) {
            System.out.println();
            applicationMenu();
            userInput = formatUserInput(input.next());

            if (userInput.equals("quit")) {
                runProgram = false;
            } else {
                processCommand(userInput);
            }
        }
        System.out.println("\nTerminating Application");
    }

    // MODIFIES: this
    // EFFECTS: Instantiates required objects and utilities
    private void initializeFields() {
        portfolio = new Portfolio();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays current portfolio and main menu on the console
    private void applicationMenu() {
        viewAllPropertiesMainMenu();
        System.out.println();
        System.out.println("To add a new property, type 'add'");
        System.out.println("To remove an existing property, type 'remove'");
        System.out.println("To manage a specific property, type 'manage'");
        System.out.println("For summary statistics on your portfolio, type 'summary'");
        System.out.println("To close the application, type 'quit'");
        System.out.print("Type your answer here: ");
    }

    // MODIFIES: this
    // EFFECTS: processes user input according to the four possible options, otherwise returns an error message
    private void processCommand(String userInput) {
        if (userInput.equals("add")) {
            addNewProperty();
        } else if (userInput.equals("remove")) {
            removeExistingProperty();
        } else if (userInput.equals("manage")) {
            manageExistingProperty();
        } else if (userInput.equals("summary")) {
            printSummaryStatistics();
        } else {
            System.out.println("\nYou have entered an invalid input. Returning to the main menu");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input according to the seven possible options, otherwise returns an error message
    private void processCommand(String userInput, Property selectedProperty) {
        if (userInput.equals("civic")) {
            updateCivicAddress(selectedProperty);
        } else if (userInput.equals("value")) {
            updateMarketValue(selectedProperty);
        } else if (userInput.equals("income")) {
            updateMonthlyRentalIncome(selectedProperty);
        } else if (userInput.equals("tenants")) {
            manageTenantsMenu(selectedProperty);
        } else if (userInput.equals("add")) {
            addTenants(selectedProperty);
        } else if (userInput.equals("remove")) {
            removeTenants(selectedProperty);
        } else if (userInput.equals("main")) {
            return;
        } else {
            System.out.println("\nYou have entered an invalid input. Please try again.");
            System.out.println("-----------------------------------------------------------------");
        }
    }

    // EFFECTS: returns true and calls printAllProperties() if propertyList isn't empty
    //          else returns false
    private boolean viewAllPropertiesSubMenu() {
        if (portfolio.getPropertyList().isEmpty()) {
            return false;
        } else {
            System.out.println();
            System.out.println("Your Portfolio:");
            printAllProperties();
            return true;
        }
    }

    // EFFECTS: returns true and calls printAllProperties() if propertyList isn't empty
    //          else returns false and prints error message on console
    private boolean viewAllPropertiesMainMenu() {
        if (portfolio.getPropertyList().isEmpty()) {
            System.out.println("Your portfolio is currently empty. Please add a property below.");
            return false;
        } else {
            System.out.println("Your Portfolio:");
            printAllProperties();
            return true;
        }
    }

    // EFFECTS: prints the civic address for all properties inside the portfolio out on the console
    public void printAllProperties() {
        int count = 1;
        for (Property p : portfolio.getPropertyList()) {
            System.out.println("\tProperty #" + count++ + ": " + p.getCivicAddress());
        }
    }

    // MODIFIES: this
    // EFFECTS: Gets new property info from user and passes it to the portfolio.addNewProperty() method.
    private void addNewProperty() {
        System.out.println("\nNew Property Information:");
        System.out.print("\tCivic Address: ");
        String civicAddress = input.next();

        System.out.print("\tPurchase Price: $");
        int purchasePrice = input.nextInt();

        System.out.print("\tDesired Monthly Rent: $");
        int monthlyRent = input.nextInt();

        if (portfolio.addNewProperty(civicAddress, purchasePrice, monthlyRent)) {
            System.out.println("\n" + civicAddress + " has been added to your portfolio!");
            System.out.println("-----------------------------------------------------------------");
        } else {
            System.out.println("\n" + civicAddress + " already exists in your portfolio and is unable to be added.");
            System.out.println("-----------------------------------------------------------------");
        }
    }

    // REQUIRES: propertyList.size() > 0
    // MODIFIES: this
    // EFFECTS: gathers user input and removes the property with the given address if it exists in the portfolio
    private void removeExistingProperty() {
        if (viewAllPropertiesSubMenu()) {
            System.out.print("\nEnter the address of the property you wish to remove: ");
            String userInput = input.next().trim();
            if (portfolio.removeExistingProperty(userInput)) {
                System.out.println("\n" + userInput + " has been removed from your portfolio!");
                System.out.println("-----------------------------------------------------------------");
            } else {
                System.out.println("\n" + userInput + " does not exist in your portfolio. Please try again.");
                System.out.println("-----------------------------------------------------------------");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: calls findProperty() or prints an error message if viewAllProperties() returns false
    private void manageExistingProperty() {
        if (viewAllPropertiesSubMenu()) {
            System.out.println();
            findProperty();
        }
    }

    // MODIFIES: this
    // EFFECTS: calls displayPropertyInfo() on a specific property if its address matches a user-inputted civic address
    private void findProperty() {
        System.out.print("Enter the address of the property you want to manage: ");
        String propName = input.next().trim();

        for (Property p : portfolio.getPropertyList()) {
            if (propName.equals(p.getCivicAddress())) {
                displayPropertyInfo(p);
                return;
            }
        }
        System.out.println(propName + " does not exist in your portfolio.");
        System.out.println("-----------------------------------------------------------------");
    }

    // MODIFIES: this
    // EFFECTS: prints out the current fields of the specific property requested by user out on the console and
    //          calls managePropertyMenu()
    private void displayPropertyInfo(Property selectedProperty) {
        System.out.println();
        System.out.println("Details for " + selectedProperty.getCivicAddress() +  ": ");
        System.out.println("\tMarket Value: $" + selectedProperty.getPropertyValue());
        System.out.println("\tDesired Monthly Rental Rate: $" + selectedProperty.getMonthlyRent());
        if (selectedProperty.getIsRented()) {
            viewAllTenants(selectedProperty);
        } else {
            System.out.println("\tOccupancy Status: Vacant");
        }
        managePropertyMenu(selectedProperty);
    }

    // MODIFIES: this
    // EFFECTS: displays menu of specific property actions to the user
    private void managePropertyMenu(Property selectedProperty) {
        System.out.println("\nTo update this property's address, type 'civic'");
        System.out.println("To update this property's market value, type 'value'");
        System.out.println("To update this property's desired rental income, type 'income'");
        System.out.println("To manage this property's tenants, type 'tenants'");
        System.out.println("To return to the main menu, type 'main'");
        System.out.print("Type your answer here: ");
        String userInput = formatUserInput(input.next());

        processCommand(userInput, selectedProperty);
    }

    // MODIFIES: this
    // EFFECTS: updates the address of a specific property
    private void updateCivicAddress(Property selectedProperty) {
        System.out.print("Enter the new civic address for this property: ");
        String newAddress = input.next().trim();
        selectedProperty.setCivicAddress(newAddress);
        System.out.println("\nAddress for " + selectedProperty.getCivicAddress() +  " updated!");
        System.out.println("-----------------------------------------------------------------");
    }

    // MODIFIES: this
    // EFFECTS: updates the market value of a specific property
    private void updateMarketValue(Property selectedProperty) {
        System.out.println("Enter the new market value for this property: ");
        int newMarketValue = input.nextInt();
        selectedProperty.setPropertyValue(newMarketValue);
        System.out.println("\nMarket value for " + selectedProperty.getCivicAddress() + " updated!");
        System.out.println("-----------------------------------------------------------------");
    }

    // MODIFIES: this
    // EFFECTS: updates the desired monthly rental income of a specific property
    private void updateMonthlyRentalIncome(Property selectedProperty) {
        System.out.println("Enter your desired rental income for this property: ");
        int newRentalIncome = input.nextInt();
        selectedProperty.setMonthlyRent(newRentalIncome);
        System.out.println("\nDesired rental income for " + selectedProperty.getCivicAddress() + " updated!");
        System.out.println("-----------------------------------------------------------------");
    }

    // MODIFIES: this
    // EFFECTS: displays menu of tenant options for user
    private void manageTenantsMenu(Property selectedProperty) {
        System.out.println();
        displayTenantList(selectedProperty);

        boolean keepRunning = true;
        String userInput;

        while (keepRunning) {
            System.out.println();
            System.out.println("To add a new tenant, type 'add'");
            System.out.println("To remove an existing tenant, type 'remove'");
            System.out.println("To return to the main menu, type 'main'");
            System.out.print("Type your answer here: ");
            userInput = formatUserInput(input.next());
            if (userInput.equals("main")) {
                return;
            } else {
                processCommand(userInput, selectedProperty);
            }
        }
    }

    // EFFECTS: displays menu of tenant options for user
    private void displayTenantList(Property selectedProperty) {
        if (selectedProperty.getTenantList().isEmpty()) {
            System.out.println(selectedProperty.getCivicAddress() + " is currently vacant.");
        } else {
            System.out.println("Tenants for " + selectedProperty.getCivicAddress());
            viewAllTenants(selectedProperty);
        }
    }

    // REQUIRES: selectedProperty.tenantList.size() > 0
    // EFFECTS: prints the names of all tenants corresponding to a specific property out
    public void viewAllTenants(Property selectedProperty) {
        int count = 1;
        for (Tenant t : selectedProperty.getTenantList()) {
            System.out.println("\tTenant #" + count++ + ": " + t.getTenantName());
        }
    }

    // MODIFIES: this
    // EFFECTS:  asks user to enter the name of a new tenant and passes it to the addNewTenant function
    private void addTenants(Property selectedProperty) {
        System.out.print("Enter your tenant's name: ");
        String tenantToAdd = input.next().trim();
        System.out.println();
        if (selectedProperty.addNewTenant(tenantToAdd)) {
            System.out.println(tenantToAdd + " has been added to " + selectedProperty.getCivicAddress() + "!");
        } else {
            System.out.println(tenantToAdd + " is already assigned to " + selectedProperty.getCivicAddress() + "!");
        }
        System.out.println("-----------------------------------------------------------------");
    }

    // MODIFIES: this
    // EFFECTS:  asks user to enter the name of an existing tenant and passes it to the removeTenant function
    private void removeTenants(Property selectedProperty) {
        System.out.print("Enter your tenant's name: ");
        String tenantToRemove = input.next().trim();
        System.out.println();
        if (selectedProperty.removeTenant(tenantToRemove)) {
            System.out.println(tenantToRemove + " has been removed from " + selectedProperty.getCivicAddress());
        } else {
            System.out.println(tenantToRemove + " does not exist in " + selectedProperty.getCivicAddress());
        }
        System.out.println("-----------------------------------------------------------------");
    }

    // EFFECTS: prints out summary statistics for a user's portfolio of properties out on the console
    private void printSummaryStatistics() {
        if (!portfolio.getPropertyList().isEmpty()) {
            System.out.println();
            System.out.println("Summary statistics for your portfolio:");
            System.out.println("Total value: $" + portfolio.getTotalPortfolioValue());
            System.out.println("Total monthly rent: $" + portfolio.getTotalMonthlyRent());
            System.out.println("Total vacancy rate: " + portfolio.getVacancyRate() + "%");
            System.out.println();
            System.out.print("Press enter to return to the main menu.");
            String userInput = input.next();
        }
    }

    // EFFECTS: returns user input formatted in all lowercase and without any leading or trailing whitespace
    private String formatUserInput(String userInput) {
        return userInput.toLowerCase(Locale.ROOT).trim();
    }
}
