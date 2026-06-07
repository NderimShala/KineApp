
// src/test/java/Properties/PatientDAOTest.java
package Properties;

import entity.Patient;
import entity.Pathologie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientDAOTest {
    private PatientDAO patientDAO;

    @BeforeEach
    public void setUp() {
        patientDAO = new PatientDAO();
    }

    @Test
    public void testCreatePatient() {
        Pathologie patho = new Pathologie("TestPatho");
        Patient patient = new Patient("Dupont", "Jean", "Solidaris", patho);
        patientDAO.addPatient(patient);

        List<Patient> patients = patientDAO.getAllPatients();
        assertTrue(patients.contains(patient), "Patient devrait être dans la liste");
    }

    @Test
    public void testFindByName() {
        Pathologie patho = new Pathologie("TestPatho");
        Patient patient = new Patient("Dupont", "Jean", "Solidaris", patho);
        patientDAO.addPatient(patient);

        Patient found = patientDAO.findByName("Dupont");
        assertNotNull(found, "Patient devrait être trouvé");
        assertEquals("Dupont", found.getName());
    }

    @Test
    public void testDeletePatient() {
        Pathologie patho = new Pathologie("TestPatho");
        Patient patient = new Patient("Dupont", "Jean", "Solidaris", patho);
        patientDAO.addPatient(patient);

        patientDAO.deleteByName("Dupont");

        Patient found = patientDAO.findByName("Dupont");
        assertNull(found, "Patient devrait être supprimé");
    }
}