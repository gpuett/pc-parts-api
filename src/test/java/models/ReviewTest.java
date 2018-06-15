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

    //helper
    public Review setupReview() {
        return new Review("Works great!", "John", 4, 1);
    }
}