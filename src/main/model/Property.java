package model;

import java.util.ArrayList;


// !!! Complete class-level comment

public class Property {

    private String civicAddress;
    private int propertyValue;
    private int monthlyRent;
    private ArrayList<Tenant> tenantList;
    private boolean isRented = false;

    // REQUIRES: civilAddress has a non-zero length;
    //           propertyValue must be a non-zero positive integer;
    //           monthlyRent must be a non-zero positive integer
    // EFFECTS:  Creates a new instance of type Property
    public Property(String civicAddress, int propertyValue, int monthlyRent) {
        // stub
    }

    // REQUIRES: tenantName has a non-zero length
    // MODIFIES: this
    // EFFECTS: Adds a new tenant to a property's list of tenants.
    //          If tenantList was empty before insertion, switch isRented to true
    public void addNewTenant(String tenantName) {
        // stub
    }

    // REQUIRES: tenantList has length > 0
    // MODIFIES: this
    // EFFECTS: Removes a tenant with the given name from a property's list of tenants.
    //          If tenantList is empty after removal, switch isRented to false.
    public void removeTenant(String tenantName) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: charges tenants one month's rent and increases total income generated
    public void chargeRent() {

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

    // setters
    public void setCivicAddress(String newAddress) {
        this.civicAddress = newAddress;
    }

    public void updatePropertyValue(int newValue) {
        this.propertyValue = newValue;
    }

    public void updateMonthlyRent(int newRent) {
        this.monthlyRent = newRent;
    }

}
