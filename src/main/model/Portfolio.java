package model;

import java.util.ArrayList;
import java.util.List;


// This class represents a digital portfolio of rental properties
public class Portfolio {

    private List<Property> propertyList;

    // EFFECTS: Constructs a new portfolio of properties
    public Portfolio() {
        propertyList = new ArrayList<>();
    }

    // REQUIRES: civicAddress must be unique, propertyValue & monthlyRent must be non-zero positive integers
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

    // EFFECTS: Returns the sum of all market values from properties in a user's portfolio
    public long getTotalPortfolioValue() {
        long totalValue = 0;
        for (Property p : propertyList) {
            totalValue += p.getPropertyValue();
        }
        return totalValue;
    }

    // EFFECTS: Returns the sum of all rental cash flows from occupied properties
    public long getTotalMonthlyRent() {
        long totalMonthlyRent = 0;
        for (Property p : propertyList) {
            if (p.getIsRented()) {
                totalMonthlyRent += p.getMonthlyRent();
            }
        }
        return totalMonthlyRent;
    }

    // EFFECTS: Returns the percentage (out of 100) of properties within a user's portfolio that are vacant
    public double getVacancyRate() {
        double vacantProperties = 0.0;
        if (propertyList.isEmpty()) {
            return vacantProperties;
        } else {
            for (Property p : propertyList) {
                if (!p.getIsRented()) {
                    vacantProperties++;
                }
            }
        }
        return (vacantProperties / propertyList.size()) * 100;
    }

    // getters
    public List<Property> getPropertyList() {
        return propertyList;
    }


}
