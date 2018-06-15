package dao;

import models.*;
import org.sql2o.Sql2o;

public class Sql2oTypeDao {
    private final Sql2o sql2o;
    public Sql2oTypeDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


}
