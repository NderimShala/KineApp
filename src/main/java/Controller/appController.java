package Controller;
import javax.swing.JOptionPane;

import Contrats.*;

import Properties.*;

import View.*;
import org.example.authentication.AuthentificationService;

import entity.Kine;
import entity.Patient;
import entity.Objectif;

public class appController implements InterfaceLoginListener, InterfaceMenuListener, InterfaceNouveauKineListener, InterfaceNouveauPatientListener, InterfaceObjectif  {
    private InterfaceKineModel interfaceKine; //stocker la ref à la vue
    private InterfacePatientModel interfacePatient;
    private InterfaceMainFrame interfaceMainFrame;
    private InterfaceLogin interfaceLogin;

    private LoginDialog loginDialog;
    private NouveauKineDialog nouveauKineDialog;
    private AddPAtientDialog nouveauPatientDialog;
    private AddObjectifDialog objectifDialog;
    private OuvrirPatientDialog ouvrirPatientDialog;
    private SupprPatientDialog supprPatientDialog;

    private InterfaceKineDAO kineDAO;
    private InterfaceAuthentification authentificationService;

    private PatientsService patientService;
    private ObjectifService objectifService;

    private String usernameEnAttente;
    private String passwordEnAttente;
    private Patient patientCourant;

    private PatientDAO patientDAO;

    private JPanelPatient patientPanel;

    public appController(InterfaceKineModel interfaceKine, InterfacePatientModel interfacePatient, InterfaceMainFrame interfaceMainFrame, InterfaceLogin interfaceLogin) {
        this.interfaceKine = interfaceKine;
        this.interfacePatient = interfacePatient;
        this.interfaceMainFrame = interfaceMainFrame;
        this.interfaceLogin = interfaceLogin;
        this.loginDialog = (LoginDialog) interfaceLogin;

        this.nouveauKineDialog = new NouveauKineDialog();
        this.nouveauKineDialog.setListener(this); //enregistrer le controleur comme listener du nouveau kine dialog

        this.nouveauPatientDialog = new AddPAtientDialog();
        this.nouveauPatientDialog.setListener(this); //enregistrer le controleur comme listener du nouveau patient dialog

        this.ouvrirPatientDialog = new OuvrirPatientDialog();
        this.supprPatientDialog = new SupprPatientDialog();

        this.objectifDialog = new AddObjectifDialog();

        this.kineDAO = new KineDAO();
        this.patientDAO = new PatientDAO();
        this.authentificationService = new AuthentificationService(kineDAO);

        this.patientService = new PatientsService();
        this.objectifService = new ObjectifService();

        interfaceLogin.setLoginListener(this); //enregistrer le controleur comme listener du login
        interfaceMainFrame.setLoginMenuListener(this);


        initialiserKine();
        initialiserPatient();
        initlaiserFrame();

        this.patientPanel = (JPanelPatient) interfacePatient;
        patientPanel.setListenerAjouter(this);
        patientPanel.setListenerSupprimer(this);
    }

    public void initialiserKine(){
        Kine kineDefault = new Kine();
        interfaceKine.afficherKine(kineDefault);
    }

    public void initialiserPatient(){
        Patient patientDefault = patientService.creerPatientDefault();
        this.patientCourant = patientDefault;
        interfacePatient.afficherPatient(patientDefault);
        interfacePatient.desactiverBoutton();
    }

    public void initlaiserFrame(){
        interfaceMainFrame.desactiverMenuItemPatient();
        interfaceMainFrame.desactiverMenuItemLogout();
    }

