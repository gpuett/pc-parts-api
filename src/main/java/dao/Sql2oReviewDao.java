package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oReviewDao implements ReviewDao {
    private final Sql2o sql2o;
    public Sql2oReviewDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Review review) {
        String sql = "INSERT INTO reviews (content, writtenby, rating, partid, createdat) VALUES (:content, :writtenBy, :rating, :partId, :createdAt)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(review)
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Review> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews")
                    .executeAndFetch(Review.class);
        }
    }

    @Override
    public List<Review> getAllReviewsByPartId(int partId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews WHERE partId = :partId")
                    .addParameter("partId", partId)
                    .executeAndFetch(Review.class);
        }
    }

    @Override
    public Review findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Review.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from reviews WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from reviews";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Review> getAllReviewsByPartSortedNewestToOldest(int partId) {
        List<Review> unsortedReviews = getAllReviewsByPartId(partId);
        List<Review> sortedReviews = new ArrayList<>();
        int i = 1;
        for (Review review : unsortedReviews) {
            int comparisonResult;
            if (i == unsortedReviews.size()) {
                if (review.compareTo(unsortedReviews.get(i-1)) == -1) {
                    sortedReviews.add(0, unsortedReviews.get(i-1));
                }
                break;
            } else {
                if (review.compareTo(unsortedReviews.get(i-1)) == -1) {
                    sortedReviews.add(0, unsortedReviews.get(i));
                    i++;
                } else if (review.compareTo(unsortedReviews.get(i)) == 0) {
                    sortedReviews.add(0, unsortedReviews.get(i));
                    i++;
                } else {
                    sortedReviews.add(0, unsortedReviews.get(i));
                    i ++;
                }
            }

        }

        return sortedReviews;
    }
}
