package model;

// !!! Complete class-level comment
// future idea: implement two subclasses: ResidentialTenant and CommercialTenant

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
