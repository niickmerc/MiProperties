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
        testPortfolio.addNewProperty(p2.getCivicAddress(), p2.getPropertyValue(), p2.getMonthlyRent());
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        assertEquals(2, testPortfolio.getPropertyList().size());
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

//    @Test
//    public void testSummaryStatsMethods() {
//        assertEquals(0, testPortfolio.getTotalPortfolioValue());
//        assertEquals(0, testPortfolio.getTotalMonthlyRent());
//        assertEquals(0, testPortfolio.getVacancyRate());
//
//        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
//        testPortfolio.addNewProperty(p2.getCivicAddress(), p2.getPropertyValue(), p2.getMonthlyRent());
//        testPortfolio.addNewProperty(p3.getCivicAddress(), p3.getPropertyValue(), p3.getMonthlyRent());
//
//        assertEquals(p1.getPropertyValue() + p2.getPropertyValue() + p3.getPropertyValue(),
//                testPortfolio.getTotalPortfolioValue());
//        assertEquals(0, testPortfolio.getTotalMonthlyRent());
//        assertEquals(100.0, testPortfolio.getVacancyRate());
//
//        testPortfolio.getPropertyList().get(0).addNewTenant("John Doe");
//        assertEquals(((2.0/3.0) * 100), testPortfolio.getVacancyRate());
//
//        testPortfolio.getPropertyList().get(1).addNewTenant("Jane Doe");
//        assertEquals(1.0/3.0 * 100, testPortfolio.getVacancyRate());
//
//        testPortfolio.getPropertyList().get(2).addNewTenant("Jane Doe");
//        assertEquals(0, testPortfolio.getVacancyRate());
//
//        assertEquals(p1.getMonthlyRent() + p2.getMonthlyRent() + p3.getMonthlyRent(),
//                testPortfolio.getTotalMonthlyRent());
//    }

    @Test
    public void testGetTotalPortFolioValueEmptyPortfolio() {
        assertEquals(0, testPortfolio.getTotalPortfolioValue());
    }

    @Test
    public void testGetTotalPortfolioValueMultipleProperties() {
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        testPortfolio.addNewProperty(p2.getCivicAddress(), p2.getPropertyValue(), p2.getMonthlyRent());
        testPortfolio.addNewProperty(p3.getCivicAddress(), p3.getPropertyValue(), p3.getMonthlyRent());
        assertEquals(p1.getPropertyValue() + p2.getPropertyValue() + p3.getPropertyValue(),
                testPortfolio.getTotalPortfolioValue());
    }

    @Test
    public void testGetTotalMonthlyRentEmptyPortfolio() {
        assertEquals(0, testPortfolio.getTotalMonthlyRent());
    }

    @Test
    public void testGetTotalMonthlyRentMultipleVacantProperties() {
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        testPortfolio.addNewProperty(p2.getCivicAddress(), p2.getPropertyValue(), p2.getMonthlyRent());
        testPortfolio.addNewProperty(p3.getCivicAddress(), p3.getPropertyValue(), p3.getMonthlyRent());

        assertEquals(0, testPortfolio.getTotalMonthlyRent());
    }

    @Test
    public void testGetTotalMonthlyRentMultipleOccupiedProperties() {
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        testPortfolio.addNewProperty(p2.getCivicAddress(), p2.getPropertyValue(), p2.getMonthlyRent());
        testPortfolio.addNewProperty(p3.getCivicAddress(), p3.getPropertyValue(), p3.getMonthlyRent());

        assertEquals(0, testPortfolio.getTotalMonthlyRent());

        testPortfolio.getPropertyList().get(0).addNewTenant("John Doe");
        testPortfolio.getPropertyList().get(1).addNewTenant("Jane Doe");
        testPortfolio.getPropertyList().get(2).addNewTenant("Jane Doe");

        assertEquals(p1.getMonthlyRent() + p2.getMonthlyRent() + p3.getMonthlyRent(),
                testPortfolio.getTotalMonthlyRent());
    }

    @Test
    public void testGetVacancyRatesEmptyPortfolio() {
        assertEquals(0, testPortfolio.getVacancyRate());
    }

    @Test
    public void testGetVacancyRatesMultipleVacantProperties() {
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        testPortfolio.addNewProperty(p2.getCivicAddress(), p2.getPropertyValue(), p2.getMonthlyRent());
        testPortfolio.addNewProperty(p3.getCivicAddress(), p3.getPropertyValue(), p3.getMonthlyRent());

        assertEquals(100.0, testPortfolio.getVacancyRate());
    }

    @Test
    public void testGetVacancyRatesMultipleProperties() {
        testPortfolio.addNewProperty(p1.getCivicAddress(), p1.getPropertyValue(), p1.getMonthlyRent());
        testPortfolio.addNewProperty(p2.getCivicAddress(), p2.getPropertyValue(), p2.getMonthlyRent());
        testPortfolio.addNewProperty(p3.getCivicAddress(), p3.getPropertyValue(), p3.getMonthlyRent());

        assertEquals(100.0, testPortfolio.getVacancyRate());

        testPortfolio.getPropertyList().get(0).addNewTenant("John Doe");
        assertEquals(((2.0/3.0) * 100), testPortfolio.getVacancyRate());

        testPortfolio.getPropertyList().get(1).addNewTenant("Jane Doe");
        assertEquals(1.0/3.0 * 100, testPortfolio.getVacancyRate());

        testPortfolio.getPropertyList().get(2).addNewTenant("Jane Doe");
        assertEquals(0, testPortfolio.getVacancyRate());
    }
}
