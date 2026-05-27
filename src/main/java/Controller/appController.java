package Controller;

import Contrats.*;

import Properties.*;

import View.*;
import org.example.authentication.AuthentificationService;

import entity.Kine;
import entity.Patient;
import entity.Pathologie;
import entity.Objectif;

public class appController implements InterfaceLoginListener, InterfaceMenuListener, InterfaceNouveauKineListener, InterfaceNouveauPatientListener, InterfaceAddObjectifListener{
    private InterfaceKineModel interfaceKine; //stocker la ref à la vue
    private InterfacePatientModel interfacePatient;
    private InterfaceMainFrame interfaceMainFrame;
    private InterfaceLogin interfaceLogin;

    private LoginDialog loginDialog;
    private NouveauKineDialog nouveauKineDialog;
    private AddPAtientDialog nouveauPatientDialog;
    private AddObjectifDialog objectifDialog;

    private InterfaceKineDAO kineDAO;
    private InterfaceAuthentification authentificationService;

    private InterfaceEntityService<Patient> patientService;
    private ObjectifService objectifService;

    private String usernameEnAttente;
    private String passwordEnAttente;
    private Patient patientCourant;

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

        this.objectifDialog = new AddObjectifDialog();

        this.kineDAO = new KineDAO();
        this.authentificationService = new AuthentificationService(kineDAO);

        this.patientService = new PatientsService();
        this.objectifService = new ObjectifService();

        interfaceLogin.setLoginListener(this); //enregistrer le controleur comme listener du login
        interfaceMainFrame.setLoginMenuListener(this);


        initialiserKine();
        initialiserPatient();
        initlaiserFrame();

        JPanelPatient patientPanel = (JPanelPatient) interfacePatient;
        patientPanel.setListener(this);
    }

    public void initialiserKine(){
        Kine kineDefault = new Kine();
        interfaceKine.afficherKine(kineDefault);
    }

    public void initialiserPatient(){
        Objectif objectifDefault1 = new Objectif("default");
        Objectif objectifDefault2 = new Objectif("default");
        Objectif[] objectifs = {objectifDefault1, objectifDefault2};

        Pathologie pathoDefault = new Pathologie();
        pathoDefault.setObjectif(objectifs);

        Patient patientDefault = new Patient();
        patientDefault.setMaladie(pathoDefault);

        this.patientCourant = patientDefault;
        interfacePatient.afficherPatient(patientDefault);
        interfacePatient.desactiverBoutton();
    }

    public void initlaiserFrame(){
        interfaceMainFrame.desactiverMenuItemPatient();
        interfaceMainFrame.desactiverMenuItemLogout();
    }

    @Override
    public void onLoginClicked(String username, String password,boolean nouveau){
        System.out.println("tentative de connextion : username: "+username + "nouveau : " + nouveau);
        System.out.println("utilisateurs existants : " + kineDAO);

        if(username == null || username.isBlank() || password == null || password.isBlank()){
            System.out.println("erruer : username ou password vide");
            return;
        }
        if(nouveau){
            System.out.println("nouvel utilisateur détecté");
            this.usernameEnAttente = username;
            this.passwordEnAttente = password;

            interfaceLogin.fermer();

            nouveauKineDialog.setVisible(true);
        }
        else{
            System.out.println("tentative d'authentification pour : "+username);
            InterfaceUtilisateur utilisateur = authentificationService.authentification(username, password);
            if(utilisateur != null){
                System.out.println("Login réussi pour : " + utilisateur.getName() + " " + utilisateur.getFirstname());

                if(utilisateur instanceof Kine){
                    Kine kine = (Kine)utilisateur;
                    interfaceKine.afficherKine(kine);
                }
                interfaceMainFrame.desactiverMenuItemPatient();
                interfaceMainFrame.activerMenuItemLogout();
                interfaceMainFrame.desactiverMenuItemLogin();;
                interfacePatient.activerBoutton();
                interfaceMainFrame.activerMenuItemPatient();

                interfaceLogin.fermer();
        }
            else{
                System.out.println("login échoué");
            }
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
    public void onNouveauKineClicked(String username, String password, String nom, String prenom, String specialisation, String inami, String photo){

        InterfaceUtilisateur utilisateurCree = authentificationService.addKine(usernameEnAttente, passwordEnAttente, nom, prenom, specialisation, inami, photo);
        System.out.println("Après addKine - résultat: " + utilisateurCree);

        if (utilisateurCree instanceof Kine) {
            Kine kine = (Kine) utilisateurCree;
            interfaceKine.afficherKine(kine);
            authentificationService.authentification(username, password);

            nouveauKineDialog.fermer();
        };


        interfaceMainFrame.desactiverMenuItemPatient();
        interfaceMainFrame.activerMenuItemLogout();
        interfaceMainFrame.desactiverMenuItemLogin();;
        interfacePatient.activerBoutton();
        interfaceMainFrame.activerMenuItemPatient();

        nouveauKineDialog.fermer();

        this.usernameEnAttente = null;
        this.passwordEnAttente = null;
    }

    @Override
    public void onNouveauPatientClicked(String nom, String prenom, String mutuelle, String nomPatho) {
        Patient patient = patientService.creer(nom, prenom, mutuelle, nomPatho);
        this.patientCourant = patient;
        interfacePatient.afficherPatient(patient);
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
    public void onAjouterObjectifClicked(){
        String nomObjectif = objectifDialog.afficherEtRecupererNomObjectif();
        if(nomObjectif != null && !nomObjectif.isBlank()){
            Objectif objectif = objectifService.creer(nomObjectif);

            if(patientCourant != null){
                objectifService.ajouterObjectifPatient(patientCourant, objectif);
                interfacePatient.afficherPatient(patientCourant);
            }
        }
    }
}
