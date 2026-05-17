package Controller;

import Contrats.InterfaceKineModel;
import Contrats.InterfacePatientModel;
import Contrats.InterfaceMainFrame;
import Contrats.InterfaceLogin;
import Contrats.InterfaceLoginListener;
import Contrats.InterfaceMenuListener;
import Contrats.InterfaceKineDAO;
import Contrats.InterfaceAuthentification;

import Properties.KineDAO;

import org.example.authentication.AuthentificationService;

import View.LoginDialog;
import entity.Kine;
import entity.Patient;
import entity.Pathologie;
import entity.Objectif;

public class appController implements InterfaceLoginListener, InterfaceMenuListener{
    private InterfaceKineModel interfaceKine; //stocker la ref à la vue
    private InterfacePatientModel interfacePatient;
    private InterfaceMainFrame interfaceMainFrame;
    private InterfaceLogin interfaceLogin;

    private LoginDialog loginDialog;

    private InterfaceKineDAO kineDAO;
    private InterfaceAuthentification authentificationService;

    public appController(InterfaceKineModel interfaceKine, InterfacePatientModel interfacePatient, InterfaceMainFrame interfaceMainFrame, InterfaceLogin interfaceLogin) {
        this.interfaceKine = interfaceKine;
        this.interfacePatient = interfacePatient;
        this.interfaceMainFrame = interfaceMainFrame;
        this.interfaceLogin = interfaceLogin;
        this.loginDialog = (LoginDialog) interfaceLogin;

        this.kineDAO = new KineDAO();
        this.authentificationService = new AuthentificationService();

        interfaceLogin.setLoginListener(this); //enregistrer le controleur comme listener du login
        interfaceMainFrame.setLoginMenuListener(this);

        initialiserKine();
        initialiserPatient();
        initlaiserFrame();
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

        interfacePatient.afficherPatient(patientDefault);
        interfacePatient.desactiverBoutton();
    }

    public void initlaiserFrame(){
        interfaceMainFrame.desactiverMenuItemPatient();
        interfaceMainFrame.desactiverMenuItemLogout();
    }

    @Override
    public void onLoginClicked(String username, String password,boolean nouveau){

    }
    @Override
    public void onCancelClicked(){
        interfaceLogin.fermer();
    }
    @Override
    public void onLogoutMenuClicked(){
        interfaceMainFrame.desactiverMenuItemPatient();
        interfaceMainFrame.desactiverMenuItemLogout();
    }
    @Override
    public void onLoginMenuClicked(){
        loginDialog.setVisible(true);
    }


}
