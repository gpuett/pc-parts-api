package dao;

import models.*;
import org.junit.*;
import org.sql2o.*;

import java.util.Arrays;


import static org.junit.Assert.*;

public class Sql2oCpuDaoTest {
    private static Connection conn;
    private static Sql2oCpuDao cpuDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/pc_parts_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        cpuDao = new Sql2oCpuDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        cpuDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add() {
        Cpu cpu = setupCpu();
        assertEquals(1, cpuDao.getAll().size());
    }

    @Test
    public void getAllForNoEntry() {
        assertEquals(0, cpuDao.getAll().size());
    }

    @Test
    public void getAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAll() {
    }

    //helper
    public Cpu setupCpu () {
        Cpu cpu = new Cpu("AMD", "RYZEN 7", "3.4GHz", 8, 219.99);
        cpuDao.add(cpu);
        return cpu;
    }
}