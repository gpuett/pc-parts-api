import com.google.gson.Gson;
import dao.Sql2OPartDao;
import dao.Sql2oTypeDao;
import dao.Sql2oReviewDao;
import exceptions.ApiException;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

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
           return gson.toJson(part);
        });

        post("/types/new", "application/json", (request, response) -> {
           Type type = gson.fromJson(request.body(), Type.class);
           typeDao.add(type);
           response.status(201);
           return gson.toJson(type);
        });

        post("/parts/:partId/reviews/new", "application/json", (request, response) -> {
           int partId = Integer.parseInt(request.params("partId"));
           Review review = gson.fromJson(request.body(), Review.class);
           review.setCreatedAt();
           review.setFormattedCreatedAt();
           review.setPartId(partId);
           reviewDao.add(review);
           response.status(201);
           return gson.toJson(review);
        });

        post("/parts/:partId/type/:typeId", "application/json", (request, response) -> {
            int partId = Integer.parseInt(request.params("partId"));
            int typeId = Integer.parseInt(request.params("typeId"));
            Part part = partDao.findById(partId);
            Type type = typeDao.findById(typeId);
            if (part != null && type != null) {
                typeDao.addTypeToPart(type, part);
                response.status(201);
                return gson.toJson(String.format("Part '%s' and Type '%s' have been associated", part.getSeries(), type.getName()));
            } else {
                throw new ApiException(404, String.format("Part or Type does not exist"));
            }
        });


        //Filters
        exception(ApiException.class, (exception, request, response) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });

        after((request, response) -> {
            response.type("application/json");
        });
    }
}
