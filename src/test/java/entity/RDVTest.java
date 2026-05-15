package entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RDVTest {

    @Test
    public void testConstructeurInitialisation() {
        Date date = new Date();

        Kine kine = new Kine("Slangen", "Oceane", "Neuro", "031000", "/Nd.jpg");

        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs = {objectif1, objectif2};
        Pathologie pathologie = new Pathologie("Periostite", objectifs);
        Patient patient = new Patient("Shala", "Nderim", "Solidaris", pathologie);

        RDV rdv = new RDV(date, kine, patient);

        assertEquals(date, rdv.getDate());
        assertEquals(kine, rdv.getTherapeute());
        assertEquals(patient, rdv.getPatient());
    }

    @Test
    public void testConstructeurParDefaut() {
        RDV rdv = new RDV();

        assertNotNull(rdv.getDate());

        assertEquals("default", rdv.getTherapeute().getName());
        assertEquals("default", rdv.getTherapeute().getFirstname());
        assertEquals("default", rdv.getTherapeute().getSpecialisation());
        assertEquals("0", rdv.getTherapeute().getInami());

        assertEquals("default", rdv.getPatient().getName());
        assertEquals("default", rdv.getPatient().getFirstname());
        assertEquals("default", rdv.getPatient().getMutuelle());
        assertEquals("default", rdv.getPatient().getMaladie().getName());
        assertEquals(0, rdv.getPatient().getMaladie().getObjectif().length);
    }

    @Test
    public void testSetters() {
        RDV rdv = new RDV();

        Date date = new Date();

        Kine kine = new Kine("Slangen", "Oceane", "Neuro", "031000","/Nd.jpg");

        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs = {objectif1, objectif2};
        Pathologie pathologie = new Pathologie("Periostite", objectifs);
        Patient patient = new Patient("Shala", "Nderim", "Solidaris", pathologie);

        rdv.setDate(date);
        rdv.setTherapeute(kine);
        rdv.setPatient(patient);

        assertEquals(date, rdv.getDate());
        assertEquals(kine, rdv.getTherapeute());
        assertEquals(patient, rdv.getPatient());
    }

    @Test
    public void testToString() {
        Date date = new Date();

        Kine kine = new Kine("Slangen", "Oceane", "Neuro", "031000", "/Nd.jpg");

        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs = {objectif1, objectif2};
        Pathologie pathologie = new Pathologie("Periostite", objectifs);
        Patient patient = new Patient("Shala", "Nderim", "Solidaris", pathologie);

        RDV rdv = new RDV(date, kine, patient);

        assertTrue(rdv.toString().contains("date=" + date));
        assertTrue(rdv.toString().contains("therapeute=" + kine));
        assertTrue(rdv.toString().contains("patient=" + patient));
    }

    @Test
    public void testEquals() {
        Date date = new Date();

        Kine kine1 = new Kine("Slangen", "Oceane", "Neuro", "031000", "/Nd.jpg");
        Kine kine2 = new Kine("Slangen", "Oceane", "Neuro", "031000", "/Nd.jpg");
        Kine kine3 = new Kine("Nisin", "Mael", "Pedia", "081001", "/Nd.jpg");

        Objectif objectif1 = new Objectif("Calmer douleurs");
        Objectif objectif2 = new Objectif("renforcer");
        Objectif[] objectifs1 = {objectif1, objectif2};

        Objectif objectif3 = new Objectif("Calmer douleurs");
        Objectif objectif4 = new Objectif("renforcer");
        Objectif[] objectifs2 = {objectif3, objectif4};

        Pathologie pathologie1 = new Pathologie("Periostite", objectifs1);
        Pathologie pathologie2 = new Pathologie("Periostite", objectifs2);

        Patient patient1 = new Patient("Shala", "Nderim", "Solidaris", pathologie1);
        Patient patient2 = new Patient("Shala", "Nderim", "Solidaris", pathologie2);
        Patient patient3 = new Patient("Dupont", "Jean", "MutuelleTest", pathologie1);

        RDV rdv1 = new RDV(date, kine1, patient1);
        RDV rdv2 = new RDV(date, kine2, patient2);
        RDV rdv3 = new RDV(date, kine3, patient3);

        assertEquals(rdv1, rdv2);
        assertNotEquals(rdv1, rdv3);
    }
}
