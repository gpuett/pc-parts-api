package dao;

import org.junit.*;
import models.*;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oTypeDaoTest {
    private static Sql2oTypeDao typeDao;
    private static Sql2OPartDao partDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/pc_parts_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        typeDao = new Sql2oTypeDao(sql2o);
        partDao = new Sql2OPartDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        typeDao.clearAll();
        partDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add() {
        Type type = setupType();
        int originalTypeId = type.getId();
        typeDao.add(type);
        assertNotEquals(originalTypeId, type.getId());
    }

    @Test
    public void addTypeToPart() {
        Part part1 = setupPart();
        Part part2 = setupPart();
        Type type = setupType();
        typeDao.add(type);
        typeDao.addTypeToPart(type, part1);
        typeDao.addTypeToPart(type, part2);
        assertEquals(2, typeDao.getAllPartsByType(type.getId()).size());
    }

    @Test
    public void getAll() {
        Type type = setupType();
        typeDao.add(type);
        assertEquals(1, typeDao.getAll().size());
    }


    @Test
    public void findById() {
        Type type = setupType();
        typeDao.add(type);
        assertEquals(type, typeDao.findById(type.getId()));
    }

    @Test
    public void deleteById() {
        Type type = setupType();
        typeDao.add(type);
        typeDao.deleteById(type.getId());
        assertEquals(0, typeDao.getAll().size());
    }

    @Test
    public void deletingTypeUpdateJoinTable() {
        Type type = setupType();
        typeDao.add(type);
        Part part = setupPart();
        typeDao.addTypeToPart(type, part);
        typeDao.deleteById(type.getId());
        assertEquals(0, typeDao.getAllPartsByType(type.getId()).size());
    }

    //helpers
    public Type setupType() {
        return new Type("CPU");
    }

    public Part setupPart () {
        Part part = new Part("AMD", "RYZEN 7", 219);
        partDao.add(part);
        return part;
    }
}