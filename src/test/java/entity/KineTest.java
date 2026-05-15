package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KineTest{
    @Test
    public void testConstructeurInitialisation(){
        Kine kine = new Kine("Slangen", "Oceane", "Neuro", "031000", "/Nd.jpg");

        assertEquals("Slangen", kine.getName());
        assertEquals("Oceane", kine.getFirstname());
        assertEquals("Neuro", kine.getSpecialisation());
        assertEquals("031000", kine.getInami());
        assertEquals("/Nd.jpg", kine.getKinePhoto());
    }

    @Test
    public void testConstructeurParDefaut(){
        Kine kine = new Kine();

        assertEquals("default", kine.getName());
        assertEquals("default", kine.getFirstname());
        assertEquals("default", kine.getSpecialisation());
        assertEquals("0", kine.getInami());
        assertEquals("/Nderim.jpg", kine.getKinePhoto());

    }

    @Test
    public void testSetters(){
        Kine kine = new Kine();
        kine.setName("Gros");
        kine.setFirstname("Tas");
        kine.setInami("081001");
        kine.setSpecialisation("uro");

        assertEquals("Gros", kine.getName());
        assertEquals("Tas", kine.getFirstname());
        assertEquals("uro", kine.getSpecialisation());
        assertEquals("081001", kine.getInami());
    }

    @Test
    public void testEquals(){
        Kine k1 = new Kine("Slangen", "Oceane", "Neuro", "031000", "/Nd.jpg");
        Kine k2 = new Kine("Nisin", "Mael", "Pedia", "081001", "/Nd.jpg");
        Kine k3 = new Kine("Slangen", "Oceane", "Neuro", "031000","/Nd.jpg");
        
        assertEquals(k1, k3);
        assertNotEquals(k1, k2);
    }
    
    @Test
    public void testToString() {
        Kine kine = new Kine("Slangen", "Oceane", "Neuro", "031000","/Nd.jpg");

        assertEquals("Kine{specialisation='Neuro', name='Slangen', firstname='Oceane', Numéro iname='031000'}", kine.toString());
    }
}