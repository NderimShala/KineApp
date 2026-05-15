package org.example.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest{
    private KineLogin kineLogin;
    private PatientLogin patientLogin;

    @BeforeEach //éxecuter cette méthode avant chaque test
    public void setUp(){
        kineLogin = new KineLogin();
        patientLogin = new PatientLogin();

        kineLogin.addUser("Kine1", "password1");
        kineLogin.addUser("kine2", "password2");

        patientLogin.addUser("patient1", "password1");
        patientLogin.addUser("patient2", "password2");
    }

    //test KineLogin

    @Test
    public void testKineToutOk(){
        boolean result = kineLogin.login("Kine1", "password1");
        assertTrue(result, "tout est ok");
    }

    @Test
    public void testKineMauvaisMdp(){
        boolean result = kineLogin.login("Kine1", "mauvaisMDP");
        assertFalse(result, "c'est un mauvais mdp");
    }

    @Test
    public void testKineInconnu(){
        boolean result = kineLogin.login("jbvhsdbvhsbvhusbv", "password1");
        assertFalse(result, "utilisateur innexistant");
    }

    @Test
    public void testNomKineVide(){
        boolean result = kineLogin.login("", "password1");
        assertFalse(result, "pas de nom d'utilisateur");
    }

    @Test
    public void testKineMdpVide(){
        boolean result = kineLogin.login("Kine1", "");
        assertFalse(result, "pas de mot de passe");
    }

    @Test
    public void testNomKineNULL(){
        boolean result = kineLogin.login(null, "password1");
        assertFalse(result, "utilisateur à la valeur null");
    }

    @Test
    public void testKineMdpNULL(){
        boolean result = kineLogin.login("Kine1", null);
        assertFalse(result, "mdp à la valeur null");
    }


    // test pour PatientLogin

    @Test
    public void testPatientToutOK() {
        boolean result = patientLogin.login("patient1", "password1");
        assertTrue(result, "tout est ok");
    }

    @Test
    public void testPatientMauvaisMDP() {
        boolean result = patientLogin.login("patient1", "wrongPassword");
        assertFalse(result, "pas le bon MDP");
    }

    @Test
    public void testPatientMauvaisNom() {
        boolean result = patientLogin.login("unknownPatient", "password1");
        assertFalse(result, "le patient n'existe pas");
    }

    // test ajout utilisateur


    @Test
    public void testAddUserKineLogin() {
        kineLogin.addUser("kine3", "password3");
        boolean result = kineLogin.login("kine3", "password3");
        assertTrue(result, "nouvel utilisateur ajouté");
    }

    @Test
    public void testAddUserPatientLogin() {
        patientLogin.addUser("patient3", "password3");
        boolean result = patientLogin.login("patient3", "password3");
        assertTrue(result, "nouveau patient ajouté");
    }

    @Test
    public void testAddUserSansNom() {
        kineLogin.addUser("", "password1");
        patientLogin.addUser("", "password1");
        boolean result = kineLogin.login("", "password1");
        boolean result2 = patientLogin.login("", "password1");
        assertFalse(result, "pas de nom pour le kine");
        assertFalse(result2, "pas de nom pour le patient");

    }

    @Test
    public void testAddUserSansMDP() {
        kineLogin.addUser("kine3", "");
        patientLogin.addUser("patient3", "");
        boolean result = kineLogin.login("kine3", "");
        boolean result2 = patientLogin.login("patient3", "");
        assertFalse(result, "pas de mdp pour le kine");
        assertFalse(result2, "pas de mdp pour le patient");
    }

    //test suppression utilisateur

    @Test
    public void testRemoveUserKineLogin() {
        kineLogin.suppUser("kine1");
        boolean result = kineLogin.login("kine1", "password1");
        assertFalse(result, "le kine n'existe plus -> echec");
    }

    @Test
    public void testRemoveUserPatientLogin() {
        patientLogin.suppUser("patient1");
        boolean result = patientLogin.login("patient1", "password1");
        assertFalse(result, "le patient n'existe plus -> echec");
    }
}
