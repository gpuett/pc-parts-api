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
        Type type = setupType();
        type.setId(4);
        assertEquals(4, type.getId());
    }

    //helpers
    public Type setupType() {
        return new Type("CPU");
    }
}