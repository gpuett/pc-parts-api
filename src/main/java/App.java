import com.google.gson.Gson;
import dao.Sql2OPartDao;
import dao.Sql2oTypeDao;
import dao.Sql2oReviewDao;
import exceptions.ApiException;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {
    private static boolean isProduction = false;

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            isProduction = true;
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        Sql2OPartDao partDao;
        Sql2oTypeDao typeDao;
        Sql2oReviewDao reviewDao;
        Connection conn;
        Gson gson = new Gson();
        Sql2o sql2o;
        if(isProduction) {
            String connectionString = "jdbc:postgresql://";
            sql2o = new Sql2o(connectionString, null, null); //change with heroku deployment
        } else {
            String connectionString = "jdbc:postgresql://localhost:5432/pc_parts";
            sql2o = new Sql2o(connectionString, null, null);
        }
        partDao = new Sql2OPartDao(sql2o);
        typeDao = new Sql2oTypeDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();

        post("/parts/new", "application/json", (request, response) -> {
           Part part = gson.fromJson(request.body(), Part.class);
           partDao.add(part);
           response.status(201);
           response.type("application/json");
           return gson.toJson(part);
        });
    }
}
