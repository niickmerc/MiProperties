package ui;

import model.Portfolio;
import model.Property;
import java.util.Scanner;

// Property Management Application

public class PropertyManagementApp {

    private Portfolio portfolio;
    private Scanner input;

    public PropertyManagementApp() {
        System.out.println("Welcome to the myPropertyManagement app!");
        runApp();
    }

    private void runApp() {
        boolean keepRunning = true;
        String command;

        initialize();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Thanks for stopping by!");
    }

    private void initialize() {
        portfolio = new Portfolio();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("Select from one of the following options: \n");
        System.out.println("To view your current portfolio, type 'view'");
        System.out.println("To add a new property to your portfolio, type 'add'");
        System.out.println("To manage a specific property, type 'manage'");
        System.out.println("To remove a property from your portfolio, type 'remove'");
        System.out.println("To quit the application, type 'quit'");
    }

    private void processCommand(String userInput) { // USE SWITCH HERE
        if (userInput.equals("view")) {
            viewAllProperties();
        } else if (userInput.equals("manage")) {
            findProperty();
        } else if (userInput.equals("add")) {
            addNewProperty();
        } else if (userInput.equals("remove")) {
            removeExistingProperty();
        } else {
            System.out.println("You've made an invalid selection. Please try again.");
        }
    }

    private boolean viewAllProperties() {
        if (portfolio.getPropertyList().isEmpty()) {
            System.out.println("Your portfolio is currently empty.");
            return false;
        } else {
            portfolio.viewAllProperties();
            return true;
        }
    }

    private void addNewProperty() {
        System.out.print("Civic Address: ");
        String address = input.next();
        System.out.print("Property Value: $");
        int value = input.nextInt();
        System.out.print("Desired Monthly Rental Income: $");
        int income = input.nextInt();

        portfolio.addNewProperty(address, value, income);
    }

    private void removeExistingProperty() {
        viewAllProperties();

        if (portfolio.getPropertyList().isEmpty()) {
            System.out.println("Please add a property and try again.");
        } else {
            System.out.println("Enter the full address of the property you want to remove: ");
            String propName = input.next();

            if (portfolio.removeExistingProperty(propName)) {
                System.out.println(propName + " has has been removed from your portfolio.");
            } else {
                System.out.println(propName + " does not exist. Returning to the main menu.");
            }
        }
    }

    private void findProperty() {
        if (viewAllProperties()) {
            System.out.println("Enter the full address of the property you want to manage: ");
            String propName = input.next();

            for (Property p : portfolio.getPropertyList()) {
                if (propName.equals(p.getCivicAddress())) {
                    manageProperty(p);
                }
            }
        } else {
            System.out.println("Unable to complete request. Please add a property and try again");
        }
    }

    private void manageProperty(Property p) {
        String occupancyStatus = "";

        System.out.println("Property Details:");
        System.out.println("\tAddress: " + p.getCivicAddress());
        System.out.println("\tMarket Value: $" + p.getPropertyValue());
        System.out.println("\tDesired Monthly Rental Rate: $" + p.getMonthlyRent());
        if (p.getIsRented()) {
            occupancyStatus = "Occupied";
        } else {
            occupancyStatus = "Vacant";
        }
        System.out.println("\tOccupancy Status: " + occupancyStatus);

        System.out.println("To update this property's civic address, type 'civic'");
        System.out.println("To update this property's market value, type 'value'");
        System.out.println("To update your desired rental income, type 'income'");
        System.out.println("To manage this property's tenants, type 'tenants'");
        String managePropertyInput = input.next();
        processManageCommand(managePropertyInput, p);
    }

    private void processManageCommand(String managePropertyInput, Property selectedProperty) { // USE SWITCH HERE TOO
        if (managePropertyInput.equals("civic")) {
            updateCivicAddress(selectedProperty);
        } else if (managePropertyInput.equals("value")) {
            updateMarketValue(selectedProperty);
        } else if (managePropertyInput.equals("income")) {
            updateMonthlyRentalIncome(selectedProperty);
        } else if (managePropertyInput.equals("tenants")) {
            System.out.println(); // NEED TO WORK ON THIS STILL !!!
        } else {
            System.out.println("Invalid selection. Returning to the main menu.");
        }
    }

    private void updateCivicAddress(Property selectedProperty) {
        System.out.println("Enter the new civic address for this property: ");
        String newAddress = input.next();
        selectedProperty.setCivicAddress(newAddress);
    }

    private void updateMarketValue(Property selectedProperty) {
        System.out.println("Enter the new market value for this property: ");
        int newMarketValue = input.nextInt();
        selectedProperty.setPropertyValue(newMarketValue);
    }

    private void updateMonthlyRentalIncome(Property selectedProperty) {
        System.out.println("Enter your desired rental income for this property: ");
        int newRentalIncome = input.nextInt();
        selectedProperty.setMonthlyRent(newRentalIncome);

    }
}

// REFERENCE: This code was created with some references to the CPSC210 TellerApp project
