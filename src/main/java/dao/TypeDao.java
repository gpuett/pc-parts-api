package dao;

import models.*;

import java.util.List;

public interface TypeDao {

    void add(Type type);

    void addTypeToPart(Type type, Part part);

    List<Type> getAll();

    List<Part> getAllPartsByType(int typeId);

    Type findById(int id);

    void deleteById(int id);

    void clearAll();
}
