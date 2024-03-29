package dao;

import org.junit.*;
import org.sql2o.*;
import models.*;

import static org.junit.Assert.*;

public class Sql2oReviewDaoTest {
    private static Connection conn;
    private static Sql2oReviewDao reviewDao;
    private static Sql2OPartDao partDao;


    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/pc_parts_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        reviewDao = new Sql2oReviewDao(sql2o);
        partDao = new Sql2OPartDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        reviewDao.clearAll();
        partDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingReviewSetsId() {
        Review review = setupReview();
        int originalId = review.getId();
        int foundId = reviewDao.findById(review.getId()).getId();
        assertEquals(foundId, originalId);
    }

    @Test
    public void getAll() {
        Review review1 = setupReview();
        Review review2 = setupReview();
        assertEquals(2, reviewDao.getAll().size());
    }

    @Test
    public void getAllReviewsByPartId() {
        Part part1 = setupPart();
        Part part2 = setupPart();
        part2.setId(33);
        Review review1 = setupReviewForPart(part1);
        Review review2 = setupReviewForPart(part1);
        Review review3 = setupReviewForPart(part2);
        assertEquals(2, reviewDao.getAllReviewsByPartId(part1.getId()).size());
    }


    @Test
    public void deleteById() {
        Review review1 = setupReview();
        Review review2 = setupReview();
        assertEquals(2, reviewDao.getAll().size());
        reviewDao.deleteById(review1.getId());
        assertEquals(1, reviewDao.getAll().size());
    }

    @Test
    public void clearAll() {
        Review review1 = setupReview();
        Review review2 = setupReview();
        reviewDao.clearAll();
        assertEquals(0, reviewDao.getAll().size());
    }

    @Test
    public void timeStampIsReturnedCorrectly() {
        Part part = setupPart();
        Review review = new Review("great", "kim", 4, part.getId());
        reviewDao.add(review);
        long creationTime = review.getCreatedAt();
        long savedTime = reviewDao.getAll().get(0).getCreatedAt();
        String formattedCreationTime = review.getFormattedCreatedAt();
        String formattedSavedTime = reviewDao.getAll().get(0).getFormattedCreatedAt();
        assertEquals(formattedCreationTime, formattedSavedTime);
        assertEquals(creationTime, savedTime);
    }

    @Test
    public void getAllReviewsByPartSortedNewestToOldest() {
        Part part = setupPart();
        Review review1 = new Review("great", "kim", 4, part.getId());
        reviewDao.add(review1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        Review review2 = new Review("junk", "joe", 1, part.getId());
        reviewDao.add(review2);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        Review review3 = new Review("okay", "sam", 2, part.getId());
        reviewDao.add(review3);
        assertEquals(3, reviewDao.getAllReviewsByPartId(part.getId()).size());
        assertEquals("okay", reviewDao.getAllReviewsByPartSortedNewestToOldest(part.getId()).get(0).getContent());
    }

    //helpers

    public Review setupReview() {
        Review review = new Review("Works great!", "John", 4, 20);
        reviewDao.add(review);
        return review;
    }

    public Part setupPart() {
        Part part = new Part("AMD", "RYZEN 7", 219);
        partDao.add(part);
        return part;
    }

    public Review setupReviewForPart(Part part) {
        Review review = new Review("Works great!", "John", 4, part.getId());
        reviewDao.add(review);
        return review;
    }
}