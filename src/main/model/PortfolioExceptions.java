//package model;
//
//import model.exceptions.DuplicatePropertyException;
//import model.exceptions.NegativeValueException;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import persistence.Writable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//// This class represents a digital portfolio of rental properties
//public class PortfolioExceptions implements Writable {
//
//    private List<PropertyExceptions> propertyList;  // a list of properties that have been created by a user
//    private String name;
//
//    // EFFECTS: Constructs a new portfolio of properties
//    public PortfolioExceptions() {
//
//        propertyList = new ArrayList<>();
//        name = "My Portfolio";
//    }
//
//    // REQUIRES: civicAddress must be unique, propertyValue & monthlyRent must be non-zero positive integers
//    //           throws DuplicateCivicAddressException, NegativeValueException
//    // MODIFIES: this
//    // EFFECTS: Adds a new property with the given address, value, and desired monthly rent into the portfolio.
//    //          if successful, return true, else return false
//    public boolean addNewProperty(String civicAddress, int propertyValue, int monthlyRent) throws
//            DuplicatePropertyException, NegativeValueException {
//
//        if (propertyValue < 0 || monthlyRent < 0) {
//            throw new NegativeValueException("You cannot input a negative value here!");
//        }
//
//        PropertyExceptions newProperty = new PropertyExceptions(civicAddress, propertyValue, monthlyRent);
//
//        for (PropertyExceptions p : propertyList) {
//            if (p.equals(newProperty)) {
//                throw new DuplicatePropertyException("A property with this address exists in your portfolio!");
//            }
//        }
//
//        propertyList.add(newProperty);
//        return true;
//        }
//
//
//    // REQUIRES: civicAddress must be unique, propertyValue & monthlyRent must be non-zero positive integers
//    //           throws DuplicateCivicAddressException, NegativeValueException
//    // MODIFIES: this
//    // EFFECTS: Adds a new property with the given address, value, desired monthly rent, and list of tenants
//    //          into the portfolio. if successful, return true, else return false
//    public boolean addNewProperty(String civAddress, int value, int rent, ArrayList<Tenant> tenantList) {
//        PropertyOG propertyToAdd = loopAndReturnProperty(civAddress);
//
//        if (propertyToAdd == null) {
//            PropertyOG newProp = new PropertyOG(civAddress, propertyValue, monthlyRent, tenantList);
//            propertyList.add(newProp);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    // REQUIRES: propertyList has a size > 0
//    //           throws EmptyPropertyListException
//    // MODIFIES: this
//    // EFFECTS: Removes the property with the given name from the portfolio. if successful, return true, else false
//    public boolean removeExistingProperty(String civilAddress) {
//        PropertyOG propertyToRemove = loopAndReturnProperty(civilAddress);
//
//        if (propertyToRemove != null) {
//            propertyList.remove(propertyToRemove);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    // EFFECTS: Returns the sum of all market values from properties in a user's portfolio
//    public long getTotalPortfolioValue() {
//        long totalValue = 0;
//        for (PropertyOG p : propertyList) {
//            totalValue += p.getPropertyValue();
//        }
//        return totalValue;
//    }
//
//    // EFFECTS: Returns the sum of all rental cash flows from occupied properties
//    public long getTotalMonthlyRent() {
//        long totalMonthlyRent = 0;
//        for (PropertyOG p : propertyList) {
//            if (p.getIsRented()) {
//                totalMonthlyRent += p.getMonthlyRent();
//            }
//        }
//        return totalMonthlyRent;
//    }
//
//    // EFFECTS: Returns the percentage (out of 100) of properties within a user's portfolio that are occupied
//    public double getOccupancyRate() {
//        double vacantProperties = 0.0;
//        if (propertyList.isEmpty()) {
//            return vacantProperties;
//        } else {
//            for (PropertyOG p : propertyList) {
//                if (!p.getIsRented()) {
//                    vacantProperties++;
//                }
//            }
//        }
//        return 100 - Math.round((vacantProperties / propertyList.size()) * 100);
//    }
//
//    // EFFECTS: Returns the property with a specific address if it exists in the portfolio, otherwise returns null
//    public PropertyOG loopAndReturnProperty(String civicAddress) {
//        for (PropertyOG p : propertyList) {
//            if (p.getCivicAddress().equals(civicAddress)) {
//                return p;
//            }
//        }
//        return null;
//    }
//
//    // getters
//    public List<PropertyOG> getPropertyList() {
//        return propertyList;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("name", name);
//        json.put("properties", propertiesToJson());
//        return json;
//    }
//
//    // EFFECTS: returns properties in this portfolio as a JSON array
//    private JSONArray propertiesToJson() {
//        JSONArray jsonArray = new JSONArray();
//
//        for (PropertyOG p : propertyList) {
//            jsonArray.put(p.toJson());
//        }
//
//        return jsonArray;
//    }
//
//
//}
