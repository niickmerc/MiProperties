package persistence;

import model.PropertyOG;
import model.TenantOG;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    protected void checkProperty(String civic, int propValue, int rent, ArrayList<TenantOG> tenants, PropertyOG newProp) {
        assertEquals(civic, newProp.getCivicAddress());
        assertEquals(propValue, newProp.getPropertyValue());
        assertEquals(rent, newProp.getMonthlyRent());
        assertTrue(checkTenantList(tenants, newProp.getTenantList()));
    }

    protected boolean checkTenantList(ArrayList<TenantOG> list1, ArrayList<TenantOG> list2) {
        for(int i = 0; i < list2.size(); i++) {
            if(!list1.get(i).getTenantName().equals(list2.get(i).getTenantName())) {
                return false;
            }
        }
        return true;
    }
}
