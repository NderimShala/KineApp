package entity;

import java.io.Serializable;
import java.util.Objects;

public class Objectif implements Serializable {
    private String name;

    public Objectif() {
        this.name = "default";
    }

    public Objectif(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Objectif{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Objectif objectif = (Objectif) o;
        return Objects.equals(getName(), objectif.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
