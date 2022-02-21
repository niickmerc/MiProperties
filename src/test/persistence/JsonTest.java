package persistence;

import model.Property;
import model.Tenant;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkProperty(String civic, int propValue, int rent, ArrayList<Tenant> tenants, Property newProp) {
        assertEquals(civic, newProp.getCivicAddress());
        assertEquals(propValue, newProp.getPropertyValue());
        assertEquals(rent, newProp.getMonthlyRent());
        assertEquals(tenants, newProp.getTenantList());
    }
}
