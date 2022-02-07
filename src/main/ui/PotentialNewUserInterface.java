package ui;

import model.Portfolio;
import model.Property;

import java.util.Scanner;


public class PotentialNewUserInterface {
    private Portfolio portfolio;
    private Scanner input;

    public PotentialNewUserInterface() {
        System.out.println("Ayo g thanks for pulling up.");
        runApp();
    }

    // EFFECTS: Instantiates the application environment ???????
    private void runApp() {

        boolean runProgram = true;
        String userCommand;

        initializeFields();

        while (runProgram) {
            System.out.println();
            applicationMenu();
            userCommand = input.next();
            userCommand = userCommand.toLowerCase();

            if (userCommand.equals("quit")) {
                runProgram = false;
            } else {
                processCommand(userCommand);
            }
        }
        System.out.println("Thanks for stopping by!");
    }

    // EFFECTS: Instantiates required objects and utilities
    private void initializeFields() {
        portfolio = new Portfolio();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: prints all possible user options out on the console
    private void applicationMenu() {
        viewAllProperties();
        System.out.println(); // Line Break
        System.out.println("To add a new property, type 'add'");
        System.out.println("To remove an existing property, type 'remove'");
        System.out.println("To manage a specific property, type 'manage'");
        System.out.println("For summary statistics on your portfolio, type 'summary' (TODO)");
        System.out.println("To close the application, type 'quit'");
        System.out.print("Type your answer here: ");
    }

    // EFFECTS: processes user input according to the three possible options, otherwise returns an error message
    private void processCommand(String userInput) {
        if (userInput.equals("add")) {
            addNewProperty();
        } else if (userInput.equals("remove")) {
            removeExistingProperty();
        } else if (userInput.equals("manage")) {
            manageExistingProperty();
        } else if (userInput.equals("summary")) {
            System.out.println("Coming soon :)");
        } else {
            System.out.println("\nUhhhh I dunno what to do with this. Please try again.");
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
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
            System.out.println("\nInvalid selection. Returning to the main menu.");
            System.out.println("-----------------------------------------------------------------");
        }
    }

    // EFFECTS: returns true and prints contents of propertyList if not empty - else returns false and prints error
    private boolean viewAllProperties() {
        if (portfolio.getPropertyList().isEmpty()) {
            System.out.println("Your portfolio is currently empty.");
            return false;
        } else {
            System.out.println("Your Portfolio:");
            portfolio.viewAllProperties();
            return true;
        }
    }

    // REQUIRES:
    // MODIFIES: portfolio
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

    // REQUIRES: ???
    // MODIFIES: Do I write "this" here?
    // EFFECTS: ???
    private void removeExistingProperty() {
        System.out.println();
        if (viewAllProperties()) {
            System.out.print("\nEnter the address of the property you wish to remove: ");
            String userInput = input.next();
            if (portfolio.removeExistingProperty(userInput)) {
                System.out.println("\n" + userInput + " has been removed from your portfolio!");
                System.out.println("-----------------------------------------------------------------");
            } else {
                System.out.println("\n" + userInput + " does not exist in your portfolio. Please try again.");
                System.out.println("-----------------------------------------------------------------");
            }
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void manageExistingProperty() {
        System.out.println();
        if (viewAllProperties()) {
            System.out.println();
            findProperty();
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void findProperty() {
        System.out.print("Enter the address of the property you want to manage: ");
        String propName = input.next();

        for (Property p : portfolio.getPropertyList()) {
            if (propName.equals(p.getCivicAddress())) {
                displayPropertyInfo(p);
                return;
            }
        }
        System.out.println(propName + " does not exist in your portfolio.");
        System.out.println("-----------------------------------------------------------------");
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void displayPropertyInfo(Property selectedProperty) {
        String occupancyStatus = "";
        System.out.println();
        System.out.println("Details for " + selectedProperty.getCivicAddress() +  ": ");
        System.out.println("\tMarket Value: $" + selectedProperty.getPropertyValue());
        System.out.println("\tDesired Monthly Rental Rate: $" + selectedProperty.getMonthlyRent());
        if (selectedProperty.getIsRented()) {
            occupancyStatus = "Occupied";
        } else {
            occupancyStatus = "Vacant";
        }
        System.out.println("\tOccupancy Status: " + occupancyStatus);

        managePropertyMenu(selectedProperty);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void managePropertyMenu(Property selectedProperty) {

        System.out.println("\nTo update this property's address, type 'civic'");
        System.out.println("To update this property's market value, type 'value'");
        System.out.println("To update this property's desired rental income, type 'income'");
        System.out.println("To manage this property's tenants, type 'tenants'");
        System.out.println("To return to the main menu, type 'main'");
        System.out.print("Type your answer here: ");
        String userInput = input.next();

        processCommand(userInput, selectedProperty);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void updateCivicAddress(Property selectedProperty) {
        System.out.println("Enter the new civic address for this property: ");
        String newAddress = input.next();
        selectedProperty.setCivicAddress(newAddress);
        System.out.println("\nAddress for " + selectedProperty.getCivicAddress() +  " updated!");
        System.out.println("-----------------------------------------------------------------");
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void updateMarketValue(Property selectedProperty) {
        System.out.println("Enter the new market value for this property: ");
        int newMarketValue = input.nextInt();
        selectedProperty.setPropertyValue(newMarketValue);
        System.out.println("\nMarket value for " + selectedProperty.getCivicAddress() + " updated!");
        System.out.println("-----------------------------------------------------------------");
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void updateMonthlyRentalIncome(Property selectedProperty) {
        System.out.println("Enter your desired rental income for this property: ");
        int newRentalIncome = input.nextInt();
        selectedProperty.setMonthlyRent(newRentalIncome);
        System.out.println("\nDesired rental income for " + selectedProperty.getCivicAddress() + " updated!");
        System.out.println("-----------------------------------------------------------------");
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void manageTenantsMenu(Property selectedProperty) {
        System.out.println();
        if (selectedProperty.getTenantList().isEmpty()) {
            System.out.println(selectedProperty.getCivicAddress() + " is currently vacant.");
        } else {
            System.out.println("Tenants for " + selectedProperty.getCivicAddress());
            selectedProperty.viewAllTenants();
        }
        System.out.println();
        // !!! add a loop here for multiple tenant additions / removals
        System.out.println("To add a new tenant, type 'add'");
        System.out.println("To remove an existing tenant, type 'remove'");
        System.out.println("To return to the main menu, type 'main'");
        System.out.print("Type your answer here: ");
        String userInput = input.next();
        processCommand(userInput, selectedProperty);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void addTenants(Property selectedProperty) {
        System.out.print("Enter your tenant's name: ");
        String tenantToAdd = input.next();
        selectedProperty.addNewTenant(tenantToAdd);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void removeTenants(Property selectedProperty) {
        System.out.print("Enter your tenant's name: ");
        String tenantToRemove = input.next();
        selectedProperty.removeTenant(tenantToRemove);
    }


}
