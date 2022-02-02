package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    Property p1;
//    Property p2;
//    Property p3;
//    Property p4;

    @BeforeEach
    public void setup(){

        p1 = new Property("2133 University Avenue", 1250000, 3600);
        //        p2 = new Property("1703-1088 Richards St", 650000, 2200);
        //        p3 = new Property("213-2130 West Broadway St", 885000, 2950);
        //        p4 = new Property("1027 Cornwall Avenue", 4500000, 5000);

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
    public void testAddTenantNoDuplicates(){
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
    public void testRemoveTenantMultiple() {

        addFirstTenant("John Doe");
        p1.addNewTenant("Jane Doe");
        p1.addNewTenant("John Smith");

    }

    private void addFirstTenant(String name) {
        assertTrue(p1.getTenantList().isEmpty());
        assertTrue(p1.addNewTenant(name));
        assertEquals(1, p1.getTenantList().size());
        assertTrue(p1.getIsRented());
    }




}
