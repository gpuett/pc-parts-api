package models;

import org.junit.Test;

import static org.junit.Assert.*;


public class ReviewTest {

    @Test
    public void getContent() {
        Review review = setupReview();
        assertEquals("Works great!", review.getContent());
    }

    @Test
    public void setContent() {
        Review review = setupReview();
        review.setContent("just fine");
        assertNotEquals("Works great!", review.getContent());
    }

    @Test
    public void getWrittenBy() {
        Review review = setupReview();
        assertEquals("John", review.getWrittenBy());
    }

    @Test
    public void setWrittenBy() {
        Review review = setupReview();
        review.setWrittenBy("Kim");
        assertNotEquals("John", review.getWrittenBy());
    }

    @Test
    public void getRating() {
        Review review = setupReview();
        assertEquals(4, review.getRating());
    }

    @Test
    public void setRating() {
        Review review = setupReview();
        review.setRating(1);
        assertEquals(1, review.getRating());
    }


    @Test
    public void getPartId() {
        Review review = setupReview();
        assertEquals(1, review.getPartId());
    }

    @Test
    public void name() {
        Review review = setupReview();
        review.setPartId(3);
        assertEquals(3, review.getPartId());
    }

    @Test
    public void setId() {
        Review review = setupReview();
        review.setId(3);
        assertEquals(3, review.getId());
    }

    //helper
    public Review setupReview() {
        return new Review("Works great!", "John", 4, 1);
    }
}