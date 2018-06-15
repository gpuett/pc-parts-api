package dao;

import models.Part;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2OPartDao implements PartDao {
    private final Sql2o sql2o;
    public Sql2OPartDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Part part) {
        String sql = "INSERT INTO cpus (manufacturer, series, speed, cores, price) VALUES (:manufacturer, :series, :speed, :cores, :price)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(part)
                    .executeUpdate()
                    .getKey();
            part.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Part> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM cpus").executeAndFetch(Part.class);
        }
    }

    @Override
    public Part findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM cpus WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Part.class);
        }
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from cpus WHERE id = :id";
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
        String sql = "DELETE from cpus";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
