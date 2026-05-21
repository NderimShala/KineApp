package entity;

import java.util.Objects;
import Contrats.InterfaceUtilisateur;

public class Kine extends Person implements InterfaceUtilisateur{
    private String specialisation;
    private String inami;
    private String kinePhoto;

    public Kine() {
        super();
        this.specialisation = "default";
        this.inami = "0";
        this.kinePhoto = "/default.jpg";
    }

    public Kine(String name, String firstname, String specialisation, String numInami, String photo) {
        super(name, firstname);
        this.specialisation = specialisation;
        this.inami = numInami;
        this.kinePhoto = photo;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String getInami() {
        return inami;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public void setInami(String numInami) {
        this.inami = numInami;
    }

    public String getKinePhoto() {
        return kinePhoto;
    }

    public void setKinePhoto(String kinePhoto) {
        this.kinePhoto = kinePhoto;
    }

    @Override
    public String getPhoto(){
        return kinePhoto;
    }

    @Override
    public String toString() {
        return "Kine{" +
                "specialisation='" + specialisation + '\'' +
                ", inami='" + inami + '\'' +
                ", kinePhoto='" + kinePhoto + '\'' +
                ", firstname='" + firstname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Kine kine = (Kine) o;
        return Objects.equals(getSpecialisation(), kine.getSpecialisation()) && Objects.equals(getInami(), kine.getInami()) && Objects.equals(getKinePhoto(), kine.getKinePhoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSpecialisation(), getInami(), getKinePhoto());
    }
}
