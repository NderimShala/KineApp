package entity;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class Pathologie implements Serializable {
    private String name;
    private Objectif[] objectif;

    public Pathologie() {
        this.name = "default";
        this.objectif = new Objectif[0];
    }

    public Pathologie(String nom){
        this.name = name;
        this.objectif = new Objectif[0];
    }

    public Pathologie(String name, Objectif[] objectif) {
        this.name = name;
        this.objectif = objectif;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Objectif[] getObjectif() {
        return objectif;
    }
    public void setObjectif(Objectif[] objectif) {
        this.objectif = objectif;
    }
    @Override
    public String toString() {
        return "Pathologie{" + "name=" + name + ", objectif=" + objectif + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pathologie that = (Pathologie) o;
        return Objects.equals(getName(), that.getName()) && Objects.deepEquals(getObjectif(), that.getObjectif());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), Arrays.hashCode(getObjectif()));
    }
}
