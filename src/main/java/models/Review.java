package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Review implements Comparable<Review> {
    private String content;
    private String writtenBy;
    private int rating;
    private int id;
    private int cpuId;
    private int gpuId;
    private int memoryId;
    private int motherboardId;
    private int storageId;
    private long createdAt;
    private String formattedCreatedAt;

    public Review(String content, String writtenBy, int rating) {
        this.content = content;
        this.writtenBy = writtenBy;
        this.rating = rating;
        setCreatedAt();
        setFormattedCreatedAt();
    }

//    public Review(String content, String writtenBy, int rating, int gpuId) {
//        this.content = content;
//        this.writtenBy = writtenBy;
//        this.rating = rating;
//        this.gpuId = gpuId;
//        setCreatedAt();
//        setFormattedCreatedAt();
//    }
//
//    public Review(String content, String writtenBy, int rating, int memoryId) {
//        this.content = content;
//        this.writtenBy = writtenBy;
//        this.rating = rating;
//        this.memoryId = memoryId;
//        setCreatedAt();
//        setFormattedCreatedAt();
//    }
//
//    public Review(String content, String writtenBy, int rating, int motherboardId) {
//        this.content = content;
//        this.writtenBy = writtenBy;
//        this.rating = rating;
//        this.motherboardId = motherboardId;
//        setCreatedAt();
//        setFormattedCreatedAt();
//    }
//
//    public Review(String content, String writtenBy, int rating, int storageId) {
//        this.content = content;
//        this.writtenBy = writtenBy;
//        this.rating = rating;
//        this.storageId = storageId;
//        setCreatedAt();
//        setFormattedCreatedAt();
//    }

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

    public int getCpuId() {
        return cpuId;
    }

    public void setCpuId(int cpuId) {
        this.cpuId = cpuId;
    }

    public int getGpuId() {
        return gpuId;
    }

    public void setGpuId(int gpuId) {
        this.gpuId = gpuId;
    }

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public int getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(int motherboardId) {
        this.motherboardId = motherboardId;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
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
                cpuId == review.cpuId &&
                gpuId == review.gpuId &&
                memoryId == review.memoryId &&
                motherboardId == review.motherboardId &&
                storageId == review.storageId &&
                createdAt == review.createdAt &&
                Objects.equals(content, review.content) &&
                Objects.equals(writtenBy, review.writtenBy) &&
                Objects.equals(formattedCreatedAt, review.formattedCreatedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(content, writtenBy, rating, id, cpuId, gpuId, memoryId, motherboardId, storageId, createdAt, formattedCreatedAt);
    }
}
