package model;

import java.util.ArrayList;

public class Property {

    private String address;
    private int propertyValue;
    private int monthlyRent;
    private ArrayList<Tenant> tenantList;
    private boolean rentalStatus;

    // REQUIRES: address must be of valid format ("XXXX StreetName")
    //           propertyValue and monthlyRent must be non-zero positive integers
    // EFFECTS:  Creates a new instance of type Property
    public Property(String address, int propertyValue, int monthlyRent) {

    }

    // getters
    public String getAddress() {
        return address;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public int getMonthlyRent() {
        return monthlyRent;
    }

    public ArrayList getTenantList() {
        return tenantList;
    }

    // setters
    public void setAddress(String newAddress) {
        this.address = newAddress;
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

    public boolean changeRentalStatus(){

    }

    public void chargeRentToTenant(){

    }


}
