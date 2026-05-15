package entity;

import java.util.Objects;

public class Person {
    protected String name;
    protected String firstname;

    public Person() {
        this.name = "default";
        this.firstname = "default";
    }

    public Person(String name, String firstname) {
        this.name = name;
        this.firstname = firstname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getName(), person.getName()) && Objects.equals(getFirstname(), person.getFirstname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getFirstname());
    }
}
