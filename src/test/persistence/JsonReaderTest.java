package persistence;

import model.PortfolioOG;
import model.PropertyOG;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderFileDoesntExist() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PortfolioOG portfolio = reader.read();
            fail("Expected IOException");
        } catch (IOException e) {
            // Expected result
        }
    }

    @Test
    void testReaderEmptyPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPortfolio.json");
        try {
            PortfolioOG emptyPortfolio = reader.read();
            assertEquals("My Portfolio", emptyPortfolio.getName());
            assertEquals(0, emptyPortfolio.getPropertyList().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderWorkingPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderWorkingPortfolio.json");
        try {
          PortfolioOG workingPortfolio = reader.read();
          assertEquals("My Portfolio", workingPortfolio.getName());
          List<PropertyOG> properties = workingPortfolio.getPropertyList();
          assertEquals(3, properties.size());

          for(int i = 0; i < properties.size(); i++) {

              switch (i) {
                  case 0: checkProperty("1234 Happy Drive", 2000000,
                          3000, properties.get(0).getTenantList(), properties.get(0));
                  case 1: checkProperty("1 University Avenue", 1500000,
                          2500, properties.get(1).getTenantList(), properties.get(1));
                  case 2: checkProperty("2311 McDonald Street", 800000,
                          2200, properties.get(2).getTenantList(), properties.get(2));
                  default:
              }
          }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
