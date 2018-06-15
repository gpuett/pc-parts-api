package dao;

import models.Part;

import java.util.List;

public interface PartDao {

    void add(Part part);

    List<Part> getAll();

    Part findById(int id);

    void deleteById(int id);

    void clearAll();
}
