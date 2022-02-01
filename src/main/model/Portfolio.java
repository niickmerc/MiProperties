package model;

import java.util.ArrayList;

// !!! Complete class-level comment

public class Portfolio {

    private ArrayList<Property> propertyList;

    // EFFECTS: Constructs a new portfolio of properties
    public Portfolio() {
        // stub
    }

    // REQUIRES: civicAddress must be in a valid form, propertyValue & monthlyRent must be non-zero positive integers
    // MODIFIES: this
    // EFFECTS: Adds a new property with the given address, value, and desired monthly rent into the portfolio
    public void addNewProperty(String civicAddress, int propertyValue, int monthlyRent) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Removes the property with the given name from the portfolio
    public void removeExistingProperty(String civilAddress){
        // stub
    }

    // REQUIRES: propertyList is an ArrayList with length > 0
    // EFFECTS: prints all civic addresses within propertyList out on the console
    public void viewAllProperties() {
        // stub
    }

}