    @Override
    public void onLoginClicked(String username, String password,boolean nouveau) throws Exception {
        System.out.println("tentative de connextion : username: " + username + "nouveau : " + nouveau);
        System.out.println("utilisateurs existants : " + kineDAO);
        try {
            if (username == null || username.isBlank() || password == null || password.isBlank()) {
                throw new Exception("Username ou password vide");
            }
            if (nouveau) {
                System.out.println("nouvel utilisateur détecté");
                this.usernameEnAttente = username;
                this.passwordEnAttente = password;

                interfaceLogin.fermer();

                nouveauKineDialog.setVisible(true);
            }
            else {
                System.out.println("tentative d'authentification pour : " + username);
                InterfaceUtilisateur utilisateur = authentificationService.authentification(username, password);
                if (utilisateur != null) {
                    System.out.println("Login réussi pour : " + utilisateur.getName() + " " + utilisateur.getFirstname());

                    if (utilisateur instanceof Kine) {
                        Kine kine = (Kine) utilisateur;
                        interfaceKine.afficherKine(kine);
                    }
                    interfaceMainFrame.desactiverMenuItemPatient();
                    interfaceMainFrame.activerMenuItemLogout();
                    interfaceMainFrame.desactiverMenuItemLogin();
                    ;
                    interfacePatient.activerBoutton();
                    interfaceMainFrame.activerMenuItemPatient();

                    interfaceLogin.fermer();
                }
                else{
                    throw new Exception("Login échoué veuillez réessayer");
                }
            }
        }catch (Exception e){
            loginDialog.afficherMessageErreurLogin("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void onCancelClicked(){
        interfaceLogin.fermer();
    }
    @Override
    public void onLogoutMenuClicked(){
        interfaceMainFrame.desactiverMenuItemPatient();
        interfaceMainFrame.desactiverMenuItemLogout();
        interfaceMainFrame.activerMenuItemLogin();
        interfacePatient.desactiverBoutton();
        interfaceMainFrame.desactiverMenuItemPatient();
        initialiserKine();
    }
    @Override
    public void onLoginMenuClicked(){
        loginDialog.setVisible(true);
    }

    @Override
    public void onNouveauKineClicked(String username, String password, String nom, String prenom, String specialisation, String inami, String photo) throws Exception{
        try{
            InterfaceUtilisateur utilisateurCree = authentificationService.addKine(usernameEnAttente, passwordEnAttente, nom, prenom, specialisation, inami, photo);
            System.out.println("Après addKine - résultat: " + utilisateurCree);

            if (utilisateurCree instanceof Kine) {
                Kine kine = (Kine) utilisateurCree;
                interfaceKine.afficherKine(kine);
                authentificationService.authentification(username, password);

                nouveauKineDialog.fermer();


                interfaceMainFrame.desactiverMenuItemPatient();
                interfaceMainFrame.activerMenuItemLogout();
                interfaceMainFrame.desactiverMenuItemLogin();;
                interfacePatient.activerBoutton();
                interfaceMainFrame.activerMenuItemPatient();


                this.usernameEnAttente = null;
                this.passwordEnAttente = null;
            }
            else{
                throw new Exception("Erreur lors de la création du Kine");
            }
        } catch (Exception e){nouveauKineDialog.afficherMessageErreurKine("Erreur : " + e.getMessage());}
    }

    @Override
    public void onNouveauPatientClicked(String nom, String prenom, String mutuelle, String nomPatho) throws Exception {
        try {
            if (nom == null || nom.isBlank() || prenom == null || prenom.isBlank() || nomPatho == null || nomPatho.isBlank()) {
                throw new Exception("Informations manquantes");
            }

            Patient patient = patientService.creer(nom, prenom, mutuelle, nomPatho);
            this.patientCourant = patient;
            System.out.println("=== DEBUG CRÉATION PATIENT ===");
            System.out.println("Nom du patient : '" + patient.getName() + "'");
            System.out.println("Prénom du patient : '" + patient.getFirstname() + "'");
            System.out.println("patient créé : " + patient.getName());
            patientDAO.addPatient(patient);
            System.out.println("Total patients sauvegardés : " + patientDAO.getAllPatients().size());
            for (Patient p : patientDAO.getAllPatients()) {
                System.out.println("  - Patient : " + p.getName());
            }
            System.out.println("==============================");
            nouveauPatientDialog.afficherMessageSuccesPatient();
            interfacePatient.afficherPatient(patient);
            nouveauPatientDialog.fermer();
        }catch (Exception e){nouveauPatientDialog.afficherMessageErreurInfoManquante(e.getMessage());}
    }

    @Override
    public void onCancelPatientClicked() {
        nouveauPatientDialog.fermer();
    }

    @Override
    public void onAjouterPatientMenuClicked(){
        System.out.println("ouverture du dialog pour ajouter un patient");
        nouveauPatientDialog.setVisible(true);
    }

    @Override
    public void onAjouterObjectifClicked() throws Exception {
        try {
            String nomObjectif = objectifDialog.afficherEtRecupererNomObjectif();
            if (nomObjectif != null && !nomObjectif.isBlank()) {
                Objectif objectif = objectifService.creer(nomObjectif);

                if (patientCourant != null) {
                    objectifService.ajouterObjectifPatient(patientCourant, objectif);
                    patientDAO.updatePatient(patientDAO.getAllPatients().indexOf(patientCourant), patientCourant);
                    interfacePatient.afficherPatient(patientCourant);
                }
            }
            else throw new Exception("L'objectif ne peut être vide");
        }catch (Exception e){objectifDialog.afficherMessageObjectifVide("Erreur :" + e.getMessage());}
    }


    @Override
    public void onSupprObjectifClicked() throws Exception {
        try{
            if(patientCourant != null){
                int indexSelected = patientPanel.getSelectedObjectifIndex();
                if(indexSelected >= 0){
                    objectifService.supprimerObjectifPatient(patientCourant, indexSelected);
                    patientDAO.updatePatient(patientDAO.getAllPatients().indexOf(patientCourant), patientCourant);
                    interfacePatient.afficherPatient(patientCourant);
                }
                else throw new Exception("Aucun objectif sélectionné pour suppression");
            }
        }catch (Exception e){patientPanel.afficherMessageErreur("Erreur : " + e.getMessage());}

    }

    @Override
    public void onOuvrirPatientMenuClicked() {
        String nomRecherche = ouvrirPatientDialog.afficherEtRecupererNomPatient();
        if(nomRecherche != null && !nomRecherche.isBlank()){
            Patient patientTrouve = patientDAO.findByName(nomRecherche);
            if(patientTrouve != null) {
                this.patientCourant = patientTrouve;
                interfacePatient.afficherPatient(patientTrouve);
                System.out.println("patient trouvé et affiché");
            }
            else {
                JOptionPane.showMessageDialog(null, "patient inexistant", "erreur", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    @Override
    public void onSupprPatientMenuClicked() {
        String nomRecherche = supprPatientDialog.afficherEtRecupererNomPatient();
        if(nomRecherche != null && !nomRecherche.isBlank()){
            Patient patientASupprimer = patientDAO.findByName(nomRecherche);
            if(patientASupprimer != null){
                int confirmation = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer le patient : " + nomRecherche + " ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                if(confirmation == JOptionPane.YES_OPTION){
                    patientDAO.deleteByName(nomRecherche);
                    if(patientCourant != null && patientCourant.getName().equalsIgnoreCase(nomRecherche)){
                        this.patientCourant = patientService.creerPatientDefault();
                        interfacePatient.afficherPatient(patientCourant);
                    }
                    JOptionPane.showMessageDialog(null, "patient supprimé avec succès : " + nomRecherche, "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "patient inexistant", "erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public String afficherEtRecupererNomObjectif(){
        return null;
    }
    @Override
    public void afficherMessageObjectifVide(String message){

    }
}
