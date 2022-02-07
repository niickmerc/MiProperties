package model;

import java.util.ArrayList;


// !!! Complete class-level comment

public class Property {

    private String civicAddress;                // a property's street address
    private int propertyValue;                  // a property's market value in Canadian dollars
    private int monthlyRent;                    // desired monthly rental income in Canadian dollars
    private ArrayList<Tenant> tenantList;       // a list of tenants that currently occupy the property (if any)
    private boolean isRented;                   // current rental status (false = vacant, true = occupied)

    // REQUIRES: civilAddress has a non-zero length;
    //           propertyValue must be a non-zero positive integer;
    //           monthlyRent must be a non-zero positive integer
    // EFFECTS:  Creates a new instance of type Property
    public Property(String civicAddress, int propertyValue, int monthlyRent) {
        this.civicAddress = civicAddress;
        this.propertyValue = propertyValue;
        this.monthlyRent = monthlyRent;
        tenantList = new ArrayList<Tenant>();
        isRented = false;
    }

    // REQUIRES: tenantName has a non-zero length, AND must be unique (no duplicates in tenantList)
    // MODIFIES: this
    // EFFECTS: Adds a unique new tenant with the given name to this. If addition is successful, return true - else
    //          return false.
    //          Also, if addition is successful and tenantList was empty at method call, switch isRented to true
    public boolean addNewTenant(String tenantName) {
        boolean isListEmpty = tenantList.isEmpty();
        for (Tenant t : tenantList) {
            if (t.getTenantName().equals(tenantName)) {
                return false;
            }
        }
        Tenant newTenant = new Tenant(tenantName);
        tenantList.add(newTenant);
        if (isListEmpty) {
            isRented = true;
        }
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
                tenantList.remove(t);
                if (tenantList.isEmpty()) {
                    this.isRented = false;
                }
                return true;
            }
        }
        return false;
    }

//    public void viewAllTenants() {
//        int count = 1;
//        for (Tenant t : tenantList) {
//            System.out.println("\tTenant #" + count++ + ": " + t.getTenantName());
//        }
//    }

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
        this.civicAddress = newAddress;
    }

    public void setPropertyValue(int newValue) {
        this.propertyValue = newValue;
    }

    public void setMonthlyRent(int newRent) {
        this.monthlyRent = newRent;
    }

}
