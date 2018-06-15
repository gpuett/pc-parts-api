package dao;

import models.*;
import org.junit.*;
import org.sql2o.*;



import static org.junit.Assert.*;

public class Sql2OPartDaoTest {
    private static Connection conn;
    private static Sql2OPartDao cpuDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/pc_parts_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        cpuDao = new Sql2OPartDao(sql2o);
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
        Part part = setupCpu();
        assertEquals(1, cpuDao.getAll().size());
    }

    @Test
    public void getAllForNoEntry() {
        assertEquals(0, cpuDao.getAll().size());
    }

    @Test
    public void getAll() {
        Part part1 = setupCpu();
        Part part2 = setupCpu();
        assertEquals(2, cpuDao.getAll().size());
    }

    @Test
    public void findById() {
        Part part1 = setupCpu();
        Part part2 = setupCpu();
        assertEquals(part1, cpuDao.findById(part1.getId()));
    }


    @Test
    public void deleteById() {
        Part part1 = setupCpu();
        Part part2 = setupCpu();
        cpuDao.deleteById(part1.getId());
        assertEquals(1, cpuDao.getAll().size());
    }

    @Test
    public void clearAll() {
        Part part1 = setupCpu();
        Part part2 = setupCpu();
        cpuDao.clearAll();
        assertEquals(0, cpuDao.getAll().size());
    }

    //helper
    public Part setupCpu () {
        Part part = new Part("AMD", "RYZEN 7", "3.4GHz", 8, 219);
        cpuDao.add(part);
        return part;
    }
}