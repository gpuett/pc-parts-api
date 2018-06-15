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
    }

    @Test
    public void setId() {
    }

    //helpers
    public Type setupType() {
        return new Type("CPU");
    }
}