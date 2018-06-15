package dao;

import models.Cpu;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCpuDao implements CpuDao {
    private final Sql2o sql2o;
    public Sql2oCpuDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Cpu cpu) {
        String sql = "INSERT INTO cpus (manufacturer, series, speed, cores, price) VALUES (:manufacturer, :series, :speed, :cores, :price)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(cpu)
                    .executeUpdate()
                    .getKey();
            cpu.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Cpu> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM cpus").executeAndFetch(Cpu.class);
        }
    }

    @Override
    public Cpu findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM cpus WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Cpu.class);
        }
    }

    @Override
    public void update(int id, String newManufacturer, String newSeries, String newSpeed, int newCores, double newPrice) {
        String sql = "UPDATE cpus SET (manufacturer, series, speed, cores, price) = (:manufacturer, :series, :speed, :cores, :price)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("manufacturer", newManufacturer)
                    .addParameter("series", newSeries)
                    .addParameter("speed", newSpeed)
                    .addParameter("cores", newCores)
                    .addParameter("price", newPrice)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
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
