package entity;

import java.io.Serializable;
import java.util.Objects;

public class Patient extends Person implements Serializable {
    private String mutuelle;
    private Pathologie maladie;

    public Patient() {
        super();
        this.mutuelle = "default";
        this.maladie = new Pathologie();
    }

    public Patient(String name, String firstname, String mutuelle, Pathologie maladie) {
        super(name, firstname);
        this.mutuelle = mutuelle;
        this.maladie = maladie;
    }

    public String getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }

    public Pathologie getMaladie() {
        return maladie;
    }

    public void setMaladie(Pathologie maladie) {
        this.maladie = maladie;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstname='" + firstname + '\'' +
                ", name='" + name + '\'' +
                ", maladie=" + maladie +
                ", mutuelle='" + mutuelle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getMutuelle(), patient.getMutuelle()) && Objects.equals(getMaladie(), patient.getMaladie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMutuelle(), getMaladie());
    }
}
