package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// This class represents a digital portfolio of rental properties
public class Portfolio implements Writable {

    private List<Property> propertyList;  // a list of properties that have been created by a user
    private String name;

    // EFFECTS: Constructs a new portfolio of properties
    public Portfolio() {

        propertyList = new ArrayList<>();
        name = "My Portfolio";
    }

    // REQUIRES: civicAddress must be unique, propertyValue & monthlyRent must be non-zero positive integers
    //           throws DuplicateCivicAddressException, NegativeValueException
    // MODIFIES: this
    // EFFECTS: Adds a new property with the given address, value, and desired monthly rent into the portfolio.
    //          if successful, return true, else return false
    public boolean addNewProperty(String civicAddress, int propertyValue, int monthlyRent) {

        Property propertyToAdd = loopAndReturnProperty(civicAddress);

        if (propertyToAdd == null) {
            Property newProp = new Property(civicAddress, propertyValue, monthlyRent);
            propertyList.add(newProp);
            logNewEvent(1, civicAddress);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: civicAddress must be unique, propertyValue & monthlyRent must be non-zero positive integers
    //           throws DuplicateCivicAddressException, NegativeValueException
    // MODIFIES: this
    // EFFECTS: Adds a new property with the given address, value, desired monthly rent, and list of tenants
    //          into the portfolio. if successful, return true, else return false
    public boolean addNewProperty(String civAddress, int propertyValue, int monthlyRent, ArrayList<Tenant> tenantList) {
        Property propertyToAdd = loopAndReturnProperty(civAddress);

        if (propertyToAdd == null) {
            Property newProp = new Property(civAddress, propertyValue, monthlyRent, tenantList);
            propertyList.add(newProp);
            logNewEvent(1, civAddress);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: propertyList has a size > 0
    //           throws EmptyPropertyListException
    // MODIFIES: this
    // EFFECTS: Removes the property with the given name from the portfolio. if successful, return true, else false
    public boolean removeExistingProperty(String civicAddress) {
        Property propertyToRemove = loopAndReturnProperty(civicAddress);

        if (propertyToRemove != null) {
            propertyList.remove(propertyToRemove);
            logNewEvent(2, civicAddress);
            return true;
        } else {
            return false;
        }
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

    // EFFECTS: Returns the percentage (out of 100) of properties within a user's portfolio that are occupied
    public double getOccupancyRate() {
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
        return 100 - Math.round((vacantProperties / propertyList.size()) * 100);
    }

    // EFFECTS: Returns the property with a specific address if it exists in the portfolio, otherwise returns null
    public Property loopAndReturnProperty(String civicAddress) {
        for (Property p : propertyList) {
            if (p.getCivicAddress().equals(civicAddress)) {
                return p;
            }
        }
        return null;
    }

    // getters
    public List<Property> getPropertyList() {
        return propertyList;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: Converts a user's portfolio into JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("properties", propertiesToJson());
        return json;
    }

    // EFFECTS: returns properties in this portfolio as a JSON array
    private JSONArray propertiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Property p : propertyList) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: logs new event to EventLog
    public void logNewEvent(int caseNum, String civicAddress) {

        String description = "";

        switch (caseNum) {
            case 1:
                description = "Added " + civicAddress + " to the portfolio";
                break;
            case 2:
                description = "Removed " + civicAddress + " from the portfolio";
                break;
        }

        EventLog.getInstance().logEvent(new Event(description));
    }


}
