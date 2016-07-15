package com.apiTienda.rest;

import com.apiTienda.dao.JDBC;
import com.apiTienda.modelo.Review;
import com.apiTienda.modelo.TransformadorJson;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Api RESTful de reviews
 *
 * @author ruben
 */
@Path("/reviews")
public class ApiReviews {

    private TransformadorJson TJson = new TransformadorJson();
    private JDBC dataBase;

    public ApiReviews() throws SQLException {
        this.dataBase = new JDBC();
    }

    @POST
    public void setReview(String JSonReview) {
        Review r = (Review) this.TJson.fromJson(JSonReview, Review.class);
        try {
            this.dataBase.setReview(r);

        } catch (SQLException ex) {
            System.out.println("Error al insertar review");
        }
    }



    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getReviewById(@PathParam("id") Integer id) throws SQLException {
        Review r = this.dataBase.getReviewById(id);
        return this.TJson.toJson(r);
    }

}
