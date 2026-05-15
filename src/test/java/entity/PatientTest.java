package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PatientTest{
    @Test
    public void testConstructeurinitialisation(){
        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs = {objectif1, objectif2};
        Pathologie pathologie = new Pathologie("Periostite", objectifs);
        Patient patient = new Patient("Shala", "Nderim", "Solidaris", pathologie);

        assertEquals("Shala", patient.getName());
        assertEquals("Nderim", patient.getFirstname());
        assertEquals("Solidaris", patient.getMutuelle());
        assertEquals("Periostite", patient.getMaladie().getName());
        assertEquals("Calmer douleurs", patient.getMaladie().getObjectif()[0].getName());
        assertEquals("renforcer", patient.getMaladie().getObjectif()[1].getName());
    }

    @Test
    public void testConstructeurParDefaut(){
        Patient patient = new Patient();

        assertEquals("default", patient.getName());
        assertEquals("default", patient.getFirstname());
        assertEquals("default", patient.getMutuelle());
        assertEquals("default", patient.getMaladie().getName());
        assertEquals(0, patient.getMaladie().getObjectif().length);
    }

    @Test
    public void testSetters() {
        Patient patient = new Patient();

        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs = {objectif1, objectif2};
        Pathologie pathologie = new Pathologie("Periostite", objectifs);

        patient.setName("Shala");
        patient.setFirstname("Nderim");
        patient.setMutuelle("Solidaris");
        patient.setMaladie(pathologie);

        assertEquals("Shala", patient.getName());
        assertEquals("Nderim", patient.getFirstname());
        assertEquals("Solidaris", patient.getMutuelle());
        assertEquals("Periostite", patient.getMaladie().getName());
        assertEquals("Calmer douleurs", patient.getMaladie().getObjectif()[0].getName());
        assertEquals("renforcer", patient.getMaladie().getObjectif()[1].getName());
    }

    @Test
    public void testToString() {
        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs = {objectif1, objectif2};
        Pathologie pathologie = new Pathologie("Periostite", objectifs);

        Patient patient = new Patient("Shala", "Nderim", "Solidaris", pathologie);

        assertTrue(patient.toString().contains("firstname='Nderim'"));
        assertTrue(patient.toString().contains("name='Shala'"));
        assertTrue(patient.toString().contains("mutuelle='Solidaris'"));
        assertTrue(patient.toString().contains("maladie=Pathologie"));
    }

    @Test
    public void testEquals() {
        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs1 = {objectif1, objectif2};

        Objectif objectif3 = new Objectif("Calmer douleurs");
        Objectif objectif4 = new Objectif("renforcer");
        Objectif[] objectifs2 = {objectif3, objectif4};

        Pathologie pathologie1 = new Pathologie("Periostite", objectifs1);
        Pathologie pathologie2 = new Pathologie("Periostite", objectifs2);
        Pathologie pathologie3 = new Pathologie("Tendinite", objectifs1);

        Patient patient1 = new Patient("Shala", "Nderim", "Solidaris", pathologie1);
        Patient patient2 = new Patient("Shala", "Nderim", "Solidaris", pathologie2);
        Patient patient3 = new Patient("Dupont", "Jean", "MutuelleTest", pathologie3);

        assertEquals(patient1, patient2);
        assertNotEquals(patient1, patient3);
    }


}