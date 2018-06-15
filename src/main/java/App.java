import com.google.gson.Gson;
import dao.Sql2OPartDao;
import dao.Sql2oTypeDao;
import dao.Sql2oReviewDao;
import exceptions.ApiException;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
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
            String connectionString = "jdbc:postgresql://ec2-107-21-255-2.compute-1.amazonaws.com:5432:dbfktgojsojurp";
            sql2o = new Sql2o(connectionString, null, null); //change with heroku deployment
        } else {
            String connectionString = "jdbc:postgresql://localhost:5432/pc_parts";
            sql2o = new Sql2o(connectionString, "cpxgxeppylxbtd", "9056aa2af00e376bb95351123e29a3dfe9f9d54cf42c669497a6f4c66c13467e");
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

        get("/parts", "application/json", (request, response) -> {
            System.out.println(partDao.getAll());
            if(partDao.getAll().size() > 0) {
                return gson.toJson(partDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no parts are currently listed in the database.\"}";
            }
        });

        get("/parts/:id", "application/json", (request, response) -> {
            int partId = Integer.parseInt(request.params("id"));
            Part partToFind = partDao.findById(partId);
            if(partToFind == null) {
                throw new ApiException(404, String.format("No part with id: '%s' exists", request.params("id")));
            }
            return gson.toJson(partToFind);
        });

        get("types", "application/json", (request, response) -> {
            return gson.toJson(typeDao.getAll());
        });

        get("/parts/:partId/reviews", "application/json", (request, response) -> {
            int partId = Integer.parseInt(request.params("partId"));
            Part partToFind = partDao.findById(partId);
            if(partToFind == null) {
                throw new ApiException(404, String.format("No part with id: '%s' exists", request.params("partId")));
            }
            List<Review> allReviews = reviewDao.getAllReviewsByPartId(partId);
            return gson.toJson(allReviews);
        });

        get("/types/:id/parts", "application/json", (request, response) -> {
            int typeId = Integer.parseInt(request.params("id"));
            Type typeToFind = typeDao.findById(typeId);
            if (typeToFind == null) {
                throw new ApiException(404, String.format("No type with id: '%s' exists", request.params("id")));
            } else if (typeDao.getAllPartsByType(typeId).size() == 0) {
                return "{\"message\":\"I'm sorry, but no parts are listed for this type.\"}";
            } else {
                return gson.toJson(typeDao.getAllPartsByType(typeId));
            }
        });

        get("parts/:id/sortedReviews", "application/json", (request, response) -> {
            int partId = Integer.parseInt(request.params("id"));
            Part partToFind = partDao.findById(partId);
            List<Review> allReviews;
            if(partToFind == null) {
                throw new ApiException(404, String.format("No part with id: '%s' exists", request.params("id")));
            }
            allReviews = reviewDao.getAllReviewsByPartSortedNewestToOldest(partId);
            return gson.toJson(allReviews);
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
