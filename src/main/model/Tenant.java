package model;


import org.json.JSONObject;
import persistence.Writable;

// This class represents a rental tenant with a full name
public class Tenant implements Writable {
    private String tenantName;  // a tenant's full legal name

    // EFFECTS: creates a new tenant with a given name
    public Tenant(String name) {

        tenantName = name;
    }

    @Override
    // EFFECTS: converts a property's tenant into JSON format
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
