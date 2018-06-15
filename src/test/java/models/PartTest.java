package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class PartTest {

    @Test
    public void getManufacturer() {
        Part testPart = setupCpu();
        assertEquals("AMD", testPart.getManufacturer());
    }

    @Test
    public void setManufacturer() {
        Part testPart = setupCpu();
        testPart.setManufacturer("Intel");
        assertEquals("Intel", testPart.getManufacturer());
    }

    @Test
    public void getSeries() {
        Part testPart = setupCpu();
        assertEquals("RYZEN 7", testPart.getSeries());
    }

    @Test
    public void setSeries() {
        Part testPart = setupCpu();
        testPart.setSeries("Core i9");
        assertEquals("Core i9", testPart.getSeries());
    }



    @Test
    public void getPrice() {
        Part testPart = setupCpu();
        assertEquals(219, testPart.getPrice(), 0);
    }

    @Test
    public void setPrice() {
        Part testPart = setupCpu();
        testPart.setPrice(250);
        assertEquals(250, testPart.getPrice(), 0);
    }


    //helper
    public Part setupCpu () {
        return new Part("AMD", "RYZEN 7",219);
    }
}