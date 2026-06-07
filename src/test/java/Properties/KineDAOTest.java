
// src/test/java/Properties/KineDAOTest.java
package Properties;

import entity.Kine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KineDAOTest {
    private KineDAO kineDAO;

    @BeforeEach
    public void setUp() {
        kineDAO = new KineDAO();
    }

    @Test
    public void testCheckPassword() {
        assertTrue(kineDAO.checkPassword("admin", "admin"), "Login admin devrait réussir");
    }

    @Test
    public void testWrongPassword() {
        assertFalse(kineDAO.checkPassword("admin", "wrong"), "Mauvais mot de passe devrait échouer");
    }

    @Test
    public void testSaveKine() {
        Kine newKine = new Kine("Nouveau", "Kine", "Cardio", "99999", "/default.jpg");
        kineDAO.save("newkine", "newkine123", newKine);

        assertTrue(kineDAO.existsByUsername("newkine"), "Nouveau kine devrait existir");
    }
}