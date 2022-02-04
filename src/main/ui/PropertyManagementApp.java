package ui;

import model.Portfolio;

import java.util.Scanner;

// Property Management Application

public class PropertyManagementApp {

// User Story #1 - As a user, I want to be able to add a new property to my portfolio.
// User Story #2 - As a user, I want to be able to remove an existing property from my portfolio.
// User Story #3 - As a user, I want to be able to view all properties in my portfolio.
// User Story #4 - As a user, I want to be able to select a specific property and view it in more detail.

    private Portfolio portfolio;
    private Scanner input;


    public PropertyManagementApp() {

        System.out.println("Welcome to your Property Management App :)");
        runApp();
    }

    private void runApp() {
        boolean keepRunning = true;
        String command = null;

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
        System.out.println("Select from the following options: \n");
        System.out.println("To view all your properties: view");
        System.out.println("To add a new property: add");
        System.out.println("To manage a specific property: manage ");
        System.out.println("To remove an existing property: remove");
        System.out.println("To quit the application: quit");
    }

    private void processCommand(String userInput) {
        if (userInput.equals("view")) {
            viewAllProperties();
        } else if (userInput.equals("manage")) {
            manageProperties();
        } else if (userInput.equals("add")) {
            addNewProperty();
        } else if (userInput.equals("remove")) {
            removeExistingProperty();
        } else {
            System.out.println("Selection is invalid. Please try again.");
        }
    }

    private void viewAllProperties() {
        if (portfolio.getPropertyList().isEmpty()) {
            System.out.println("Your portfolio is empty. Please add a property and try again.");
        } else {
            portfolio.viewAllProperties();
        }
    }

    private void addNewProperty() {
        System.out.println("Enter the civic address of the property: ");
        String civicAddress = input.next();
        System.out.println("Enter the market value of the property: ");
        int propertyValue = input.nextInt();
        System.out.println("Enter the desired monthly rental income for the property: ");
        int monthlyRent = input.nextInt();

        portfolio.addNewProperty(civicAddress, propertyValue, monthlyRent);
    }

    private void removeExistingProperty() {
        viewAllProperties();

        System.out.println("Enter the address of the property you want to remove: ");
        String propName = input.next();

        if (portfolio.removeExistingProperty(propName)) {
            System.out.println("Property has been removed.");
        } else {
            System.out.println("Invalid entry. Returnint to the main manu.");
        }
    }

    private void manageProperties() {

    }




}
