package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ObjectifTest {

    @Test
    public void testConstructeurInitialisation() {
        Objectif objectif = new Objectif("Calmer douleurs");

        assertEquals("Calmer douleurs", objectif.getName());
    }

    @Test
    public void testConstructeurParDefaut() {
        Objectif objectif = new Objectif();

        assertEquals("default", objectif.getName());
    }

    @Test
    public void testSetters() {
        Objectif objectif = new Objectif();

        objectif.setName("renforcer");

        assertEquals("renforcer", objectif.getName());
    }

    @Test
    public void testEquals() {
        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("Calmer douleurs");
        Objectif objectif3 = new Objectif("renforcer");

        assertEquals(objectif1, objectif2);
        assertNotEquals(objectif1, objectif3);
    }

    @Test
    public void testToString() {
        Objectif objectif = new Objectif("Calmer douleurs");

        assertEquals("Objectif{name='Calmer douleurs'}", objectif.toString());
    }
}