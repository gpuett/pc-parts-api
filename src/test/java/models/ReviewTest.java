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

    //helper
    public Review setupReview() {
        return new Review("Works great!", "John", 4);
    }
}