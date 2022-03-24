package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TenantTest {

    Tenant testTenant;

    @BeforeEach
    public void setup() {
        testTenant = new Tenant("John Doe");
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
