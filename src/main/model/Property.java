package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


// This class represents a property with an address, current market value, desired monthly income, a list of current
//      tenants, and a boolean value which represents the property's occupancy status

public class Property implements Writable {

    private String civicAddress;                // a property's civic address
    private int propertyValue;                  // a property's market value in Canadian dollars
    private int monthlyRent;                    // a property's desired monthly rental income in Canadian dollars
    private ArrayList<Tenant> tenantList;       // a list of tenants that currently occupy the property (if any)
    private boolean isRented;                   // current rental status (false = vacant, true = occupied)

    // REQUIRES: civilAddress has a non-zero length;
    //           propertyValue must be a non-zero positive integer,
    //           monthlyRent must be a non-zero positive integer
    // throws DuplicateCivicAddressException, NegativeValueException
    // EFFECTS:  Creates a new instance of type Property
    public Property(String civicAddress, int propertyValue, int monthlyRent) {
        this.civicAddress = civicAddress;
        this.propertyValue = propertyValue;
        this.monthlyRent = monthlyRent;
        tenantList = new ArrayList<Tenant>();
        isRented = false;
    }

    // REQUIRES: civilAddress has a non-zero length;
    //           propertyValue must be a non-zero positive integer,
    //           monthlyRent must be a non-zero positive integer
    // throws DuplicateCivicAddressException, NegativeValueException
    // EFFECTS:  Creates a new instance of type Property
    public Property(String civicAddress, int propertyValue, int monthlyRent, ArrayList<Tenant> tenantList) {
        this.civicAddress = civicAddress;
        this.propertyValue = propertyValue;
        this.monthlyRent = monthlyRent;
        this.tenantList = tenantList;

        if (!tenantList.isEmpty()) {
            isRented = true;
        } else {
            isRented = false;
        }
    }

    // REQUIRES: tenantName has a non-zero length, AND must be unique (no duplicates in tenantList)
    // MODIFIES: this
    // EFFECTS: Adds a unique new tenant with the given name to this. If addition is successful, return true - else
    //          return false.
    //          Also, if addition is successful and tenantList was empty at method call, switch isRented to true
    public boolean addNewTenant(String tenantName) {
        for (Tenant t : tenantList) {
            if (t.getTenantName().equals(tenantName)) {
                return false;
            }
        }
        tenantList.add(new Tenant(tenantName));
        if (!this.getIsRented()) {
            isRented = true;
        }
        logNewEvent(1, tenantName);
        return true;
    }

    // REQUIRES: tenantList has length > 0
    // MODIFIES: this
    // EFFECTS:  Removes a tenant with the given name from this. If removal is successful, return true - else
    //           return false
    //           If removal is successful and tenantList is now empty, switch isRented to false.
    public boolean removeTenant(String tenantName) {
        for (Tenant t : tenantList) {
            if (tenantName.equals(t.getTenantName())) {
                logNewEvent(2, tenantName);
                tenantList.remove(t);
                if (tenantList.isEmpty()) {
                    this.isRented = false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    // EFFECTS: converts a user's property into a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("civic address", civicAddress);
        json.put("market value", propertyValue);
        json.put("desired rent", monthlyRent);
        json.put("tenants", tenantsToJson());
        return json;
    }

    // EFFECTS: converts a property's tenant list into a JSON object
    private JSONArray tenantsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Tenant t : tenantList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: logs new event to EventLog
    public void logNewEvent(int caseNum, String subjectOfEvent) {

        String description = "";

        switch (caseNum) {
            case 1:
                description = "Added " + subjectOfEvent + " to " + civicAddress;
                break;
            case 2:
                description = "Removed " + subjectOfEvent + " from " + civicAddress;
                break;
            case 3:
                description = "Updated " + civicAddress + " to " + subjectOfEvent;
                break;
            case 4:
                description = "Updated property value for " + civicAddress;
                break;
            case 5:
                description = "Updated monthly rental amount for " + civicAddress;
                break;
        }
        EventLog.getInstance().logEvent(new Event(description));
    }

    // getters
    public String getCivicAddress() {
        return civicAddress;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public int getMonthlyRent() {
        return monthlyRent;
    }

    public ArrayList<Tenant> getTenantList() {
        return tenantList;
    }

    public boolean getIsRented() {
        return isRented;
    }

    // setters
    public void setCivicAddress(String newAddress) {
        logNewEvent(3, newAddress);
        this.civicAddress = newAddress;
    }

    public void setPropertyValue(int newValue) {
        logNewEvent(4, null);
        this.propertyValue = newValue;
    }

    public void setMonthlyRent(int newRent) {
        logNewEvent(5, null);
        this.monthlyRent = newRent;
    }

}


