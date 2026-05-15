package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathologieTest {

    @Test
    public void testConstructeurInitialisation() {
        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs = {objectif1, objectif2};

        Pathologie pathologie = new Pathologie("Periostite", objectifs);

        assertEquals("Periostite", pathologie.getName());
        assertEquals("Calmer douleurs", pathologie.getObjectif()[0].getName());
        assertEquals("renforcer", pathologie.getObjectif()[1].getName());
    }

    @Test
    public void testConstructeurParDefaut() {
        Pathologie pathologie = new Pathologie();

        assertEquals("default", pathologie.getName());
        assertEquals(0, pathologie.getObjectif().length);
    }

    @Test
    public void testSetters() {
        Pathologie pathologie = new Pathologie();

        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs = {objectif1, objectif2};

        pathologie.setName("Periostite");
        pathologie.setObjectif(objectifs);

        assertEquals("Periostite", pathologie.getName());
        assertEquals("Calmer douleurs", pathologie.getObjectif()[0].getName());
        assertEquals("renforcer", pathologie.getObjectif()[1].getName());
    }

    @Test
    public void testToString() {
        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs = {objectif1, objectif2};

        Pathologie pathologie = new Pathologie("Periostite", objectifs);

        assertTrue(pathologie.toString().contains("name=Periostite"));
        assertTrue(pathologie.toString().contains("objectif="));
    }

    @Test
    public void testEquals() {
        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs1 = {objectif1, objectif2};

        Objectif objectif3 = new Objectif("Calmer douleurs");
        Objectif objectif4 = new Objectif("renforcer");
        Objectif[] objectifs2 = {objectif3, objectif4};

        Objectif objectif5 = new Objectif("mobilité");
        Objectif[] objectifs3 = {objectif5};

        Pathologie pathologie1 = new Pathologie("Periostite", objectifs1);
        Pathologie pathologie2 = new Pathologie("Periostite", objectifs2);
        Pathologie pathologie3 = new Pathologie("Tendinite", objectifs1);
        Pathologie pathologie4 = new Pathologie("Periostite", objectifs3);

        assertEquals(pathologie1, pathologie2);
        assertNotEquals(pathologie1, pathologie3);
        assertNotEquals(pathologie1, pathologie4);
    }
}
