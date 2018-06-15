package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oTypeDao implements TypeDao {
    private final Sql2o sql2o;
    public Sql2oTypeDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Type type) {
        String sql = "INSERT INTO types (name) VALUES (:name)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(type)
                    .executeUpdate()
                    .getKey();
            type.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addTypeToPart(Type type, Part part) {
        String sql = "INSERT INTO parts_types (partid, typeid) VALUES (:partId, :typeId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("partId", part.getId())
                    .addParameter("typeId", type.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Type> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM types")
                    .executeAndFetch(Type.class);
        }
    }

    @Override
    public List<Part> getAllPartsByType(int typeId) {
        List<Part> parts = new ArrayList<>();
        String joinQuery = "SELECT partid FROM parts_types WHERE typeid = :typeId";

        try (Connection con = sql2o.open()) {
            List<Integer> allPartIds = con.createQuery(joinQuery)
                    .addParameter("typeId", typeId)
                    .executeAndFetch(Integer.class);
            for (Integer partId : allPartIds) {
                String partQuery = "SELECT * FROM parts WHERE id = :partId";
                parts.add(
                        con.createQuery(partQuery)
                        .addParameter("partId", partId)
                        .executeAndFetchFirst(Part.class));
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return parts;
    }

    @Override
    public Type findById(int id) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM types WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Type.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from types WHERE id = :id";
        String deleteJoin = "DELETE from parts_types WHERE typeid = :typeId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("typeId", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        try (Connection con = sql2o.open()) {
            con.createQuery("DELETE from types").executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
