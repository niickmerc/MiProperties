package persistence;


import model.PortfolioOG;
import model.PropertyOG;
import model.TenantOG;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            PortfolioOG portfolio = new PortfolioOG();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPortfolio() {
        try {
            PortfolioOG portfolio = new PortfolioOG();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPortfolio.json");
            writer.open();
            writer.write(portfolio);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPortfolio.json");
            portfolio = reader.read();
            assertEquals("My Portfolio", portfolio.getName());
            assertEquals(0, portfolio.getPropertyList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPortfolio() {
        try {
            PortfolioOG portfolio = new PortfolioOG();
            portfolio.addNewProperty("1234 Happy Drive", 2000000, 3000);
            portfolio.addNewProperty("1 University Avenue", 1500000, 2500);
            portfolio.getPropertyList().get(0).addNewTenant("Nick Mercuri");
            portfolio.getPropertyList().get(1).addNewTenant("Nick Mercuri");

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPortfolio.json");
            writer.open();
            writer.write(portfolio);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPortfolio.json");
            portfolio = reader.read();
            assertEquals("My Portfolio", portfolio.getName());
            List<PropertyOG> properties = portfolio.getPropertyList();
            assertEquals(2, properties.size());
            ArrayList<TenantOG> tenantsForTestProperties = new ArrayList<>();
            tenantsForTestProperties.add(new TenantOG("Nick Mercuri"));

            checkProperty("1234 Happy Drive", 2000000, 3000,
                    tenantsForTestProperties, properties.get(0)); // !!!
            checkProperty("1 University Avenue", 1500000, 2500,
                    tenantsForTestProperties, properties.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown!");
        }
    }
}