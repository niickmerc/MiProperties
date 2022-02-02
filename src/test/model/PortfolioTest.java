package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PortfolioTest {

    Portfolio testPortfolio;
    Property p1;
    Property p2;
    Property p3;
    Property p4;

    @BeforeEach
    public void setup(){

        testPortfolio = new Portfolio();
        p1 = new Property("2133 University Avenue", 1250000, 3600);
        p2 = new Property("1703-1088 Richards St", 650000, 2200);
        p3 = new Property("213-2130 West Broadway St", 885000, 2950);
        p4 = new Property("1027 Cornwall Avenue", 4500000, 5000);
    }

    @Test
    public void testConstructor() {
        assertTrue(testPortfolio.getPropertyList().isEmpty());
    }

    @Test
    public void testAddNewPropertySingleEntry() {
        assertTrue(testPortfolio.getPropertyList().isEmpty());
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        assertEquals(1, testPortfolio.getPropertyList().size());
    }

    @Test
    public void testAddNewPropertyMultipleEntries() {
        assertTrue(testPortfolio.getPropertyList().isEmpty());
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        testPortfolio.addNewProperty(p2.getCivicAddress(), p2.getPropertyValue(), p2.getMonthlyRent());
        assertEquals(2, testPortfolio.getPropertyList().size());
    }

    @Test
    public void testAddNewPropertyDuplicateProperty() {
        assertTrue(testPortfolio.getPropertyList().isEmpty());
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        assertEquals(1, testPortfolio.getPropertyList().size());
    }

    @Test
    public void testRemoveExistingPropertyOnEmptyList(){
        assertTrue(testPortfolio.getPropertyList().isEmpty());
        assertFalse(testPortfolio.removeExistingProperty(p1.getCivicAddress()));
    }

    @Test
    public void testRemoveExistingPropertySingleProperty() {
        assertTrue(testPortfolio.getPropertyList().isEmpty());
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        testPortfolio.removeExistingProperty(p1.getCivicAddress());
        assertTrue(testPortfolio.getPropertyList().isEmpty());
    }

    @Test
    public void testRemoveExistingPropertyMultipleProperties() {
        assertTrue(testPortfolio.getPropertyList().isEmpty());
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        testPortfolio.addNewProperty(p2.getCivicAddress(), p2.getPropertyValue(), p2.getMonthlyRent());
        testPortfolio.addNewProperty(p3.getCivicAddress(), p3.getPropertyValue(), p3.getMonthlyRent());

        assertEquals(3, testPortfolio.getPropertyList().size());

        assertTrue(testPortfolio.removeExistingProperty(p3.getCivicAddress()));
        assertTrue(testPortfolio.removeExistingProperty(p1.getCivicAddress()));

        assertEquals(1, testPortfolio.getPropertyList().size());
    }

}
