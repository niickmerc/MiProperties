package model;

import java.util.ArrayList;

public class Property {

    private String address;
    private int propertyValue;
    private int monthlyRent;
    private ArrayList<Tenant> tenants;

    // REQUIRES: address must be of valid format ("XXXX StreetName")
    //           propertyValue and monthlyRent must be non-zero positive integers
    // EFFECTS:  Creates a new instance of type Property
    public Property(String address, int propertyValue, int monthlyRent) {

        this.address = address;
        this.propertyValue = propertyValue;
        this.monthlyRent = monthlyRent;
        tenants = new ArrayList<Tenant>();
    }


}
