package model;

import java.util.ArrayList;


// !!! Complete class-level comment

public class Property {

    private String civicAddress;
    private int propertyValue;
    private int monthlyRent;
    private ArrayList<Tenant> tenantList;
    private boolean rentalStatus = false;

    // REQUIRES: civilAddress must be of valid form, propertyValue and monthlyRent
    //           must be non-zero positive integers
    // EFFECTS:  Creates a new instance of type Property
    public Property(String civicAddress, int propertyValue, int monthlyRent) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Adds a new tenant to a property's list of tenants
    public void addNewTenant(String tenantName) {
        // stub
    }

    // REQUIRES: tenantList is not empty
    // MODIFIES: this
    // EFFECTS: Removes a tenant with the given name from a property's list of tenants
    public void removeTenant(String tenantName) {
        // stub
    }











  /*
    // getters
    public String getCivicAddress() { return civicAddress; }
    public int getPropertyValue() { return propertyValue; }
    public int getMonthlyRent() { return monthlyRent; }
    public ArrayList getTenantList() {
        return tenantList;
    }

    // setters
    public void setCivicAddress(String newAddress) {
        this.civicAddress = newAddress;
    }
    public void setPropertyValue(int newValue) {
        this.propertyValue = newValue;
    }
    public void setMonthlyRent(int newRent) {
        this.monthlyRent = newRent;
    }
    public void assignTenant(Tenant t) {
        this.tenantList.add(t);
    }
    public boolean removeTenant(String tenantName) {
        return false;
    }

   */
}
