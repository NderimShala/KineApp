package entity;

import java.util.Date;
import java.util.Objects;

public class RDV {
    private Date date;
    private Kine therapeute;
    private Patient patient;

    public RDV() {
        this.date = new Date();
        this.therapeute = new Kine();
        this.patient = new Patient();
    }

    public RDV(Date date, Kine therapeute, Patient patient) {
        this.date = date;
        this.therapeute = therapeute;
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Kine getTherapeute() {
        return therapeute;
    }

    public void setTherapeute(Kine therapeute) {
        this.therapeute = therapeute;
    }

    @Override
    public String toString() {
        return "RDV{" +
                "date=" + date +
                ", therapeute=" + therapeute +
                ", patient=" + patient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RDV rdv = (RDV) o;
        return Objects.equals(getDate(), rdv.getDate()) && Objects.equals(getTherapeute(), rdv.getTherapeute()) && Objects.equals(getPatient(), rdv.getPatient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getTherapeute(), getPatient());
    }
}
