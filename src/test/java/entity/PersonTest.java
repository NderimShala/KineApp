package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PersonTest{
    @Test
    public void testConstructeurInitialisation(){
        Person person = new Person("SHALA", "Nderim");

        assertEquals("SHALA", person.getName());
        assertEquals("Nderim", person.getFirstname());
    }

    @Test
    public void testConstructeurParDefaut(){
        Person person = new Person();
        assertEquals("default", person.getName());
        assertEquals("default", person.getFirstname());
    }

    @Test
    public void testSetters(){
        Person person = new Person();
        person.setName("Gros");
        person.setFirstname("Tas");

        assertEquals("Gros", person.getName());
        assertEquals("Tas", person.getFirstname());
    }

    @Test
    public void testEquals(){
        Person p1 = new Person("Nisin", "Mael");
        Person p2 = new Person("Nisin", "Mael");
        Person p3 = new Person("Rensonnet", "Noah");

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
    }

    @Test
    public void testToString(){
        Person person = new Person("Nisin", "Mael");

        assertEquals("Person{name='Nisin', firstname='Mael'}", person.toString());
    }
}
