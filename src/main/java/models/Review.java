package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Review implements Comparable<Review> {
    private String content;
    private String writtenBy;
    private int rating;
    private int id;
    private int partId;
    private long createdAt;
    private String formattedCreatedAt;

    public Review(String content, String writtenBy, int rating, int partId) {
        this.content = content;
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.partId = partId;
        setCreatedAt();
        setFormattedCreatedAt();
    }


    @Override
    public int compareTo(Review reviewObject) {
        if (this.createdAt < reviewObject.createdAt) {
            return -1;
        } else if (this.createdAt > reviewObject.createdAt) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = System.currentTimeMillis();
    }

    public String getFormattedCreatedAt() {
        Date date = new Date(createdAt);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        return sdf.format(date);
    }

    public void setFormattedCreatedAt() {
        Date date = new Date(this.createdAt);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        this.formattedCreatedAt = sdf.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rating == review.rating &&
                id == review.id &&
                partId == review.partId &&
                createdAt == review.createdAt &&
                Objects.equals(content, review.content) &&
                Objects.equals(writtenBy, review.writtenBy) &&
                Objects.equals(formattedCreatedAt, review.formattedCreatedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(content, writtenBy, rating, id, partId, createdAt, formattedCreatedAt);
    }
}
