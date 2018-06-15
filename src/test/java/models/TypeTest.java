package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeTest {

    @Test
    public void getName() {
        Type type = setupType();
        assertEquals("CPU", type.getName());
    }

    @Test
    public void setName() {
        Type type = setupType();
        type.setName("Motherboard");
        assertEquals("Motherboard", type.getName());
    }

    @Test
    public void setId() {
    }

    //helpers
    public Type setupType() {
        return new Type("CPU");
    }
}