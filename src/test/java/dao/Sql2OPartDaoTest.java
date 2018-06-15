package dao;

import models.*;
import org.junit.*;
import org.sql2o.*;



import static org.junit.Assert.*;

public class Sql2OPartDaoTest {
    private static Connection conn;
    private static Sql2OPartDao partDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/pc_parts_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        partDao = new Sql2OPartDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        partDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add() {
        Part part = setupPart();
        assertEquals(1, partDao.getAll().size());
    }

    @Test
    public void getAllForNoEntry() {
        assertEquals(0, partDao.getAll().size());
    }

    @Test
    public void getAll() {
        Part part1 = setupPart();
        Part part2 = setupPart();
        assertEquals(2, partDao.getAll().size());
    }

    @Test
    public void findById() {
        Part part1 = setupPart();
        Part part2 = setupPart();
        assertEquals(part1, partDao.findById(part1.getId()));
    }


    @Test
    public void deleteById() {
        Part part1 = setupPart();
        Part part2 = setupPart();
        partDao.deleteById(part1.getId());
        assertEquals(1, partDao.getAll().size());
    }

    @Test
    public void clearAll() {
        Part part1 = setupPart();
        Part part2 = setupPart();
        partDao.clearAll();
        assertEquals(0, partDao.getAll().size());
    }

    //helper
    public Part setupPart () {
        Part part = new Part("AMD", "RYZEN 7", 219);
        partDao.add(part);
        return part;
    }
}