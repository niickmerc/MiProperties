package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    Property p1;

    @BeforeEach
    public void setup(){
        p1 = new Property("2133 University Avenue", 1250000, 3600);
    }

    @Test
    public void testConstructor() {
        assertEquals("2133 University Avenue", p1.getCivicAddress());
        assertEquals(1250000, p1.getPropertyValue());
        assertEquals(3600, p1.getMonthlyRent());
        assertFalse(p1.getIsRented());
        assertTrue(p1.getTenantList().isEmpty());
    }

    @Test
    public void testSetters() {
        p1.setCivicAddress("1234 Happy Drive");
        p1.setPropertyValue(1000000);
        p1.setMonthlyRent(3000);

        assertEquals("1234 Happy Drive", p1.getCivicAddress());
        assertEquals(1000000, p1.getPropertyValue());
        assertEquals(3000, p1.getMonthlyRent());
    }

    @Test
    public void testAddTenantOnlyOne() {
        addFirstTenant("Jon Doe");
    }

    @Test
    public void testAddMultipleTenants() {
        addFirstTenant("Jon Doe");
        p1.addNewTenant("Jane Doe");
        assertEquals(2, p1.getTenantList().size());
    }

    @Test
    public void testAddTenantNoDuplicates() {
        addFirstTenant("John Doe");
        p1.addNewTenant("John Doe");
        assertEquals(1, p1.getTenantList().size());
    }

    @Test
    public void testRemoveTenantOnlyOne() {
        addFirstTenant("John Doe");
        p1.removeTenant("John Doe");
        assertTrue(p1.getTenantList().isEmpty());
        assertFalse(p1.getIsRented());
    }

    @Test
    public void testRemoveTenantNotPresent() {
        addFirstTenant("John Doe");
        assertFalse(p1.removeTenant("Jane Doe"));
        assertEquals(1, p1.getTenantList().size());
    }

    @Test
    public void testRemoveTenantMultipleTenants() {
        addFirstTenant("John Doe");
        p1.addNewTenant("Jane Doe");
        p1.addNewTenant("John Smith");
        p1.removeTenant("John Doe");
        p1.removeTenant("John Smith");
        assertEquals(1, p1.getTenantList().size());
    }

    private void addFirstTenant(String name) {
        assertTrue(p1.getTenantList().isEmpty());
        assertTrue(p1.addNewTenant(name));
        assertEquals(1, p1.getTenantList().size());
        assertTrue(p1.getIsRented());
    }




}
