package model;

import java.util.ArrayList;

// !!! Complete class-level comment

public class Portfolio {

    private ArrayList<Property> propertyList;

    // EFFECTS: Constructs a new portfolio of properties
    public Portfolio() {
        propertyList = new ArrayList<Property>();
    }

    // REQUIRES: civicAddress must be in a valid form, propertyValue & monthlyRent must be non-zero positive integers
    // MODIFIES: this
    // EFFECTS: Adds a new property with the given address, value, and desired monthly rent into the portfolio.
    //          if successful, return true, else return false
    public boolean addNewProperty(String civicAddress, int propertyValue, int monthlyRent) {

        for (Property p : propertyList) {
            if (p.getCivicAddress().equals(civicAddress)) {
                return false;
            }
        }

        Property newProp = new Property(civicAddress, propertyValue, monthlyRent);
        propertyList.add(newProp);
        return true;
    }

    // REQUIRES: propertyList has a size > 0 AND property with given civic address exists in propertyList
    // MODIFIES: this
    // EFFECTS: Removes the property with the given name from the portfolio. if successful, return true, else false
    public boolean removeExistingProperty(String civilAddress) {

        for (Property p : propertyList) {
            if (p.getCivicAddress().equals(civilAddress)) {
                propertyList.remove(p);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: propertyList is an ArrayList with length > 0
    // EFFECTS: prints all civic addresses within propertyList out on the console
    public void viewAllProperties() {
        for (Property p : propertyList) {
            System.out.println(p.getCivicAddress());
        }
    }

    public void manageSpecificProperty() {

    }

    // getters
    public ArrayList<Property> getPropertyList() {
        return propertyList;
    }


}
