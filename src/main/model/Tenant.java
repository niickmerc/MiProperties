package model;


// future idea: implement two subclasses: ResidentialTenant and CommercialTenant

import org.json.JSONObject;
import persistence.Writable;

// This class represents a rental tenant with a full name
public class Tenant implements Writable {

    private String tenantName;  // a tenant's full legal name

    public Tenant(String name) {
        tenantName = name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tenant name", tenantName);
        return json;
    }

    // getters
    public String getTenantName() {
        return tenantName;
    }

    // setters
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}
