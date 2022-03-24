package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TenantTest {

    TenantOG testTenant;

    @BeforeEach
    public void setup() {
        testTenant = new TenantOG("John Doe");
    }

    @Test
    public void testConstructor() {
        assertEquals("John Doe", testTenant.getTenantName());
    }

    @Test
    public void testSetter() {
        testTenant.setTenantName("Jane Doe");
        assertEquals("Jane Doe", testTenant.getTenantName());
    }
}
