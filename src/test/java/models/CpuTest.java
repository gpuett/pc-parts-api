package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CpuTest {

    @Test
    public void getManufacturer() {
        Cpu testCpu = setupCpu();
        assertEquals("AMD", testCpu.getManufacturer());
    }

    @Test
    public void setManufacturer() {
        Cpu testCpu = setupCpu();
        testCpu.setManufacturer("Intel");
        assertEquals("Intel", testCpu.getManufacturer());
    }

    @Test
    public void getSeries() {
        Cpu testCpu = setupCpu();
        assertEquals("RYZEN 7", testCpu.getSeries());
    }

    @Test
    public void setSeries() {
    }

    @Test
    public void getSpeed() {
    }

    @Test
    public void setSpeed() {
    }

    @Test
    public void getCores() {
    }

    @Test
    public void setCores() {
    }

    @Test
    public void getPrice() {
    }

    @Test
    public void setPrice() {
    }


    //helper
    public Cpu setupCpu () {
        return new Cpu("AMD", "RYZEN 7", "3.4GHz", 8, 219.99);
    }
}