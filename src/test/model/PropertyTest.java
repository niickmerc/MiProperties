package model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    Property p1;
    Property p2;
    Property p3;
    ArrayList<Tenant> testTenants = new ArrayList<>();

    @BeforeEach
    public void setup(){

        p1 = new Property("2133 University Avenue", 1250000, 3600);
        p2 = new Property("1234 Testing St", 1000000, 2500, new ArrayList<Tenant>());

        testTenants.add(new Tenant("John Doe"));

        p3 = new Property("415 Caledonia Ave", 2300000, 4000, testTenants);


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
    public void testConstructorAltConstructorEmptyTenantList() {
        assertEquals("1234 Testing St", p2.getCivicAddress());
        assertEquals(1000000, p2.getPropertyValue());
        assertEquals(2500, p2.getMonthlyRent());
        assertTrue(p2.getTenantList().isEmpty());
        assertFalse(p2.getIsRented());
    }

    @Test
    public void testConstructorAltConstructorWithSomeTenants() {
        assertEquals("415 Caledonia Ave", p3.getCivicAddress());
        assertEquals(2300000, p3.getPropertyValue());
        assertEquals(4000, p3.getMonthlyRent());
        assertFalse(p3.getTenantList().isEmpty());
        assertEquals(1, p3.getTenantList().size());
        assertTrue(p3.getIsRented());
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
