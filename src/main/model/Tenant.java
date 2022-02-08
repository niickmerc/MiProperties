package model;


// future idea: implement two subclasses: ResidentialTenant and CommercialTenant

// This class represents a rental tenant with a full name
public class Tenant {

    private String tenantName;

    public Tenant(String name) {
        tenantName = name;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}
